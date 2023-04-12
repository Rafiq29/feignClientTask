package org.herb.feignclienttask.repo;

import org.herb.feignclienttask.dto.request.OperationRequestDTO;
import org.herb.feignclienttask.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepo extends JpaRepository<Operation, Long> {
}
