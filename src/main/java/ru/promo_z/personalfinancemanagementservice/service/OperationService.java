package ru.promo_z.personalfinancemanagementservice.service;

import ru.promo_z.personalfinancemanagementservice.dto.request.OperationRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.OperationResponseDto;
import ru.promo_z.personalfinancemanagementservice.exception.CategoryNotFoundException;
import ru.promo_z.personalfinancemanagementservice.exception.OperationIncorrectException;
import ru.promo_z.personalfinancemanagementservice.exception.WalletNotFoundException;

public interface OperationService {

    OperationResponseDto createOperation(OperationRequestDto operationRequestDto)
            throws WalletNotFoundException, CategoryNotFoundException, OperationIncorrectException;
}
