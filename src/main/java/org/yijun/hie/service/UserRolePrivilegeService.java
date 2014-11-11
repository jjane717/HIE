package org.yijun.hie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yijun.hie.persistence.entity.PrivilegeEntity;
import org.yijun.hie.persistence.entity.UserAccountEntity;
import org.yijun.hie.persistence.repository.UserRepository;

import java.util.List;

/**
 * Created by liuyijun on 14-11-11.
 */

@Service
public class UserRolePrivilegeService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<PrivilegeEntity> getParticularPrivileges (UserAccountEntity userAccountEntity) {
        return userRepository.getPrivilegeByRole(userAccountEntity);
    }
}
