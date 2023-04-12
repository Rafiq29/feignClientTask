package org.herb.feignclienttask.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationResponseDTO {
    private String curr;
    private double amount;
    private LocalDateTime dateTime;
}
