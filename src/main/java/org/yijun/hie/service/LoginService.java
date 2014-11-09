package org.yijun.hie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yijun.hie.persistence.entity.UserAccountEntity;
import org.yijun.hie.persistence.repository.UserRepository;

/**
 * Created by liuyijun on 14-11-8.
 */

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    public UserAccountEntity userLogin (String userName, String password) {
        return userRepository.getUserByName(userName).get(0);
    }
}
