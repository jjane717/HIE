package org.yijun.hie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.yijun.hie.persistence.entity.EnterpriseEntity;
import org.yijun.hie.service.EnterpriseService;

import java.util.List;

/**
 * Created by liuyijun on 14-11-18.
 */
@Controller
public class EnterpriseController {
    @Autowired
    private EnterpriseService enterpriseService;

    public List<EnterpriseEntity> getEnterpriseListForType(String type){
        return enterpriseService.getEnterpriseListForTypeFromService(type);
    }
}
