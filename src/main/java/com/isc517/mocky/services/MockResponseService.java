package com.isc517.mocky.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.isc517.mocky.entities.MockResponse;
import com.isc517.mocky.repositories.MockResponseRepository;
import com.isc517.mocky.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;


import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MockResponseService {
    @Autowired
    private MockResponseRepository mockRepo;
    private static final Gson gson = new Gson();

    @Value("${APP_HOST}")
    String HOST;


    public MockResponse getMockEntity(String id){
        Optional<MockResponse> response = mockRepo.findById(id);
        return response.orElse(null);
    }

    public List<MockResponse> getAllMocksByUser(String username){
        return mockRepo.findAllByUser_Username(username);
    }

    public String getMethodById(String id){
        Optional<MockResponse> mock = mockRepo.findById(id);
        return mock.map(MockResponse::getMethod).orElse(null);
    }


    public ResponseEntity<String> createResponse(MockResponse mock){
        HttpStatus status = HttpStatus.valueOf(mock.getStatusCode());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(mock.getContentType()));
        //headers.setExpires(mock.getExpirationDate().);

       if(!mock.getHeaders().equals("")){
           Type type = new TypeToken<Map<String, String>>(){}.getType();
           Map<String, String> headersMap = gson.fromJson(mock.getHeaders(), type);
           headersMap.forEach((k,v)->{
               System.out.println(k + " " + v);
               headers.add(k,v);
           });
       }
        //status(status).headers(headers).body(mock.getBody());

        return ResponseEntity.status(status).headers(headers).body(mock.getBody());
    }
    public MockResponse createMock(MockResponse newMock){
        LocalDateTime creationDate = LocalDateTime.now();
        newMock.setCreationDate(creationDate);
        switch (newMock.getExpirationTime()){
            case Day:
                newMock.setExpirationDate(creationDate.plusDays(1));
                break;
            case Hour:
                newMock.setExpirationDate(creationDate.plusHours(1));
                break;
            case Week:
                newMock.setExpirationDate(creationDate.plusWeeks(1));
                break;
            case Month:
                newMock.setExpirationDate(creationDate.plusMonths(1));
                break;
            default:
                newMock.setExpirationDate(creationDate.plusYears(1));
                break;
        }

            MockResponse mock2 = mockRepo.save(newMock);



        mock2.setRoute("http://"+HOST+"/mock/response/" + mock2.getId());
        return mockRepo.save(mock2);
    }

    public ResponseEntity updateMock(MockResponse newMock){
        Optional<MockResponse> oldMock = mockRepo.findById(newMock.getId());
        if(oldMock.isPresent()){
            oldMock.get().setRoute(newMock.getRoute());
            oldMock.get().setMethod(newMock.getMethod());
            oldMock.get().setHeaders(newMock.getHeaders());
            oldMock.get().setStatusCode(newMock.getStatusCode());
            oldMock.get().setContentType(newMock.getContentType());
            oldMock.get().setBody(newMock.getBody());
            oldMock.get().setEndpointName(newMock.getEndpointName());
            oldMock.get().setEndpointDescription(newMock.getEndpointDescription());
            oldMock.get().setExpirationTime(newMock.getExpirationTime());
            mockRepo.save(oldMock.get());
            return ResponseEntity.ok().body(oldMock.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mock Not Found");
    }

    public ResponseEntity<String> deleteMock(String id){
        Optional<MockResponse> response = mockRepo.findById(id);
        if(response.isPresent()){
            mockRepo.delete(response.get());
            return ResponseEntity.ok().body("Mock deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mock Not Found");
    }


    public ResponseEntity<String> getMockResponse(String id){
        MockResponse response = getMockEntity(id);
        if(response == null || isExpired(response)){
            return ResponseEntity.status(404).build();
        }
        return createResponse(response);
    }

    public boolean isExpired(MockResponse mock){
        LocalDateTime actualDate = LocalDateTime.now();
        return actualDate.isAfter(mock.getExpirationDate());
    }



}
