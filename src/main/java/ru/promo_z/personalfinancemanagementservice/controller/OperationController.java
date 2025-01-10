package ru.promo_z.personalfinancemanagementservice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo_z.personalfinancemanagementservice.dto.request.OperationRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.OperationResponseDto;
import ru.promo_z.personalfinancemanagementservice.exception.CategoryNotFoundException;
import ru.promo_z.personalfinancemanagementservice.exception.OperationIncorrectException;
import ru.promo_z.personalfinancemanagementservice.exception.WalletNotFoundException;
import ru.promo_z.personalfinancemanagementservice.service.OperationService;

@RestController
@RequestMapping("/operations")
public class OperationController {

    private final OperationService operationService;

    @Autowired
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping
    public ResponseEntity<OperationResponseDto> createOperation(@RequestBody @Valid OperationRequestDto operationRequestDto)
            throws WalletNotFoundException, CategoryNotFoundException, OperationIncorrectException {

        return ResponseEntity.status(HttpStatus.CREATED).body(operationService.createOperation(operationRequestDto));
    }
}
