package com.thegroup.business_management_api.repository;

import com.thegroup.business_management_api.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

}