package com.example.test_project.models.responses;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class ContactDetailResponse {
    private String phoneNumber;
    private String contactType;
    private Long clientId;

    public ContactDetailResponse(String phoneNumber, String contactType, Long clientId) {
        this.phoneNumber = phoneNumber;
        this.contactType = contactType;
        this.clientId = clientId;
    }



}
