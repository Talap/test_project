package com.example.test_project.models.responses;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
public class ClientResponse {
    private Long clientId;
    private String firstName;
    private String secondName;
    private String patronymic;
    private List<ContactDetailResponse> contactDetails;

    public ClientResponse(Long clientId, String firstName, String secondName, String patronymic, List<ContactDetailResponse> contactDetails) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.contactDetails = contactDetails;
    }
}
