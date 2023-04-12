package org.herb.feignclienttask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "operations")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String curr_date;
    private String curr;
    private double amount;
    private double convrt_amount;
    private double value;
    private LocalDateTime dateTime = LocalDateTime.now();
}

