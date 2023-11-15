package com.minibank.transfers.entrypoint.controller;

import com.minibank.transfers.entrypoint.dto.request.CreateTransferRequestDTO;
import com.minibank.transfers.entrypoint.UrlConstant;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = UrlConstant.TRANSFER_URI)
public class TransferController  {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransfer(@RequestBody @Valid CreateTransferRequestDTO createTransferRequestDTO) {
        log.info("Class {} method createAccount", this.getClass().getName());
        log.info("CreateTransferRequestDTO {}", createTransferRequestDTO);


    }

}



