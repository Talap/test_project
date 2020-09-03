package com.example.test_project;

import com.example.test_project.models.Client;
import com.example.test_project.models.ContactDetails;
import com.example.test_project.models.Requests.ClientRequest;
import com.example.test_project.models.Requests.ContactDetailRequest;
import com.example.test_project.repository.ContactDetailsRepository;
import com.example.test_project.service.ContactDetailService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
class ContactDetailServiceImplTest {

    @Autowired
    private ContactDetailService contactDetailService;

    @Autowired
    private ContactDetailsRepository contactDetailsRepository;

    @Test
    void addContactDetailById() {
        ContactDetailRequest contactDetailRequest = new ContactDetailRequest(1L, "87777171717", 2L);
        ContactDetails contactDetails = contactDetailService.addContactDetailById(contactDetailRequest);
        assertThat(contactDetails.getClient().getId()).isEqualTo(1L);
        assertThat(contactDetails.getContactType().getId()).isEqualTo(2L);
        assertThat(contactDetails.getTelephoneNumber()).isEqualTo("87777171717");
        assertThat(contactDetails.getId()).isNotNull();

    }

    @Test
    public void updateContactDetailById(){
        ContactDetailRequest contactDetailRequest = new ContactDetailRequest(1L, "8766666666", 3L);
        Long id = 1L;
        ContactDetails contactDetails = contactDetailService.updateContactDetailById(contactDetailRequest, id);
        assertThat(contactDetails.getClient().getId()).isEqualTo(contactDetailRequest.getClientId());
        assertThat(contactDetails.getTelephoneNumber()).isEqualTo(contactDetailRequest.getPhoneNumber());
        assertThat(contactDetails.getContactType().getId()).isEqualTo(contactDetailRequest.getPhoneType());
        assertThat(contactDetails.getId()).isEqualTo(id);
        assertThat(contactDetails.getId()).isNotNull();
    }

    @Test
    public void deleteContactDetail(){
        Long id = 1L;
        contactDetailService.deleteContactDetailById(id);
        assertThat(contactDetailsRepository.findFirstByIdAndDeletedAtIsNull(id)).isNull();
    }
}