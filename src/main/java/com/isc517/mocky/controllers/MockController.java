package com.isc517.mocky.controllers;

import com.isc517.mocky.entities.MockResponse;
import com.isc517.mocky.services.MockResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mock")
public class MockController {
    @Autowired
    private MockResponseService mockService;

    // ENTITY CRUD
    @CrossOrigin
    @GetMapping("/all/{user}")
    public List<MockResponse> getAllMocksByUsername(@PathVariable("user") String username){
        return mockService.getAllMocksByUser(username);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public MockResponse getMockEntity(@PathVariable("id") String id){
        return mockService.getMockEntity(id);
    }

    @CrossOrigin
    @PostMapping("/add")
    public MockResponse postMockEntity(@RequestBody MockResponse newMock){
        return mockService.createMock(newMock);
    }

    @CrossOrigin
    @PutMapping("/update")
    public ResponseEntity updateMockEntity(@RequestBody MockResponse newMock){
        return mockService.updateMock(newMock);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMockEntity(@PathVariable("id") String id){
        return mockService.deleteMock(id);
    }


    // MOCKS REQUESTS
    @CrossOrigin
    @GetMapping ("/response/{id}")
    public ResponseEntity getRequest(@PathVariable("id") String id, HttpServletRequest request) {

        String method = mockService.getMethodById(id);
        if(Objects.equals(request.getMethod(), method)){
            return mockService.getMockResponse(id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mock Not Found");
    }

    @CrossOrigin
    @PostMapping("/response/{id}")
    public ResponseEntity postRequest(@PathVariable("id") String id, HttpServletRequest request){
        String method = mockService.getMethodById(id);
        if(Objects.equals(request.getMethod(), method)){
            return mockService.getMockResponse(id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mock Not Found");
    }

    @CrossOrigin
    @DeleteMapping(value ="/response/{id}")
    public ResponseEntity deleteRequest(@PathVariable("id") String id, HttpServletRequest request){
        String method = mockService.getMethodById(id);
        if(Objects.equals(request.getMethod(), method)){
            return mockService.getMockResponse(id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mock Not Found");
    }

    @CrossOrigin
    @PutMapping(value ="/response/{id}")
    public ResponseEntity putRequest(@PathVariable("id") String id, HttpServletRequest request){
        String method = mockService.getMethodById(id);
        if(Objects.equals(request.getMethod(), method)){
            return mockService.getMockResponse(id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mock Not Found");
    }

    @CrossOrigin
    @RequestMapping(value ="/response/{id}", method = RequestMethod.OPTIONS)
    public ResponseEntity optionsRequest(@PathVariable("id") String id, HttpServletRequest request){
        String method = mockService.getMethodById(id);
        if(Objects.equals(request.getMethod(), method)){
            return mockService.getMockResponse(id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mock Not Found");
    }
}
