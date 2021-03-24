package com.example.demo.com.service;

import com.example.demo.com.dao.KeybookDao;
import com.example.demo.com.pojo.Keybook;
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
 * keybook服务层
 * 
 * @author Administrator
 *
 */
@Service
public class KeybookService {

	@Autowired
	private KeybookDao keybookDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Keybook> findAll() {
		return keybookDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Keybook> findSearch(Map whereMap, int page, int size) {
		Specification<Keybook> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return keybookDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Keybook> findSearch(Map whereMap) {
		Specification<Keybook> specification = createSpecification(whereMap);
		return keybookDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param keyBookId
	 * @return
	 */
	public Keybook findById(Integer keyBookId) {
		return keybookDao.findById(keyBookId).get();
	}

	/**
	 * 增加
	 * @param keybook
	 */
	public void add(Keybook keybook) {
		// keybook.setKeyBookId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		keybookDao.save(keybook);
	}

	/**
	 * 修改
	 * @param keybook
	 */
	public void update(Keybook keybook) {
		keybookDao.save(keybook);
	}

	/**
	 * 删除
	 * @param keyBookId
	 */
	public void deleteById(Integer keyBookId) {
		keybookDao.deleteById(keyBookId);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Keybook> createSpecification(Map searchMap) {

		return new Specification<Keybook>() {

			@Override
			public Predicate toPredicate(Root<Keybook> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // value
                if (searchMap.get("value")!=null && !"".equals(searchMap.get("value"))) {
                	predicateList.add(cb.like(root.get("value").as(String.class), "%"+(String)searchMap.get("value")+"%"));
                }
                // name
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                	predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                // type
                if (searchMap.get("type")!=null && !"".equals(searchMap.get("type"))) {
                	predicateList.add(cb.like(root.get("type").as(String.class), "%"+(String)searchMap.get("type")+"%"));
                }
                // typeName
                if (searchMap.get("typeName")!=null && !"".equals(searchMap.get("typeName"))) {
                	predicateList.add(cb.like(root.get("typeName").as(String.class), "%"+(String)searchMap.get("typeName")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
