package com.example.demo.com.service;

import com.example.demo.com.dao.ActorDao;
import com.example.demo.com.dao.qwe;
import com.example.demo.com.pojo.Actor;
import com.example.demo.com.pojo.wer;
import com.example.demo.com.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * actor服务层
 * 
 * @author Administrator
 *
 */
@Service
public class ActorService {

	@Autowired
	private ActorDao actorDao;

	@Autowired
	private qwe we;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<wer> findAll() {
		return we.find2();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Actor> findSearch(Map whereMap, int page, int size) {
		Specification<Actor> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return actorDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Actor> findSearch(Map whereMap) {
		Specification<Actor> specification = createSpecification(whereMap);
		return actorDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param actorid
	 * @return
	 */
	public Actor findById(Integer actorid) {
		return actorDao.findById(actorid).get();
	}

	/**
	 * 增加
	 * @param actor
	 */
	public void add(Actor actor) {
		// actor.setActorid( idWorker.nextId()+"" ); 雪花分布式ID生成器
		actorDao.save(actor);
	}

	/**
	 * 修改
	 * @param actor
	 */
	public void update(Actor actor) {
		actorDao.save(actor);
	}

	/**
	 * 删除
	 * @param actorid
	 */
	public void deleteById(Integer actorid) {
		actorDao.deleteById(actorid);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Actor> createSpecification(Map searchMap) {

		return new Specification<Actor>() {

			@Override
			public Predicate toPredicate(Root<Actor> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // actorname
                if (searchMap.get("actorname")!=null && !"".equals(searchMap.get("actorname"))) {
                	predicateList.add(cb.like(root.get("actorname").as(String.class), "%"+(String)searchMap.get("actorname")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
