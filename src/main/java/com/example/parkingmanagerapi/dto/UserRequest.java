package com.example.parkingmanagerapi.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String surname;
    private String password;
    private String mail;
    private Boolean status;
    private Long entrepriseId;
}
