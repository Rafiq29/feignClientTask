package org.herb.feignclienttask.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationRequestDTO {
    private String curr_date;
    private String curr;
    private double amount;
}
