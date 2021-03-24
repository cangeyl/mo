package com.example.demo.com.dao;

import com.example.demo.com.pojo.Usersactor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * usersactor数据访问接口
 * @author Administrator
 *
 */
public interface UsersactorDao extends JpaRepository<Usersactor,Integer>, JpaSpecificationExecutor<Usersactor> {
	
}
