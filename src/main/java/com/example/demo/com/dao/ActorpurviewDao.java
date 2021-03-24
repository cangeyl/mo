package com.example.demo.com.dao;

import com.example.demo.com.pojo.Actorpurview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * actorpurview数据访问接口
 * @author Administrator
 *
 */
public interface ActorpurviewDao extends JpaRepository<Actorpurview,Integer>, JpaSpecificationExecutor<Actorpurview> {
	
}
