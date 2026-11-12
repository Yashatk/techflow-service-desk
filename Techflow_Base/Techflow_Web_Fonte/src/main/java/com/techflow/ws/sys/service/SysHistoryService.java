package com.techflow.ws.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techflow.ws.sys.converter.SysHistoryConverter;
import com.techflow.ws.sys.domain.ServiceResponse;
import com.techflow.ws.sys.domain.Utils;
import com.techflow.ws.sys.dto.IdValueDTO;
import com.techflow.ws.sys.dto.SysHistoryDTO;
import com.techflow.ws.sys.entity.SysHistory;
import com.techflow.ws.sys.entity.SysHistoryFilter;
import com.techflow.ws.sys.entity.SysHistoryTypeEnum;
import com.techflow.ws.sys.repository.SysHistoryRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class SysHistoryService {

    @Autowired
    private SysHistoryRepository sysHistoryRepository;

    @Autowired
    private EntityManager entityManager;

    private static Logger log = LogManager.getLogger(SysHistoryService.class);

    List<SysHistory> findBy(SysHistoryFilter filter) {
        List<SysHistory> list = new ArrayList<>();

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<SysHistory> criteriaQuery = criteriaBuilder.createQuery(SysHistory.class);
			Root<SysHistory> root = criteriaQuery.from(SysHistory.class);
			Predicate conditions = criteriaBuilder.and();

			if (filter.getHistoryTypeId() != null) {
				conditions = criteriaBuilder.and(conditions, criteriaBuilder.equal(root.get("historyTypeId"), filter.getHistoryTypeId()));
            }
            if(filter.getKey() != null) {
                String searchKey = "%" + filter.getKey().toLowerCase() + "%";
                Predicate keyConditionDescription = criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), searchKey);
                conditions = criteriaBuilder.and(criteriaBuilder.or(keyConditionDescription), conditions);
            }

            if (filter.getStartDate() != null) {
                conditions = criteriaBuilder.and(conditions, criteriaBuilder.greaterThanOrEqualTo(root.get("createDate"), filter.getStartDate()));               
            }
            if (filter.getEndDate() != null) {
                conditions = criteriaBuilder.and(conditions, criteriaBuilder.lessThanOrEqualTo(root.get("createDate"), filter.getEndDate()));
            }
			criteriaQuery.where(conditions).orderBy(criteriaBuilder.desc(root.get("createDate")));

			if(filter.getPage() != null && filter.getPageSize() != null && filter.getPage() > 0 && filter.getPageSize() > 0) {
				list = entityManager.createQuery(criteriaQuery)
						.setFirstResult((filter.getPage()-1) * filter.getPageSize())
						.setMaxResults(filter.getPageSize())
						.getResultList();
			}
			else {
				list = entityManager.createQuery(criteriaQuery).getResultList();
            }
        }
        catch(Exception e) {
            log.error("Erro ao buscar histórico", e);
        }

        return list;
    }

    public ServiceResponse<List<SysHistoryDTO>> findBy(String historyTypeId, String key, String startDate, String endDate, Integer page, Integer pageSize) {
        ServiceResponse< List<SysHistoryDTO>> resp = new ServiceResponse<>();
        try {
            SysHistoryFilter filter = new SysHistoryFilter();
            filter.setHistoryTypeId(historyTypeId);
            filter.setKey(key);
            filter.setStartDate(Utils.parseToInstant(startDate,"yyyy-MM-dd'T'HH:mm:ss"));
            filter.setEndDate(Utils.parseToInstant(endDate, "yyyy-MM-dd'T'HH:mm:ss"));
            filter.setPage(page != null && page > 0 ? page : 0);
            filter.setPageSize(pageSize != null && pageSize > 0 ? pageSize : 100);

            List<SysHistory> historyList = findBy(filter);
            resp.setObj(SysHistoryConverter.convertToDTO(historyList));
            resp.setOK();
        }
        catch (Exception e) {
            resp.setMessage("Erro ao buscar histórico: " + e.getMessage());
            log.error("Erro ao buscar histórico", e);
        }        
        return resp;
    }

    public ServiceResponse<List<IdValueDTO>> findTypeBy() {
        ServiceResponse<List<IdValueDTO>> resp = new ServiceResponse<>();
        List<IdValueDTO> list = new ArrayList<>();
        try {
            for(SysHistoryTypeEnum item : SysHistoryTypeEnum.values()) {
                list.add(new IdValueDTO(item.name(), item.name()));
            }
            resp.setOK();
        }
        catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            resp.setMessage(ex.getMessage());
        }
        resp.setObj(list);
        return resp;
    }
}
