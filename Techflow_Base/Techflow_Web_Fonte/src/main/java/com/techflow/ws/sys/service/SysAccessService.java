package com.techflow.ws.sys.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techflow.ws.sys.converter.SysAccessConverter;
import com.techflow.ws.sys.domain.ServiceResponse;
import com.techflow.ws.sys.dto.SysAccessDTO;
import com.techflow.ws.sys.entity.SysAccess;
import com.techflow.ws.sys.repository.SysAccessRepository;

@Service
public class SysAccessService {

    @Autowired
    private SysAccessRepository sysAccessRepository;

    private static Logger log = LogManager.getLogger(SysAccessService.class);

    public ServiceResponse<List<SysAccessDTO>> findBy() {
        ServiceResponse<List<SysAccessDTO>> resp = new ServiceResponse<>();
        try {
            List<SysAccess> list = sysAccessRepository.findAll();
            resp.setObj(SysAccessConverter.convertToDTO(list));
            resp.setOK();
        }
        catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            resp.setMessage(ex.getMessage());
        }
        return resp;
    }

}
