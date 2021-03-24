package com.example.demo.com.dao;

import com.example.demo.com.pojo.Users;
import com.example.demo.com.pojo.wer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface qwe extends JpaRepository<wer,String>, JpaSpecificationExecutor<wer> {
    @Query(value="SELECT users.username,actor.actorname,GROUP_CONCAT(purview.purviewname SEPARATOR ',') purview FROM users LEFT JOIN usersactor ON users.`usersid`=usersactor.`usersid` LEFT JOIN actor ON actor.actorid=usersactor.actorid LEFT JOIN actorpurview ON actor.actorid=actorpurview.actorid LEFT JOIN purview ON purview.purviewid=actorpurview.purviewid GROUP BY users.username,actor.actorname",nativeQuery = true)
    List<wer> find();

    @Query(value="SELECT c.actorid username,c.actorname,GROUP_CONCAT(a.purviewname SEPARATOR ',') purview FROM actor c LEFT JOIN actorpurview d ON c.actorid=d.actorid LEFT JOIN purview a ON a.purviewid=d.purviewid GROUP BY c.actorid,c.actorname",nativeQuery = true)
    List<wer> find2();

    @Query(value="SELECT users.username,actor.actorname,GROUP_CONCAT(purview.purviewname SEPARATOR ',') purview FROM users LEFT JOIN usersactor ON users.`usersid`=usersactor.`usersid` LEFT JOIN actor ON actor.actorid=usersactor.actorid LEFT JOIN actorpurview ON actor.actorid=actorpurview.actorid LEFT JOIN purview ON purview.purviewid=actorpurview.purviewid WHERE users.username=?1 GROUP BY users.username,actor.actorname",nativeQuery = true)
    wer findById2(String id);

    @Query(value="SELECT c.actorid username,c.actorname,GROUP_CONCAT(a.purviewname SEPARATOR ',') purview FROM actor c LEFT JOIN actorpurview d ON c.actorid=d.actorid LEFT JOIN purview a ON a.purviewid=d.purviewid WHERE users.username=?1 GROUP BY c.actorid,c.actorname",nativeQuery = true)
    wer findById22(String id);


}
