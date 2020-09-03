package com.example.test_project.models.Requests;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class ClientRequest {
    private String firstName;
    private String secondName;
    private String patronymic;

    public ClientRequest(String firstName, String secondName, String patronymic) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
    }
}
