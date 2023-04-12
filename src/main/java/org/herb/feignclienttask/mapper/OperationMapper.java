package org.herb.feignclienttask.mapper;

import org.herb.feignclienttask.dto.request.OperationRequestDTO;
import org.herb.feignclienttask.dto.response.OperationResponseDTO;
import org.herb.feignclienttask.entity.Operation;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OperationMapper {
    Operation toOperation(OperationRequestDTO requestDTO);
    OperationResponseDTO toDto(Operation operation);
}
