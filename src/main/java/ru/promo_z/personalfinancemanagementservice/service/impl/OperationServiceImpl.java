package ru.promo_z.personalfinancemanagementservice.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.promo_z.personalfinancemanagementservice.dto.request.OperationRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.OperationResponseDto;
import ru.promo_z.personalfinancemanagementservice.exception.CategoryNotFoundException;
import ru.promo_z.personalfinancemanagementservice.exception.OperationIncorrectException;
import ru.promo_z.personalfinancemanagementservice.exception.WalletNotFoundException;
import ru.promo_z.personalfinancemanagementservice.mapper.OperationMapper;
import ru.promo_z.personalfinancemanagementservice.model.*;
import ru.promo_z.personalfinancemanagementservice.model.enums.OperationType;
import ru.promo_z.personalfinancemanagementservice.repository.CategoryRepository;
import ru.promo_z.personalfinancemanagementservice.repository.OperationRepository;
import ru.promo_z.personalfinancemanagementservice.repository.WalletRepository;
import ru.promo_z.personalfinancemanagementservice.security.AuthUser;
import ru.promo_z.personalfinancemanagementservice.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;
    private final WalletRepository walletRepository;
    private final CategoryRepository categoryRepository;
    private final OperationMapper operationMapper;

    public OperationServiceImpl(OperationRepository operationRepository, WalletRepository walletRepository,
                                CategoryRepository categoryRepository, OperationMapper operationMapper) {

        this.operationRepository = operationRepository;
        this.walletRepository = walletRepository;
        this.categoryRepository = categoryRepository;
        this.operationMapper = operationMapper;
    }

    @Transactional
    @Override
    public OperationResponseDto createOperation(OperationRequestDto operationRequestDto) throws WalletNotFoundException,
            CategoryNotFoundException, OperationIncorrectException {

        User authUser = getAuthUser();
        Wallet wallet = walletRepository.findByIdAndUser_Id(operationRequestDto.getWalletId(), authUser.getId())
                .orElseThrow(() -> new WalletNotFoundException("No wallet was found for the specified user."));
        Category category = categoryRepository.findByIdAndUser_Id(operationRequestDto.getCategoryId(), authUser.getId())
                .orElseThrow(() -> new CategoryNotFoundException("No category found for the specified user."));

        String operationType = operationRequestDto.getOperationType();
        if (!(operationType.equals(OperationType.EXPENSE.toString())
                || operationType.equals(OperationType.INCOME.toString()))) {

            throw new OperationIncorrectException("Incorrect operation type. Acceptable: "
                    + OperationType.EXPENSE + " and " + OperationType.INCOME + ".");
        }

        long amount = operationRequestDto.getAmount();
        if (amount <= 0) {
            throw new OperationIncorrectException("The operation amount must be greater than zero.");
        }

        if (operationType.equals(OperationType.EXPENSE.toString()) && (wallet.getBalance() - amount < 0)) {
            throw new OperationIncorrectException("The operation cannot be performed because there " +
                    "are not enough funds in the wallet.");
        }

        if (operationType.equals(OperationType.EXPENSE.toString())) {
            wallet.setBalance(wallet.getBalance() - operationRequestDto.getAmount());
        } else {
            wallet.setBalance(wallet.getBalance() + operationRequestDto.getAmount());
        }
        walletRepository.save(wallet);

        Operation newOperation = operationMapper.operationRequestDtoToOperation(operationRequestDto);
        newOperation.setWallet(wallet);
        newOperation.setCategory(category);

        return operationMapper.operationToOperationResponseDto(operationRepository.saveAndFlush(newOperation));
    }

    private User getAuthUser() {
        AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return authUser.getUser();
    }
}
