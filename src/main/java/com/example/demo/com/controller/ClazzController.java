package com.example.demo.com.controller;

import com.example.demo.com.pojo.Clazz;
import com.example.demo.com.service.ClazzService;

import com.example.demo.com.util.PageResult;
import com.example.demo.com.util.Result;
import com.example.demo.com.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * clazz控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/clazz")
public class ClazzController {

	@Autowired
	private ClazzService clazzService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true, StatusCode.OK,"查询成功",clazzService.findAll());
	}


	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable Integer id){
		return new Result(true,StatusCode.OK,"查询成功",clazzService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method= RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Clazz> pageList = clazzService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Clazz>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",clazzService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param clazz
	 */
	@RequestMapping(method= RequestMethod.POST)
	public Result add(@RequestBody Clazz clazz  ){
		clazzService.add(clazz);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param clazz
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Clazz clazz, @PathVariable Integer id ){
		clazz.setId(id);
		clazzService.update(clazz);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable Integer id){
		clazzService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
