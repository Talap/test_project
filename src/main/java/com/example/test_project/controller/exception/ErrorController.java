package com.example.test_project.controller.exception;

import com.example.test_project.controller.BaseController;
import com.example.test_project.controller.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
public class ErrorController extends BaseController {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> resourceNotFoundException(ServiceException e) {
        return buildResponse(buildErrorResponse(e), e.getHttpStatus());
    }
}
