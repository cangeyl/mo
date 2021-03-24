package com.example.demo.com.dao;


import com.example.demo.com.pojo.Keybook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * keybook数据访问接口
 * @author Administrator
 *
 */
public interface KeybookDao extends JpaRepository<Keybook,Integer>, JpaSpecificationExecutor<Keybook> {
	
}
