package com.example.test_project.service.implementations;

import com.example.test_project.controller.exception.ErrorCode;
import com.example.test_project.controller.exception.ServiceException;
import com.example.test_project.models.Client;
import com.example.test_project.models.ContactDetails;
import com.example.test_project.models.ContactType;
import com.example.test_project.models.Requests.ContactDetailRequest;
import com.example.test_project.repository.ClientRepository;
import com.example.test_project.repository.ContactDetailsRepository;
import com.example.test_project.repository.ContactTypeRepository;
import com.example.test_project.service.ContactDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Optional;


@AllArgsConstructor
@Service
public class ContactDetailServiceImpl implements ContactDetailService {
    private ClientRepository clientRepository;
    private ContactDetailsRepository contactDetailsRepository;
    private ContactTypeRepository contactTypeRepository;


    @Override
    public ContactDetails addContactDetailById(ContactDetailRequest contactDetailRequest) throws ServiceException {
        Optional<Client> client = Optional.ofNullable(clientRepository.findById(contactDetailRequest.getClientId()).
                orElseThrow(() -> new ServiceException(contactDetailRequest.getClientId())));
        ContactType contactType = contactTypeRepository.findFirstById(contactDetailRequest.getPhoneType());
        if(contactType == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.CONTACT_TYPE_NOT_FOUND)
                    .message("Такого способа связи не существует")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
        ContactDetails contactDetails = new ContactDetails(contactDetailRequest.getPhoneNumber(), contactType, client.get());
        contactDetailsRepository.save(contactDetails);
        return contactDetails;
    }


    @Override
    public ContactDetails updateContactDetailById(ContactDetailRequest contactDetailRequest, Long contactDetailId) throws ServiceException {
        ContactDetails contactDetails = contactDetailsRepository.findFirstByIdAndDeletedAtIsNull(contactDetailId);
        Optional<Client> client = Optional.ofNullable(clientRepository.findById(contactDetailRequest.getClientId()).
                orElseThrow(() -> new ServiceException(contactDetailRequest.getClientId())));
        ContactType contactType = contactTypeRepository.findFirstById(contactDetailRequest.getPhoneType());
        if(contactType == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.CONTACT_TYPE_NOT_FOUND)
                    .message("Такого способа связи не существует")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
        contactDetails.setTelephoneNumber(contactDetailRequest.getPhoneNumber());
        contactDetails.setContactType(contactType);
        contactDetails.setClient(client.get());
        contactDetailsRepository.save(contactDetails);
        return contactDetails;
    }


    @Override
    public void deleteContactDetailById(Long id) {
        try {
            ContactDetails contactDetails = contactDetailsRepository.findFirstByIdAndDeletedAtIsNull(id);
            contactDetailsRepository.delete(contactDetails);
        } catch (Exception e){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.CONTACT_TYPE_NOT_FOUND)
                    .message("Такого способа связи не существует")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

}
