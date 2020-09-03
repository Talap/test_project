package com.example.test_project.models.Requests;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class ContactDetailRequest {
    private Long clientId;
    private String phoneNumber;
    private Long phoneType;

    public ContactDetailRequest(Long clientId, String phoneNumber, Long phoneType) {
        this.clientId = clientId;
        this.phoneNumber = phoneNumber;
        this.phoneType = phoneType;
    }
}
