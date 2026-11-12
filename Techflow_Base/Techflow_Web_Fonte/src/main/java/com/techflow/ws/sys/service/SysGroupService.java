package com.techflow.ws.sys.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techflow.ws.sys.converter.SysGroupConverter;
import com.techflow.ws.sys.domain.ServiceResponse;
import com.techflow.ws.sys.dto.SysGroupDTO;
import com.techflow.ws.sys.entity.SysGroup;
import com.techflow.ws.sys.repository.SysGroupRepository;

@Service
public class SysGroupService {

    @Autowired
    private SysGroupRepository sysGroupRepository;

    private static Logger log = LogManager.getLogger(SysGroupService.class);

    public ServiceResponse<List<SysGroupDTO>> findBy() {
        ServiceResponse<List<SysGroupDTO>> resp = new ServiceResponse<>();
        try {
            List<SysGroup> list = sysGroupRepository.findAll();
            resp.setObj(SysGroupConverter.convertToDTO(list, true, true));
            resp.setOK();
        }
        catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            resp.setMessage(ex.getMessage());
        }
        return resp;
    }

    public ServiceResponse<SysGroupDTO> create(SysGroupDTO groupDTO) {
        ServiceResponse<SysGroupDTO> resp = new ServiceResponse<>();
        try {
            if(sysGroupRepository.existsByGroupNameIgnoreCase(groupDTO.getGroupName())) {
                resp.setMessage("Group name already exists.");
                return resp;
            }
            SysGroup group = SysGroupConverter.convertTo(groupDTO);
            group = sysGroupRepository.save(group);
            resp.setObj(SysGroupConverter.convertToDTO(group, true, true));
            resp.setOK();
        }
        catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            resp.setMessage(ex.getMessage());
        }
        return resp;
    }

    public ServiceResponse<SysGroupDTO> update(Integer id, SysGroupDTO dto) {
        ServiceResponse<SysGroupDTO> resp = new ServiceResponse<>();
        try {
            SysGroup group = sysGroupRepository.findById(id)
                    .orElse(null);
            if(group == null) {
                resp.setMessage("Group not found.");
                return resp;
            }
            group.setGroupName(dto.getGroupName());
            group.setDescription(dto.getDescription());
            group = sysGroupRepository.save(group);
            resp.setObj(SysGroupConverter.convertToDTO(group, true, true));
            resp.setOK();
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
            SysGroup group = sysGroupRepository.findById(id).orElse(null);
            if(group == null) {
                resp.setMessage("Group not found.");
                return resp;
            }
            sysGroupRepository.deleteById(id);
            resp.setObj("Group deleted successfully");
            resp.setOK();
        }
        catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            resp.setMessage(ex.getMessage());
        }
        return resp;
    }

}
