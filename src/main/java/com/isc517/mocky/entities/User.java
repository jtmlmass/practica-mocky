package com.isc517.mocky.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @NonNull
    @Getter
    @Setter
    @Column(name = "username", updatable = false, nullable = false)
    private String username;

    @NonNull
    @Getter
    @Setter
    private String password;

    @NonNull
    @Getter
    @Setter
    private String name;

    @NonNull
    @Getter
    @Setter
    @ElementCollection(targetClass = String.class,fetch = FetchType.EAGER)
    List<String> roles;

    @NonNull
    @Getter
    @Setter
    private boolean isAdministrator;


    @Getter @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<MockResponse> userMocks;
}
