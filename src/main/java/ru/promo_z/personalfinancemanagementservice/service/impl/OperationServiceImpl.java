package ru.promo_z.personalfinancemanagementservice.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.promo_z.personalfinancemanagementservice.dto.request.OperationRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.OperationResponseDto;
import ru.promo_z.personalfinancemanagementservice.exception.CategoryNotFoundException;
import ru.promo_z.personalfinancemanagementservice.exception.OperationIncorrectException;
import ru.promo_z.personalfinancemanagementservice.exception.WalletNotFoundException;
import ru.promo_z.personalfinancemanagementservice.mapper.OperationMapper;
import ru.promo_z.personalfinancemanagementservice.model.Category;
import ru.promo_z.personalfinancemanagementservice.model.Operation;
import ru.promo_z.personalfinancemanagementservice.model.Wallet;
import ru.promo_z.personalfinancemanagementservice.model.enums.OperationType;
import ru.promo_z.personalfinancemanagementservice.repository.CategoryRepository;
import ru.promo_z.personalfinancemanagementservice.repository.OperationRepository;
import ru.promo_z.personalfinancemanagementservice.repository.WalletRepository;
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

        Wallet wallet = walletRepository.findById(operationRequestDto.getWalletId())
                .orElseThrow(() -> new WalletNotFoundException("The wallet with the specified ID was not found."));

        Category category = categoryRepository.findById(operationRequestDto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("The category with the specified ID was not found."));

        long amount = operationRequestDto.getAmount();
        String operationType = operationRequestDto.getOperationType();

        if (wallet.getBalance() + amount < 0) {
            throw new OperationIncorrectException("The operation cannot be performed because there " +
                    "are not enough funds in the wallet.");
        }

        if (amount == 0) {
            throw new OperationIncorrectException("The transaction amount cannot be zero.");
        }

        if ((amount < 0 && !operationType.equals(OperationType.EXPENSE.toString()))
                || (amount > 0 && !operationType.equals(OperationType.INCOME.toString()))) {
            throw new OperationIncorrectException("Incorrect operation type in relation to its amount. " +
                    "Acceptable: " + OperationType.EXPENSE + " - for negative values; " + OperationType.INCOME
                    + " - for the positive.");
        }

        wallet.setBalance(wallet.getBalance() + operationRequestDto.getAmount());
        walletRepository.save(wallet);

        Operation newOperation = operationMapper.operationRequestDtoToOperation(operationRequestDto);
        newOperation.setWallet(wallet);
        newOperation.setCategory(category);

        return operationMapper.operationToOperationResponseDto(operationRepository.saveAndFlush(newOperation));
    }
}
