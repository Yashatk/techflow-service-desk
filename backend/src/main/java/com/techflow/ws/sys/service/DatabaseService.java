package com.techflow.ws.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.techflow.ws.sys.domain.ServiceResponse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;

@Service
public class DatabaseService {

	private static final Logger log = LogManager.getLogger(DatabaseService.class);
	
	@PersistenceContext
	private EntityManager em;
	
	public static List<String> columns(List<Tuple> tuples) {
		List<String> list = new ArrayList<>();
		try {
			if (!tuples.isEmpty()) {
	            for (TupleElement<?> column : tuples.get(0).getElements()) {
	                list.add(column.getAlias());
	            }
			}	
		}
		catch(Exception ex) {
			log.error(ex.getMessage());
		}
		
		return list;
	}

	@SuppressWarnings("unchecked")
	public ServiceResponse<List<Tuple>> nativeQuery(String sql) {
		ServiceResponse<List<Tuple>> resp = new ServiceResponse<>();

		try {
			Query query = em.createNativeQuery(sql, Tuple.class);
			if(!sql.trim().toLowerCase().startsWith("insert") && !sql.trim().toLowerCase().startsWith("update")  && !sql.trim().toLowerCase().startsWith("delete")) {
				List<Tuple> resultList = query.getResultList();
				resp.setObj(resultList);		
			}
			else {
				query.getResultList();
			}
			resp.setOK();
		}
		catch(Exception ex) {
			resp.setMessage(ex.getMessage());
			log.error(ex.getMessage());
		}
		
		return resp;
	}

	@SuppressWarnings("unchecked")
	public Integer executeQuery(String sql) {
		try {
			Query query = em.createNativeQuery(sql, Tuple.class);
			return query.executeUpdate();
		}
		catch(Exception ex) {
			log.error(ex.getMessage());
			return 0;
		}
	}
	
}