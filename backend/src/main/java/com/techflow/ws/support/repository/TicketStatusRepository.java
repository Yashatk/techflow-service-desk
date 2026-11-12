package com.techflow.ws.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techflow.ws.support.entity.TicketStatus;

public interface TicketStatusRepository extends JpaRepository<TicketStatus, Integer> {

}
