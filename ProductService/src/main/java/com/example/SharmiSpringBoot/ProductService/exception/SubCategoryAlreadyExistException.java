package com.example.SharmiSpringBoot.ProductService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SubCategoryAlreadyExistException extends RuntimeException {
    public SubCategoryAlreadyExistException(String message) {
        super(message);
    }
}
