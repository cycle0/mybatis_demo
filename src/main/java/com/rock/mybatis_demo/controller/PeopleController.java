package com.rock.mybatis_demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rock.mybatis_demo.model.People;
import com.rock.mybatis_demo.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/people")
public class PeopleController {

	@Autowired
	private PeopleService peopleService;


	@GetMapping("all")
	public Object pageVideo(){

		return peopleService.findAll();
	}


	@GetMapping("find_by_id")
	public Object findById(long peopleId){

		return peopleService.findById(peopleId);
	}


	@DeleteMapping("del_by_id")
	public Object delById(int peopleId){

		return peopleService.delete(peopleId);
	}


	@PutMapping("update_by_id")
	public Object update(long peopleId, String name){

		People people = new People();
		people.setId(peopleId);
		people.setName(name);
		return peopleService.update(people);
	}

	@PostMapping("save")
	public Object save(String name){

		People people = new People();
		people.setName(name);
		return peopleService.save(people);
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

		PageHelper.startPage(page,size);

		List<People> list = peopleService.findAll();
		PageInfo<People> pageInfo = new PageInfo<>(list);
		Map<String,Object> data = new HashMap<>();
		data.put("total_size",pageInfo.getTotal());//总条数
		data.put("total_page",pageInfo.getPages());//总页数
		data.put("current_page",page);//当前页
		data.put("data",pageInfo.getList());//数据

		return data;
	}

}
