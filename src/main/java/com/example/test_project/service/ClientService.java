package com.example.test_project.service;

import com.example.test_project.models.Client;
import com.example.test_project.models.Requests.ClientRequest;
import com.example.test_project.models.responses.ClientResponse;

import java.util.List;


public interface ClientService {
    Client createClient (ClientRequest clientRequest);
    Client updateClient (ClientRequest clientRequest, Long id);
    List<ClientResponse> getAllClients();
    void deleteClientById(Long id);
}
