package com.kryvoruchka.controller;

import com.kryvoruchka.DTO.MessageDTO;
import com.kryvoruchka.domain.Maker;
import com.kryvoruchka.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchMakerException.class)
    ResponseEntity<MessageDTO> handleNoSuchMakerException(){
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such maker not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchClothesException.class)
    ResponseEntity<MessageDTO> handleNoSuchClothesException(){
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such clothes not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchCustomerException.class)
    ResponseEntity<MessageDTO> handleNoSuchCustomerException(){
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such customer not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistsCustomerForClothesException.class)
    ResponseEntity<MessageDTO> handleExistsCustomerForClothesException(){
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are customer for this " +
                "clothes"),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsClothesForMakerException.class)
    ResponseEntity<MessageDTO> handleExistsClothesForMakerException(){
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are clothes for this maker"),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsClothesForCustomerException.class)
    ResponseEntity<MessageDTO> handleExistsClothesForCustomerException(){
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are clothes for this customer"),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AlreadyExistsCustomerInClothesException.class)
    ResponseEntity<MessageDTO> handleAlreadyExistsCustomerInClothesException(){
        return new ResponseEntity<MessageDTO>(new MessageDTO("Add imposible. The clothes already contain this " +
                "customer"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomerAbsentException.class)
    ResponseEntity<MessageDTO> handleCustomerAbsentException(){
        return new ResponseEntity<MessageDTO>(new MessageDTO("Now this customer is absent"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClothesHasNotCustomerException.class)
    ResponseEntity<MessageDTO> handleClothesHasNotCustomerException(){
        return new ResponseEntity<MessageDTO>(new MessageDTO("The clothes hasn`t this customer"), HttpStatus.NOT_FOUND);
    }
}
