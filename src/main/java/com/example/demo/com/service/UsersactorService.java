package com.example.demo.com.service;

import com.example.demo.com.dao.UsersactorDao;
import com.example.demo.com.pojo.Usersactor;
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
 * usersactor服务层
 * 
 * @author Administrator
 *
 */
@Service
public class UsersactorService {

	@Autowired
	private UsersactorDao usersactorDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Usersactor> findAll() {
		return usersactorDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Usersactor> findSearch(Map whereMap, int page, int size) {
		Specification<Usersactor> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return usersactorDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Usersactor> findSearch(Map whereMap) {
		Specification<Usersactor> specification = createSpecification(whereMap);
		return usersactorDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param useractorid
	 * @return
	 */
	public Usersactor findById(Integer useractorid) {
		return usersactorDao.findById(useractorid).get();
	}

	/**
	 * 增加
	 * @param usersactor
	 */
	public void add(Usersactor usersactor) {
		// usersactor.setUseractorid( idWorker.nextId()+"" ); 雪花分布式ID生成器
		usersactorDao.save(usersactor);
	}

	/**
	 * 修改
	 * @param usersactor
	 */
	public void update(Usersactor usersactor) {
		usersactorDao.save(usersactor);
	}

	/**
	 * 删除
	 * @param useractorid
	 */
	public void deleteById(Integer useractorid) {
		usersactorDao.deleteById(useractorid);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Usersactor> createSpecification(Map searchMap) {

		return new Specification<Usersactor>() {

			@Override
			public Predicate toPredicate(Root<Usersactor> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
