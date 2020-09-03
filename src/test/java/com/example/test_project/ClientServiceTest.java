package com.example.test_project;

import com.example.test_project.models.Client;
import com.example.test_project.models.Requests.ClientRequest;
import com.example.test_project.models.responses.ClientResponse;
import com.example.test_project.repository.ClientRepository;
import com.example.test_project.repository.ContactDetailsRepository;
import com.example.test_project.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {
    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ContactDetailsRepository contactDetailsRepository;

    public ClientServiceTest() {
    }

    @Test
    public void createClient(){
        ClientRequest clientRequest = new ClientRequest("First", "Second", "Patronymic");
        Client client = clientService.createClient(clientRequest);
        assertThat(client.getFirstName()).isEqualTo("First");
        assertThat(client.getSecondName()).isEqualTo("Second");
        assertThat(client.getPatronymic()).isEqualTo("Patronymic");
        assertThat(client.getId()).isNotNull();
    }

    @Test
    public void updateClient(){
        ClientRequest clientRequest = new ClientRequest("Talap", "Kurmanov", "Muratovich");
        Long id = 1L;
        Client client = clientService.updateClient(clientRequest, id);
        assertThat(client.getFirstName()).isEqualTo("Talap");
        assertThat(client.getSecondName()).isEqualTo("Kurmanov");
        assertThat(client.getPatronymic()).isEqualTo("Muratovich");
        assertThat(client.getId()).isEqualTo(id);
        assertThat(client.getId()).isNotNull();
    }


    @Test
    public void getAllClients(){
        List<ClientResponse> clientResponseList = clientService.getAllClients();
        assertThat(clientResponseList).size().isEqualTo(clientRepository.findAll().size());
    }

    @Test
    public void deleteClient(){
        Long id = 1L;
        clientService.deleteClientById(id);
        assertThat(contactDetailsRepository.findAllByClientId(id).size() == 0);
        assertThat(clientRepository.findFirstById(id)).isNull();

    }

}
