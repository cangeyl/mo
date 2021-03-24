package com.example.demo.com.controller;

import com.example.demo.com.dao.PurviewDao;
import com.example.demo.com.pojo.Purview;
import com.example.demo.com.pojo.Users;
import com.example.demo.com.pojo.wer;
import com.example.demo.com.service.PurviewService;
import com.example.demo.com.service.UsersService;
import com.example.demo.com.util.PageResult;
import com.example.demo.com.util.Result;
import com.example.demo.com.util.StatusCode;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * users控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService usersService;
	@Autowired
	private PurviewService purviewService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping("/findAll")
	public Result findAll(){
		return new Result(true, StatusCode.OK,"查询成功",usersService.findAll());
	}

	@RequestMapping("/findById")
	public wer findById(String id){
		wer byId2 = usersService.findById2(id);
		String purview = byId2.getPurview();


		String reg = "[^\u4e00-\u9fa5]";
		String replace = purview.replaceAll(reg, "");
		System.out.println(replace);

		String[] split = replace.split("");
		String a ="[";
		for (String s : split) {
			a+='"'+s+'"'+",";
		}
		String substring = a.substring(0, a.length() - 1);
		byId2.setPurview(substring+"]");
		return byId2;
	}

	@RequestMapping("/us")
	public Map us(){
		HashMap<String, Object> map = new HashMap<>();
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		Users users = usersService.find(name);
		List<Purview> f = purviewService.f(users.getUsersid());
		for (Purview purview : f) {
			if(purview.getPurviewname().equals("增")){
				map.put("z",1);
			}if(purview.getPurviewname().equals("删")){
				map.put("s",1);
			}if(purview.getPurviewname().equals("改")){
				map.put("x",1);
			}if(purview.getPurviewname().equals("导")){
				map.put("d",1);
			}if(purview.getPurviewname().equals("超")){
				map.put("c",1);
			}
		}
		map.put("name",users.getUsername());

		return map;
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
		Page<Users> pageList = usersService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Users>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",usersService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param users
	 */
	@RequestMapping(method= RequestMethod.POST)
	public Result add(@RequestBody Users users  ){
		usersService.add(users);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param users
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Users users, @PathVariable Integer usersid ){
		users.setUsersid(usersid);
		usersService.update(users);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param usersid
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable Integer usersid){
		usersService.deleteById(usersid);
		return new Result(true,StatusCode.OK,"删除成功");
	}


}
