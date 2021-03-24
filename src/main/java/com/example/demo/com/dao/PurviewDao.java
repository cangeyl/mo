package com.example.demo.com.dao;


import com.example.demo.com.pojo.Purview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * purview数据访问接口
 * @author Administrator
 *
 */
public interface PurviewDao extends JpaRepository<Purview,Integer>, JpaSpecificationExecutor<Purview> {

    @Query(value="SELECT p.* FROM users u LEFT JOIN usersactor sru ON u.usersid= sru.usersid LEFT JOIN actor r ON sru.useractorid=r.actorid LEFT JOIN actorpurview spr ON spr.actorid=r.actorid LEFT JOIN purview p ON p.purviewid = spr.purviewid WHERE u.usersid=?1",nativeQuery = true)
    List<Purview> f(Integer usersid);
}
