package com.example.test_project.service;

import com.example.test_project.controller.exception.ServiceException;
import com.example.test_project.models.ContactDetails;
import com.example.test_project.models.Requests.ContactDetailRequest;

public interface ContactDetailService {
    ContactDetails addContactDetailById(ContactDetailRequest contactDetailRequest) throws ServiceException;
    ContactDetails updateContactDetailById(ContactDetailRequest contactDetailRequest, Long contactDetailId) throws ServiceException;
    void deleteContactDetailById(Long id);

}
