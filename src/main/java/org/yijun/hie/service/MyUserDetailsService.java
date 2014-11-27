package org.yijun.hie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yijun.hie.persistence.entity.UserAccountEntity;
import org.yijun.hie.persistence.repository.UserRepository;

import java.util.Collection;

/**
 * Created by liuyijun on 14-11-26.
 */
@Service
public class MyUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserAccountEntity userAccountEntity;
        userAccountEntity = userRepository.getUserByName(username);
        if (userAccountEntity == null){
            throw new UsernameNotFoundException("Cloud not find user " + username);
        }
        return new UserRepositoryUserDetails(userAccountEntity);
    }

    private final static class UserRepositoryUserDetails extends UserAccountEntity implements UserDetails{

        //private UserAccountEntity userAccountEntity;

        private String role;

        private UserRepositoryUserDetails(UserAccountEntity userAccountEntity){
            super();
            setIdUserAccount(userAccountEntity.getIdUserAccount());
            setPassword(userAccountEntity.getPassword());
            setStatus(userAccountEntity.getStatus());
            setUserName(userAccountEntity.getUserName());
            setFirstName(userAccountEntity.getFirstName());
            setLastName(userAccountEntity.getLastName());
            setDateOfBirth(userAccountEntity.getDateOfBirth());
            setAge(userAccountEntity.getAge());
            setIncomeStatus(userAccountEntity.getIncomeStatus());
            setIsFamily(userAccountEntity.getIsFamily());
            setIsSmallBusiness(userAccountEntity.getIsSmallBusiness());
            setStreet(userAccountEntity.getStreet());
            setCity(userAccountEntity.getCity());
            setState(userAccountEntity.getState());
            setZip(userAccountEntity.getZip());
            setPhone(userAccountEntity.getPhone());
            setEmail(userAccountEntity.getEmail());
            setEnterpriseEntity(userAccountEntity.getEnterpriseEntity());
            setRoleEntity(userAccountEntity.getRoleEntity());
            setOrderEntityList(userAccountEntity.getOrderEntityList());
            this.role = userAccountEntity.getRoleEntity().getRoleName();
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities(){
            return AuthorityUtils.createAuthorityList(role);
        }

//        @Override
//        public String getPassword(){
//            return getPassword();
//        }

        @Override
        public String getUsername(){
            return getUserName();
        }

        @Override
        public boolean isAccountNonExpired(){
            return true;
        }

        @Override
        public boolean isAccountNonLocked(){
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired(){
            return true;
        }

        @Override
        public boolean isEnabled(){
            return getStatus();
        }
    }
}
