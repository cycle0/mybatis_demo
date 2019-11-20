package com.rock.mybatis_demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rock.mybatis_demo.model.User;
import com.rock.mybatis_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;


	@GetMapping("all")
	public Object pageVideo(){

		return userService.findAll();
	}


	@GetMapping("find_by_id")
	public Object findById(long userId){

		return userService.findById(userId);
	}


	@DeleteMapping("del_by_id")
	public Object delById(int userId){

		return userService.delete(userId);
	}

	/**
	 * 分页接口
	 * @param page 当前第几页，默认是第一页
	 * @param size  每页显示几条
	 * @return
	 */
	@GetMapping("page")
	public Object pageVideo(@RequestParam(value = "page",defaultValue = "1")int page,
							@RequestParam(value = "size",defaultValue = "10")int size){

		//release v0.1版本，修改测试bug


		PageHelper.startPage(page,size);

		List<User> list = userService.findAll();
		PageInfo<User> pageInfo = new PageInfo<>(list);
		Map<String,Object> data = new HashMap<>();
		data.put("total_size",pageInfo.getTotal());//总条数
		data.put("total_page",pageInfo.getPages());//总页数
		data.put("current_page",page);//当前页
		data.put("data",pageInfo.getList());//数据

		return data;
	}

}
