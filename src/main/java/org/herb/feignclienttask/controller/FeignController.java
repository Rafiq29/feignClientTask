package org.herb.feignclienttask.controller;

import lombok.RequiredArgsConstructor;
import org.herb.feignclienttask.dto.request.OperationRequestDTO;
import org.herb.feignclienttask.dto.response.OperationResponseDTO;
import org.herb.feignclienttask.service.OperationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FeignController {
    private final OperationService service;

    @GetMapping("/convert")
    public double convert(OperationRequestDTO requestDTO) {
        return service.convert(requestDTO);
    }

    @GetMapping("/history")
    public List<OperationResponseDTO> history() {
        return service.history();
    }
}
