package com.techflow.ws.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techflow.ws.support.entity.TicketPriority;

public interface TicketPriorityRepository extends JpaRepository<TicketPriority, Integer> {

}
