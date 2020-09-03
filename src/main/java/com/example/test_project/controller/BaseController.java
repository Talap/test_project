package com.example.test_project.controller;

import com.example.test_project.controller.exception.ErrorResponse;
;
import com.example.test_project.controller.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    protected ResponseEntity<?> buildResponse(Object data, HttpStatus httpStatus){
        return new ResponseEntity<>(data , httpStatus);
    }

    protected ResponseEntity<?> buildResponse(HttpStatus httpStatus){
        return new ResponseEntity<>(httpStatus);
    }

    protected ErrorResponse buildErrorResponse(ServiceException serviceException){
        return ErrorResponse.builder()
                .errorCode(serviceException.getErrorCode())
                .message(serviceException.getMessage())
                .build();
    }
    protected ResponseEntity<?> buildSuccesResponse(Object data){
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
