package com.example.demo.com.controller;

import com.example.demo.com.pojo.Actorpurview;
import com.example.demo.com.service.ActorpurviewService;
import com.example.demo.com.util.PageResult;
import com.example.demo.com.util.Result;
import com.example.demo.com.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * actorpurview控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/actorpurview")
public class ActorpurviewController {

	@Autowired
	private ActorpurviewService actorpurviewService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true, StatusCode.OK,"查询成功",actorpurviewService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param actorpurviewid ID
	 * @return
	 */
	@RequestMapping(value="/{actorpurviewid}",method= RequestMethod.GET)
	public Result findById(@PathVariable Integer actorpurviewid){
		return new Result(true,StatusCode.OK,"查询成功",actorpurviewService.findById(actorpurviewid));
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
		Page<Actorpurview> pageList = actorpurviewService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Actorpurview>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",actorpurviewService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param actorpurview
	 */
	@RequestMapping(method= RequestMethod.POST)
	public Result add(@RequestBody Actorpurview actorpurview  ){
		actorpurviewService.add(actorpurview);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param actorpurview
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Actorpurview actorpurview, @PathVariable Integer actorpurviewid ){
		actorpurview.setActorpurviewid(actorpurviewid);
		actorpurviewService.update(actorpurview);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param actorpurviewid
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable Integer actorpurviewid){
		actorpurviewService.deleteById(actorpurviewid);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
