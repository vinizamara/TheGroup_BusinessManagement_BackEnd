package com.thegroup.business_management_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.thegroup.business_management_api.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
