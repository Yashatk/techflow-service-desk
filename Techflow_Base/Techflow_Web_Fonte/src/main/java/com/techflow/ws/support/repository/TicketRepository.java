package com.techflow.ws.support.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techflow.ws.support.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findByCreateDateBetween(Instant start, Instant end);

}
