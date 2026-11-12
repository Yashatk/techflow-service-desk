package com.techflow.ws.support.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.techflow.ws.support.converter.TicketCategoryConverter;
import com.techflow.ws.support.dto.TicketCategoryDTO;
import com.techflow.ws.support.entity.TicketCategory;
import com.techflow.ws.support.repository.TicketCategoryRepository;
import com.techflow.ws.sys.domain.ServiceResponse;

import jakarta.annotation.PostConstruct;

@Service
public class TicketCategoryService {

    private final TicketCategoryRepository ticketCategoryRepository;

    public TicketCategoryService(TicketCategoryRepository ticketCategoryRepository) {
        this.ticketCategoryRepository = ticketCategoryRepository;
    }

    private static Logger log = LogManager.getLogger(TicketCategoryService.class);

    @PostConstruct
    public void init() {
        List<String> categories = new ArrayList<>();
        categories.add("Outros");
        categories.add("RH");
        categories.add("Financeiro");
        categories.add("Estoque");
        categories.add("Vendas");
        categories.add("Compras");
        categories.add("TI");

        for(String category : categories) {
            if(!ticketCategoryRepository.existsByCategoryIgnoreCase(category)) {
                ticketCategoryRepository.save(new TicketCategory(category));
            }
        }
    }

    public ServiceResponse<List<TicketCategoryDTO>> findBy() {
        ServiceResponse<List<TicketCategoryDTO>> resp = new ServiceResponse<>();
        try {
            List<TicketCategory> entities = ticketCategoryRepository.findAll();
            resp.setObj(TicketCategoryConverter.convertToDTO(entities));
            resp.setOK();            
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            resp.setMessage(ex.getMessage());
        }
        return resp;
    }

    public ServiceResponse<TicketCategoryDTO> create(TicketCategoryDTO dto) {
        ServiceResponse<TicketCategoryDTO> resp = new ServiceResponse<>();
        try {
            if(dto.getId() != null && dto.getId() > 0) {
                // Alteração
                TicketCategory existingCategory = ticketCategoryRepository.findById(dto.getId()).orElse(null);
                if(existingCategory != null) {
                    existingCategory.setCategory(dto.getCategory());
                    existingCategory = ticketCategoryRepository.save(existingCategory);
                    resp.setObj(TicketCategoryConverter.convertToDTO(existingCategory));
                    resp.setOK();
                }
                else {
                    resp.setMessage("Categoria não encontrada: " + dto.getId());
                }
            }
            else {
                if(!ticketCategoryRepository.existsByCategoryIgnoreCase(dto.getCategory())) {
                    TicketCategory category = TicketCategoryConverter.convertTo(dto);
                    category.setId(null);
                    category = ticketCategoryRepository.save(category);
                    resp.setObj(TicketCategoryConverter.convertToDTO(category));
                    resp.setOK();
                }
                else {
                    resp.setMessage("Categoria já cadastrada: " + dto.getCategory());
                }
            }
        }
        catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            resp.setMessage(ex.getMessage());
        }
        return resp;
    }

    public ServiceResponse<TicketCategoryDTO> update(Integer id, TicketCategoryDTO dto) {
        ServiceResponse<TicketCategoryDTO> resp = new ServiceResponse<>();
        try {
            Optional<TicketCategory> otherCategory = ticketCategoryRepository.findByCategoryIgnoreCase(dto.getCategory());
            if(otherCategory.isPresent() && !otherCategory.get().getId().equals(id)) {
                resp.setMessage("Categoria " + dto.getCategory() + " já cadastrada com outro ID: " + otherCategory.get().getId());
            }
            else {
                TicketCategory category = ticketCategoryRepository.findById(id).orElse(null);
                if(category != null) {
                    category.setCategory(dto.getCategory());
                    category = ticketCategoryRepository.save(category);
                    resp.setObj(TicketCategoryConverter.convertToDTO(category));
                    resp.setOK();
                }
                else {
                    resp.setMessage("Categoria não encontrada: " + id);
                }
            }
        }
        catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            resp.setMessage(ex.getMessage());
        }
        return resp;
    }

    public ServiceResponse<String> delete(Integer id) {
        ServiceResponse<String> resp = new ServiceResponse<>();
        try {
            TicketCategory category = ticketCategoryRepository.findById(id).orElse(null);
            if(category != null) {
                String categoryName = category.getCategory();
                ticketCategoryRepository.delete(category);
                resp.setObj("Categoria " + categoryName + " excluída com sucesso.");
                resp.setOK();
            }
            else {
                resp.setMessage("Categoria não encontrada: " + id);
            }
        }
        catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            resp.setMessage(ex.getMessage());
        }
        return resp;
    }
}

