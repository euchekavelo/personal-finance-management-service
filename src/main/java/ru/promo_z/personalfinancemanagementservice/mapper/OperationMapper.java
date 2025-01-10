package ru.promo_z.personalfinancemanagementservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.promo_z.personalfinancemanagementservice.dto.request.OperationRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.OperationResponseDto;
import ru.promo_z.personalfinancemanagementservice.model.Operation;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OperationMapper {

    Operation operationRequestDtoToOperation(OperationRequestDto operationRequestDto);

    OperationResponseDto operationToOperationResponseDto(Operation operation);
}
