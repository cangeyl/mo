package com.example.demo.com.service;

import com.example.demo.com.dao.UsersDao;
import com.example.demo.com.dao.qwe;
import com.example.demo.com.pojo.Users;
import com.example.demo.com.pojo.wer;
import com.example.demo.com.util.IdWorker;
import com.example.demo.com.util.Result;
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
import java.util.Optional;

/**
 * users服务层
 * 
 * @author Administrator
 *
 */
@Service
public class UsersService {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private qwe qw;

	@Autowired
	private IdWorker idWorker;



	/**
	 * 查询全部列表
	 * @return
	 */
	public List<wer> findAll() {
		return qw.find();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Users> findSearch(Map whereMap, int page, int size) {
		Specification<Users> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return usersDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Users> findSearch(Map whereMap) {
		Specification<Users> specification = createSpecification(whereMap);
		return usersDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param usersid
	 * @return
	 */
	public Users findById(Integer usersid) {
		return usersDao.findById(usersid).get();
	}

	/**
	 * 增加
	 * @param users
	 */
	public void add(Users users) {
		// users.setUsersid( idWorker.nextId()+"" ); 雪花分布式ID生成器
		usersDao.save(users);
	}

	/**
	 * 修改
	 * @param users
	 */
	public void update(Users users) {
		usersDao.save(users);
	}

	/**
	 * 删除
	 * @param usersid
	 */
	public void deleteById(Integer usersid) {
		usersDao.deleteById(usersid);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Users> createSpecification(Map searchMap) {

		return new Specification<Users>() {

			@Override
			public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // username
                if (searchMap.get("username")!=null && !"".equals(searchMap.get("username"))) {
                	predicateList.add(cb.like(root.get("username").as(String.class), "%"+(String)searchMap.get("username")+"%"));
                }
                // password
                if (searchMap.get("password")!=null && !"".equals(searchMap.get("password"))) {
                	predicateList.add(cb.like(root.get("password").as(String.class), "%"+(String)searchMap.get("password")+"%"));
                }
                // truename
                if (searchMap.get("truename")!=null && !"".equals(searchMap.get("truename"))) {
                	predicateList.add(cb.like(root.get("truename").as(String.class), "%"+(String)searchMap.get("truename")+"%"));
                }
                // email
                if (searchMap.get("email")!=null && !"".equals(searchMap.get("email"))) {
                	predicateList.add(cb.like(root.get("email").as(String.class), "%"+(String)searchMap.get("email")+"%"));
                }
                // phone
                if (searchMap.get("phone")!=null && !"".equals(searchMap.get("phone"))) {
                	predicateList.add(cb.like(root.get("phone").as(String.class), "%"+(String)searchMap.get("phone")+"%"));
                }
                // comments
                if (searchMap.get("comments")!=null && !"".equals(searchMap.get("comments"))) {
                	predicateList.add(cb.like(root.get("comments").as(String.class), "%"+(String)searchMap.get("comments")+"%"));
                }
                // 0 刚注册             1 正常             2 异常             3 锁定             ...
                if (searchMap.get("state")!=null && !"".equals(searchMap.get("state"))) {
                	predicateList.add(cb.like(root.get("state").as(String.class), "%"+(String)searchMap.get("state")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

	public Users find(String name) {
		Users byUsername = usersDao.findByUsername(name);
		return byUsername;
	}

	public wer findById2(String id) {
		wer byId2 = qw.findById2(id);
		return byId2;
	}
}
