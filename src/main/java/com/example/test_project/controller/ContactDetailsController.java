package com.example.test_project.controller;

import com.example.test_project.controller.exception.ServiceException;
import com.example.test_project.models.Requests.ContactDetailRequest;
import com.example.test_project.models.responses.SuccessResponse;
import com.example.test_project.service.ContactDetailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@RestController
@RequestMapping("/api/contact/details")
@AllArgsConstructor
public class ContactDetailsController extends BaseController {
    private ContactDetailService contactDetailService;

    @PostMapping(value = "/add")
    @ApiOperation("Добавления контактной информаций по Клиенту")
    public ResponseEntity<?> add(@ApiParam("Добавление номера")
                                 @RequestBody ContactDetailRequest contactDetailRequest) throws ServiceException {

        return buildResponse(contactDetailService.addContactDetailById(contactDetailRequest), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    @ApiOperation("обновление контактной информаций по Клиенту")
    public ResponseEntity<?> update(@ApiParam("Изменение контактных данных")
                                    @RequestParam Long contactDetailId,
                                    @RequestBody ContactDetailRequest contactDetailRequest) throws ServiceException {

        return buildResponse(contactDetailService.updateContactDetailById
                (contactDetailRequest, contactDetailId), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление контакта по id")
    public  ResponseEntity<?> deleteWorkplaceId(@RequestParam Long id) throws ServiceException {
        contactDetailService.deleteContactDetailById(id);
        return buildResponse(SuccessResponse
                .builder().message("deleted")
                .build(), HttpStatus.OK);
    }

}
