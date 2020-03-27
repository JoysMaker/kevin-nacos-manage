package com.kevin.usc.pay.persistent.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.kevin.usc.pay.persistent.po.TUser;


public interface TUserRepository extends JpaRepository<TUser, Long>,JpaSpecificationExecutor<TUser> {


     List<TUser> findByUserNameStartingWith(String userName);//like '%XXX'

     List<TUser> findByUserNameEndingWith(String userName);//like 'XXX%'

     List<TUser> findByUserNameContaining(String userName);//like '%XXX%'

}
