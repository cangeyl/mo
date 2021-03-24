package com.example.demo.com.service;

import com.example.demo.com.dao.ActorpurviewDao;
import com.example.demo.com.pojo.Actorpurview;
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
 * actorpurview服务层
 * 
 * @author Administrator
 *
 */
@Service
public class ActorpurviewService {

	@Autowired
	private ActorpurviewDao actorpurviewDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Actorpurview> findAll() {
		return actorpurviewDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Actorpurview> findSearch(Map whereMap, int page, int size) {
		Specification<Actorpurview> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return actorpurviewDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Actorpurview> findSearch(Map whereMap) {
		Specification<Actorpurview> specification = createSpecification(whereMap);
		return actorpurviewDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param actorpurviewid
	 * @return
	 */
	public Actorpurview findById(Integer actorpurviewid) {
		return actorpurviewDao.findById(actorpurviewid).get();
	}

	/**
	 * 增加
	 * @param actorpurview
	 */
	public void add(Actorpurview actorpurview) {
		// actorpurview.setActorpurviewid( idWorker.nextId()+"" ); 雪花分布式ID生成器
		actorpurviewDao.save(actorpurview);
	}

	/**
	 * 修改
	 * @param actorpurview
	 */
	public void update(Actorpurview actorpurview) {
		actorpurviewDao.save(actorpurview);
	}

	/**
	 * 删除
	 * @param actorpurviewid
	 */
	public void deleteById(Integer actorpurviewid) {
		actorpurviewDao.deleteById(actorpurviewid);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Actorpurview> createSpecification(Map searchMap) {

		return new Specification<Actorpurview>() {

			@Override
			public Predicate toPredicate(Root<Actorpurview> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
