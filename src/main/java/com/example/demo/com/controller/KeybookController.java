package com.example.demo.com.controller;

import com.example.demo.com.pojo.Keybook;
import com.example.demo.com.service.KeybookService;
import com.example.demo.com.util.PageResult;
import com.example.demo.com.util.Result;
import com.example.demo.com.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * keybook控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/keybook")
public class KeybookController {

	@Autowired
	private KeybookService keybookService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true, StatusCode.OK,"查询成功",keybookService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param keyBookId ID
	 * @return
	 */
	@RequestMapping(value="/{keyBookId}",method= RequestMethod.GET)
	public Result findById(@PathVariable Integer keyBookId){
		return new Result(true,StatusCode.OK,"查询成功",keybookService.findById(keyBookId));
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
		Page<Keybook> pageList = keybookService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Keybook>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",keybookService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param keybook
	 */
	@RequestMapping(method= RequestMethod.POST)
	public Result add(@RequestBody Keybook keybook  ){
		keybookService.add(keybook);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param keybook
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Keybook keybook, @PathVariable Integer keyBookId ){
		keybook.setKeyBookId(keyBookId);
		keybookService.update(keybook);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param keyBookId
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable Integer keyBookId){
		keybookService.deleteById(keyBookId);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
