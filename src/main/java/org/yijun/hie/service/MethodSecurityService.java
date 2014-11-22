package org.yijun.hie.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
* Created by liuyijun on 14-11-20.
*/
@Service
public class MethodSecurityService {

    public  interface MethodSercurityService{
        @PreAuthorize("hasRole(ROLE_USER)")
        String requiresUserRole();
    }

    public String requiresUserRole(){
        return "You have ROLE_USER";
    }
}
