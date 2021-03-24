package com.example.demo.com.dao;

import com.example.demo.com.pojo.Users;
import com.example.demo.com.pojo.wer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * users数据访问接口
 * @author Administrator
 *
 */
public interface UsersDao extends JpaRepository<Users,Integer>, JpaSpecificationExecutor<Users> {



    Users findByUsername(String username);
}
