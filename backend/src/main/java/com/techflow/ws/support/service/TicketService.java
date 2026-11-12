package com.techflow.ws.support.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.techflow.ws.support.converter.TicketConverter;
import com.techflow.ws.support.converter.TicketDetailConverter;
import com.techflow.ws.support.dto.TicketDTO;
import com.techflow.ws.support.dto.TicketDetailDTO;
import com.techflow.ws.support.entity.Ticket;
import com.techflow.ws.support.entity.TicketCategory;
import com.techflow.ws.support.entity.TicketDetail;
import com.techflow.ws.support.entity.TicketFilter;
import com.techflow.ws.support.entity.TicketPriority;
import com.techflow.ws.support.entity.TicketStatus;
import com.techflow.ws.support.entity.TicketStatusEnum;
import com.techflow.ws.support.repository.TicketDetailRepository;
import com.techflow.ws.support.repository.TicketRepository;
import com.techflow.ws.sys.domain.ServiceResponse;
import com.techflow.ws.sys.domain.Utils;
import com.techflow.ws.sys.entity.SysUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private final TicketDetailRepository ticketDetailRepository;

    private final EntityManager entityManager;

    public TicketService(TicketRepository ticketRepository, TicketDetailRepository ticketDetailRepository, EntityManager entityManager) {
        this.ticketRepository = ticketRepository;
        this.ticketDetailRepository = ticketDetailRepository;
        this.entityManager = entityManager;
    }

    private static Logger log = LogManager.getLogger(TicketService.class);

    public ServiceResponse<List<TicketDTO>> findBy(Integer ticketId, 
    String key, Integer statusId, Integer categoryId, Integer priorityId, Integer userId, String startDate, String endDate, Integer page, Integer pageSize) {

        ServiceResponse<List<TicketDTO>> resp = new ServiceResponse<>();
        try {
            TicketFilter filter = new TicketFilter();
            filter.setTicketId(ticketId);
            filter.setKey(key);
            filter.setStatusId(statusId);
            filter.setCategoryId(categoryId);
            filter.setPriorityId(priorityId);
            filter.setUserId(userId);
            filter.setStartDate(Utils.parseToInstant(startDate, "yyyy-MM-dd'T'HH:mm:ss"));
            filter.setEndDate(Utils.parseToInstant(endDate, "yyyy-MM-dd'T'HH:mm:ss"));
            filter.setPage(page);
            filter.setPageSize(pageSize);

            ServiceResponse<List<Ticket>> respEntity = findBy(filter);
            List<TicketDTO> dtoList = respEntity.getObj() != null ? TicketConverter.convertToDTO(respEntity.getObj()) : null;
            resp.setObj(dtoList);
            resp.setOK();
        }
        catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            resp.setMessage(ex.getMessage());
        }
        return resp;
    }

    public ServiceResponse<List<Ticket>> findBy(TicketFilter filter) {
        ServiceResponse<List<Ticket>> resp = new ServiceResponse<>();
        List<Ticket> list = new ArrayList<>();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Ticket> criteriaQuery = criteriaBuilder.createQuery(Ticket.class);
			Root<Ticket> root = criteriaQuery.from(Ticket.class);
			Predicate conditions = criteriaBuilder.and();

			if (filter.getTicketId() != null) {
				conditions = criteriaBuilder.and(conditions, criteriaBuilder.equal(root.get("id"), filter.getTicketId()));
			}
			else {
				if (filter.getStatusId() != null) {
					conditions = criteriaBuilder.and(conditions, criteriaBuilder.equal(root.get("status").get("id"), filter.getStatusId()));
				}

				if (filter.getCategoryId() != null) {
					conditions = criteriaBuilder.and(conditions, criteriaBuilder.equal(root.get("category").get("id"), filter.getCategoryId()));
				}

				if (filter.getPriorityId() != null) {
					conditions = criteriaBuilder.and(conditions, criteriaBuilder.equal(root.get("priority").get("id"), filter.getPriorityId()));
				}

				if (filter.getUserId() != null) {
					conditions = criteriaBuilder.and(conditions, criteriaBuilder.equal(root.get("user").get("id"), filter.getUserId()));
				}

				if (filter.getKey() != null) {
					String searchKey = "%" + filter.getKey().toLowerCase() + "%";
					Predicate keyConditionSubject = criteriaBuilder.like(criteriaBuilder.lower(root.get("subject")), searchKey);
					Predicate keyConditionDescription = criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), searchKey);
					conditions = criteriaBuilder.and(criteriaBuilder.or(keyConditionSubject, keyConditionDescription), conditions);
				}

                if (filter.getStartDate() != null) {
                    conditions = criteriaBuilder.and(conditions, criteriaBuilder.greaterThanOrEqualTo(root.get("createDate"), filter.getStartDate()));               
                }
                if (filter.getEndDate() != null) {
                    conditions = criteriaBuilder.and(conditions, criteriaBuilder.lessThanOrEqualTo(root.get("createDate"), filter.getEndDate()));
                }

			}

			criteriaQuery.where(conditions).orderBy(criteriaBuilder.asc(root.get("createDate")));

			if(filter.getPage() != null && filter.getPageSize() != null && filter.getPage() > 0 && filter.getPageSize() > 0) {
				list = entityManager.createQuery(criteriaQuery)
						.setFirstResult((filter.getPage()-1) * filter.getPageSize())
						.setMaxResults(filter.getPageSize())
						.getResultList();
			}
			else {
				list = entityManager.createQuery(criteriaQuery).getResultList();
            }
            resp.setOK();
            
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            resp.setMessage(ex.getMessage());
        }
        resp.setObj(list);
        return resp;
    }

    public ServiceResponse<TicketDTO> update(Integer id, TicketDTO dto) {
        ServiceResponse<TicketDTO> resp = new ServiceResponse<>();
        try {
            Ticket ticket = ticketRepository.findById(id).orElse(null);
            if (ticket == null) {
                resp.setMessage("Ticket not found with id: " + id);
                return resp;
            }
            ticket.setSubject(dto.getSubject());
            ticket.setDescription(dto.getDescription());
            if(dto.getCategory() != null)
                ticket.setCategory(new TicketCategory(dto.getCategory().getId()));
            else
                ticket.setCategory(null);
            ticket.setPriority(new TicketPriority(dto.getPriority().getId()));
            ticket.setUser(new SysUser(dto.getUser().getId()));
            ticketRepository.save(ticket);
            resp.setObj(TicketConverter.convertToDTO(ticket));
            resp.setOK();
        }
        catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            resp.setMessage(ex.getMessage());
        }
        return resp;
    }

    public ServiceResponse<TicketDTO> create(TicketDTO ticket) {
        ServiceResponse<TicketDTO> resp = new ServiceResponse<>();
        try {
            if(ticket.getId() != null && ticket.getId() > 0) {
                Ticket existingTicket = ticketRepository.findById(ticket.getId()).orElse(null);
                if(existingTicket != null) {
                    existingTicket.setSubject(ticket.getSubject());
                    existingTicket.setDescription(ticket.getDescription());
                    if(ticket.getCategory() != null)
                        existingTicket.setCategory(new TicketCategory(ticket.getCategory().getId()));
                    if(ticket.getPriority() != null)
                        existingTicket.setPriority(new TicketPriority(ticket.getPriority().getId()));
                    existingTicket = ticketRepository.save(existingTicket);
                    resp.setObj(TicketConverter.convertToDTO(existingTicket));
                }
            }
            else {
                Ticket entity = TicketConverter.convertTo(ticket);
                entity = ticketRepository.save(entity);
                resp.setObj(TicketConverter.convertToDTO(entity));
                resp.setOK();
            }
        }
        catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            resp.setMessage(ex.getMessage());
        }
        return resp;
    }

    public ServiceResponse<TicketDTO> addDetail(TicketDetailDTO dto) {
        ServiceResponse<TicketDTO> resp = new ServiceResponse<>();
        try {
            Ticket ticket = ticketRepository.findById(dto.getTicketId()).orElse(null);
            if(ticket == null) {
                resp.setMessage("Ticket not found with id: " + dto.getTicketId());
                return resp;
            }
            TicketDetail detail = TicketDetailConverter.convertTo(dto);
            if(dto.getStatusId() == null || dto.getStatusId() <= 0) {
                detail.setStatus(new TicketStatus(TicketStatusEnum.ABERTO.getId()));
            }
            detail = ticketDetailRepository.save(detail);

            Boolean ticketChanged = false;
            if(dto.getStatusId() != null && dto.getStatusId() > 0) {
                // Altera somente se for atendimento
                if(!java.util.Objects.equals(ticket.getStatus().getId(), dto.getStatusId())) {
                    ticket.setStatus(new TicketStatus(dto.getStatusId()));
                    ticketChanged = true;
                }            
                // Ainda não tem usuário de suporte atribuído
                if(ticket.getSupportUser() == null || ticket.getSupportUser().getId() == null || !ticket.getSupportUser().getId().equals(dto.getUserId())) {
                    ticket.setSupportUser(new SysUser(dto.getUserId()));
                    ticketChanged = true;
                }
            }                      
            // Salva alterações no ticket
            if(ticketChanged) {
                ticket = ticketRepository.save(ticket);
            }            
            resp.setObj(TicketConverter.convertToDTO(ticket));
            resp.setOK();

        }
        catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            resp.setMessage(ex.getMessage());
        }
        return resp;
    }

    public ServiceResponse<TicketDTO> updateDetail(Integer id, TicketDetailDTO dto) {
        ServiceResponse<TicketDTO> resp = new ServiceResponse<>();
        try {
            TicketDetail detail = ticketDetailRepository.findById(id).orElse(null);
            if(detail == null) {
                resp.setMessage("Ticket detail not found with id: " + id);
                return resp;
            }
            detail.setDescription(dto.getDescription());
            ticketDetailRepository.save(detail);
            Ticket ticket = ticketRepository.findById(dto.getTicketId()).orElse(null);            
            resp.setObj(TicketConverter.convertToDTO(ticket));
            resp.setOK();
        }
        catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            resp.setMessage(ex.getMessage());
        }
        return resp;
    }

}
