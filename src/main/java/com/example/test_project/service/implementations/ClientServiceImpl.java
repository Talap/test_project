package com.example.test_project.service.implementations;

import com.example.test_project.controller.exception.ServiceException;
import com.example.test_project.models.Client;
import com.example.test_project.models.ContactDetails;
import com.example.test_project.models.Requests.ClientRequest;
import com.example.test_project.models.responses.ClientResponse;
import com.example.test_project.models.responses.ContactDetailResponse;
import com.example.test_project.repository.ClientRepository;
import com.example.test_project.repository.ContactDetailsRepository;
import com.example.test_project.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Builder
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    private ContactDetailsRepository contactDetailsRepository;

    @Override
    public Client createClient(ClientRequest clientRequest)  {
        Client client = new Client(clientRequest.getFirstName(), clientRequest.getSecondName(), clientRequest.getPatronymic());
        clientRepository.save(client);
        return client;
    }

    @Override
    public Client updateClient(ClientRequest clientRequest, Long id) {
        Optional<Client> client = Optional.ofNullable(clientRepository.findById(id).
                orElseThrow(() -> new ServiceException(id)));
            client.get().setFirstName(clientRequest.getFirstName());
            client.get().setSecondName(clientRequest.getSecondName());
            client.get().setPatronymic(clientRequest.getPatronymic());
        clientRepository.save(client.get());
        return client.get();
    }


    @Override
    public List<ClientResponse> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        List<ContactDetails> contactDetailsList = contactDetailsRepository.findAll();
        List<ContactDetailResponse> contactDetailResponseList = contactDetailsList.stream()
                .map((e) -> ContactDetailResponse.builder()
//                .contactType(e.getContactType().getPhoneType())
                .phoneNumber(e.getTelephoneNumber())
                .clientId(e.getClient().getId())
                .build())
                .collect(Collectors.toList());

        List<ClientResponse> clientResponses = clients.stream().map((e) -> ClientResponse.builder()
                .clientId(e.getId())
                .firstName(e.getFirstName())
                .secondName(e.getSecondName())
                .patronymic(e.getPatronymic())
                .build())
                .collect(Collectors.toList());

        clientResponses.parallelStream().forEach(e -> {
            e.setContactDetails(contactDetailResponseList.stream().
                    filter(qoE -> qoE.getClientId().equals(e.getClientId()))
                    .collect(Collectors.toList()));
        });
        return clientResponses;
    }


    @Override
    public void deleteClientById(Long id) {
        Optional<Client> client = Optional.ofNullable(clientRepository.findById(id).
                orElseThrow(() -> new ServiceException(id)));
//        List<ContactDetails> contactDetailsList =
//                contactDetailsRepository.findAllByClientId(client.get().getId());
//        for (ContactDetails contactDetails : contactDetailsList) {
//            contactDetailsRepository.delete(contactDetails);
//        }
        clientRepository.delete(client.get());
    }

//    @Override
//    public ClientResponse getClientWithAllContactInfo(Long clientId) {
//        Client client = clientRepository.findFirstById(clientId);
//        List<ContactDetails> contactDetailsList = contactDetailsRepository.findAllByClientId(clientId);
//
//        List<ContactDetailResponse> contactDetailResponseList = contactDetailsList.stream()
//                .map((e) -> ContactDetailResponse.builder()
////                        .contactType(e.getContactType().getPhoneType())
//                        .phoneNumber(e.getTelephoneNumber())
//                        .clientId(e.getClient().getId())
//                        .build())
//                .collect(Collectors.toList());
//
//        ClientResponse clientResponse = ClientResponse.builder()
//                .clientId(client.getId())
//                .firstName(client.getFirstName())
//                .secondName(client.getSecondName())
//                .patronymic(client.getPatronymic())
//                .contactDetails(contactDetailResponseList)
//                .build();
//        return clientResponse;
//    }

//    @Override
//    public ClientResponse getClientWithAllContactInfo(Long clientId) {
//        Client client = clientRepository.findFirstById(clientId);
//        List<ContactDetailResponse> contactDetailResponseList = new ArrayList<>();
//        List<ContactDetails> contactDetailsList = client.getContactDetails();
//        for(ContactDetails contactDetails: contactDetailsList){
//            ContactDetailResponse contactDetailResponse = new ContactDetailResponse(
//                    contactDetails.getTelephoneNumber(),
//                    contactDetails.getContactType().getPhoneType(),
//                    clientId);
//            contactDetailResponseList.add(contactDetailResponse);
//        }
//        ClientResponse clientResponse = new ClientResponse(clientId,
//                client.getFirstName(),
//                client.getSecondName(),
//                client.getPatronymic(),
//                contactDetailResponseList);
//        return clientResponse;
//    }


    //Выше расположен еще один способ с использованием response для отсеивания лишних атрибутов
    @Override
    public Client getClientWithAllContactInfo(Long clientId) {
        Client client = clientRepository.findFirstById(clientId);
        return client;
    }


}



