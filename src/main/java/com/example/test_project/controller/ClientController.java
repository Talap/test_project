package com.example.test_project.controller;


import com.example.test_project.controller.exception.ServiceException;
import com.example.test_project.models.Requests.ClientRequest;
import com.example.test_project.models.responses.SuccessResponse;
import com.example.test_project.service.ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@Service
@RestController
@RequestMapping("/api/client")
@AllArgsConstructor
public class ClientController extends BaseController {
    private ClientService clientService;

    @PostMapping(value = "/add")
    @ApiOperation("Добавление клиента")
    public ResponseEntity<?> add(@ApiParam("Добавление нового клиента") @RequestBody ClientRequest clientRequest)  {

        return buildResponse(clientService.createClient(clientRequest), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    @ApiOperation("Изменение клиента")
    public ResponseEntity<?> update(@ApiParam("Обновление клиента")
                                        @RequestParam Long id,
                                        @RequestBody ClientRequest clientRequest)  {

        return buildResponse(clientService.updateClient(clientRequest, id), HttpStatus.OK);
    }

    @GetMapping(value = "/get/all")
    @ApiOperation("Получение списка всех клиентов")
    public ResponseEntity<?> getAll(){
        return buildResponse(clientService.getAllClients(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление клиента и его контактов по id")
    public  ResponseEntity<?> deleteWorkplaceId(@RequestParam Long id) throws ServiceException {
        clientService.deleteClientById(id);
        return buildResponse(SuccessResponse
                .builder().message("deleted")
                .build(), HttpStatus.OK);
    }


    @GetMapping(value = "/get/single")
    @ApiOperation("Получение одного клиента с контактами")
    public ResponseEntity<?> getOneClient(@RequestParam Long id){
        return buildResponse(clientService.getClientWithAllContactInfo(id), HttpStatus.OK);
    }

}
