package com.example.demo.com.dao;


import com.example.demo.com.pojo.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * actor数据访问接口
 * @author Administrator
 *
 */
public interface ActorDao extends JpaRepository<Actor,Integer>, JpaSpecificationExecutor<Actor> {
	
}
