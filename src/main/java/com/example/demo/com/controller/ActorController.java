package com.example.demo.com.controller;

import com.example.demo.com.pojo.Actor;
import com.example.demo.com.service.ActorService;
import com.example.demo.com.util.PageResult;
import com.example.demo.com.util.Result;
import com.example.demo.com.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * actor控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/actor")
public class ActorController {

	@Autowired
	private ActorService actorService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping("/findAll")
	public Result findAll(){
		return new Result(true, StatusCode.OK,"查询成功",actorService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param actorid ID
	 * @return
	 */
	@RequestMapping(value="/{actorid}",method= RequestMethod.GET)
	public Result findById(@PathVariable Integer actorid){
		return new Result(true,StatusCode.OK,"查询成功",actorService.findById(actorid));
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
		Page<Actor> pageList = actorService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Actor>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",actorService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param actor
	 */
	@RequestMapping(method= RequestMethod.POST)
	public Result add(@RequestBody Actor actor  ){
		actorService.add(actor);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param actor
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Actor actor, @PathVariable Integer actorid ){
		actor.setActorid(actorid);
		actorService.update(actor);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param actorid
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable Integer actorid){
		actorService.deleteById(actorid);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
