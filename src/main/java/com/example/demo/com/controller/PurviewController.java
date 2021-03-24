package com.example.demo.com.controller;

import com.example.demo.com.pojo.Purview;
import com.example.demo.com.service.PurviewService;
import com.example.demo.com.util.PageResult;
import com.example.demo.com.util.Result;
import com.example.demo.com.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * purview控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/purview")
public class PurviewController {

	@Autowired
	private PurviewService purviewService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping("/findAll")
	public Result findAll(){
		return new Result(true, StatusCode.OK,"查询成功",purviewService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param purviewid ID
	 * @return
	 */
	@RequestMapping(value="/{purviewid}",method= RequestMethod.GET)
	public Result findById(@PathVariable Integer purviewid){
		return new Result(true,StatusCode.OK,"查询成功",purviewService.findById(purviewid));
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
		Page<Purview> pageList = purviewService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Purview>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",purviewService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param purview
	 */
	@RequestMapping(method= RequestMethod.POST)
	public Result add(@RequestBody Purview purview  ){
		purviewService.add(purview);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param purview
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Purview purview, @PathVariable Integer purviewid ){
		purview.setPurviewid(purviewid);
		purviewService.update(purview);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param purviewid
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable Integer purviewid){
		purviewService.deleteById(purviewid);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
