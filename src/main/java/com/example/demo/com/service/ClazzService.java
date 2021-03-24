package com.example.demo.com.service;

import com.example.demo.com.dao.ClazzDao;
import com.example.demo.com.pojo.Clazz;
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
 * clazz服务层
 * 
 * @author Administrator
 *
 */
@Service
public class ClazzService {

	@Autowired
	private ClazzDao clazzDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Clazz> findAll() {
		return clazzDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Clazz> findSearch(Map whereMap, int page, int size) {
		Specification<Clazz> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return clazzDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Clazz> findSearch(Map whereMap) {
		Specification<Clazz> specification = createSpecification(whereMap);
		return clazzDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Clazz findById(Integer id) {
		return clazzDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param clazz
	 */
	public void add(Clazz clazz) {
		// clazz.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		clazzDao.save(clazz);
	}

	/**
	 * 修改
	 * @param clazz
	 */
	public void update(Clazz clazz) {
		clazzDao.save(clazz);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Integer id) {
		clazzDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Clazz> createSpecification(Map searchMap) {

		return new Specification<Clazz>() {

			@Override
			public Predicate toPredicate(Root<Clazz> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // name
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                	predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
