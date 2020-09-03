package com.example.test_project.models;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Optional;

@ApiModel(description = "Model of tickets user for parking")
@Entity
@Component
@Table(name="contact_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_contact_details",
        initialValue = 1,
        allocationSize = 1
)
public class ContactDetails extends AuditModel{
    @Column(name="phone_number")
    @ApiModelProperty(notes = "Номер телефона")
    private String telephoneNumber;

    @ManyToOne
    @ApiModelProperty(notes = "Вид телефона")
    private ContactType contactType;

    @ManyToOne
    @JoinColumn(name = "clients")
    private Client client;

    public ContactDetails(String phoneNumber, ContactType contactType, Optional<Client> client) {
    }
}
