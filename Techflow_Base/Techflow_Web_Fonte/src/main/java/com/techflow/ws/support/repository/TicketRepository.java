package com.techflow.ws.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techflow.ws.support.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
