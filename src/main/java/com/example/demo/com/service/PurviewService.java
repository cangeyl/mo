package com.example.demo.com.service;

import com.example.demo.com.dao.PurviewDao;
import com.example.demo.com.pojo.Purview;
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
 * purview服务层
 * 
 * @author Administrator
 *
 */
@Service
public class PurviewService {

	@Autowired
	private PurviewDao purviewDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Purview> findAll() {
		return purviewDao.findAll();
	}

	public List<Purview> f(Integer id) {
		return purviewDao.f(id);
	}


	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Purview> findSearch(Map whereMap, int page, int size) {
		Specification<Purview> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return purviewDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Purview> findSearch(Map whereMap) {
		Specification<Purview> specification = createSpecification(whereMap);
		return purviewDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param purviewid
	 * @return
	 */
	public Purview findById(Integer purviewid) {
		return purviewDao.findById(purviewid).get();
	}

	/**
	 * 增加
	 * @param purview
	 */
	public void add(Purview purview) {
		// purview.setPurviewid( idWorker.nextId()+"" ); 雪花分布式ID生成器
		purviewDao.save(purview);
	}

	/**
	 * 修改
	 * @param purview
	 */
	public void update(Purview purview) {
		purviewDao.save(purview);
	}

	/**
	 * 删除
	 * @param purviewid
	 */
	public void deleteById(Integer purviewid) {
		purviewDao.deleteById(purviewid);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Purview> createSpecification(Map searchMap) {

		return new Specification<Purview>() {

			@Override
			public Predicate toPredicate(Root<Purview> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // purviewname
                if (searchMap.get("purviewname")!=null && !"".equals(searchMap.get("purviewname"))) {
                	predicateList.add(cb.like(root.get("purviewname").as(String.class), "%"+(String)searchMap.get("purviewname")+"%"));
                }
                // parentId
                if (searchMap.get("parentId")!=null && !"".equals(searchMap.get("parentId"))) {
                	predicateList.add(cb.like(root.get("parentId").as(String.class), "%"+(String)searchMap.get("parentId")+"%"));
                }
                // url
                if (searchMap.get("url")!=null && !"".equals(searchMap.get("url"))) {
                	predicateList.add(cb.like(root.get("url").as(String.class), "%"+(String)searchMap.get("url")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
