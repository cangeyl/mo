package com.example.demo.com.controller;

import com.example.demo.com.pojo.Usersactor;
import com.example.demo.com.service.UsersactorService;
import com.example.demo.com.util.PageResult;
import com.example.demo.com.util.Result;
import com.example.demo.com.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * usersactor控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/usersactor")
public class UsersactorController {

	@Autowired
	private UsersactorService usersactorService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true, StatusCode.OK,"查询成功",usersactorService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param useractorid ID
	 * @return
	 */
	@RequestMapping(value="/{useractorid}",method= RequestMethod.GET)
	public Result findById(@PathVariable Integer useractorid){
		return new Result(true,StatusCode.OK,"查询成功",usersactorService.findById(useractorid));
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
		Page<Usersactor> pageList = usersactorService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Usersactor>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",usersactorService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param usersactor
	 */
	@RequestMapping(method= RequestMethod.POST)
	public Result add(@RequestBody Usersactor usersactor  ){
		usersactorService.add(usersactor);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param usersactor
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Usersactor usersactor, @PathVariable Integer useractorid ){
		usersactor.setUseractorid(useractorid);
		usersactorService.update(usersactor);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param useractorid
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable Integer useractorid){
		usersactorService.deleteById(useractorid);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
