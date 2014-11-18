package org.yijun.hie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yijun.hie.persistence.entity.EnterpriseEntity;
import org.yijun.hie.persistence.repository.EnterpriseRepository;

import java.util.List;

/**
 * Created by liuyijun on 14-11-18.
 */
@Service
public class EnterpriseService {
    @Autowired
    private EnterpriseRepository enterpriseRepository;

    public List<EnterpriseEntity> getHIEEnterpriseListFromService(){
        return enterpriseRepository.getHIEEnterpriseListFromRepository();
    }
}
