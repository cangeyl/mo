package com.example.demo.com.dao;


import com.example.demo.com.pojo.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * clazz数据访问接口
 * @author Administrator
 *
 */
public interface ClazzDao extends JpaRepository<Clazz,Integer>, JpaSpecificationExecutor<Clazz> {
	
}
