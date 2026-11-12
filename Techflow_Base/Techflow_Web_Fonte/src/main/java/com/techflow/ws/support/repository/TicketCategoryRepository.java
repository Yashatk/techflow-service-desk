package com.techflow.ws.support.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techflow.ws.support.entity.TicketCategory;

public interface TicketCategoryRepository extends JpaRepository<TicketCategory, Integer>{

    public boolean existsByCategoryIgnoreCase(String category);

    public Optional<TicketCategory> findByCategoryIgnoreCase(String category);

}
