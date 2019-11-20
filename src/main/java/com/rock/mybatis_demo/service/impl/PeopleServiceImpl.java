package com.rock.mybatis_demo.service.impl;

import com.rock.mybatis_demo.mapper.PeopleMapper;
import com.rock.mybatis_demo.model.People;
import com.rock.mybatis_demo.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleMapper peopleMapper;


    @Override
    public List<People> findAll() {
        return peopleMapper.findAll();
    }

    @Override
    public People findById(long id) {
        return peopleMapper.findById(id);
    }

    @Override
    public int update(People video) {
       return peopleMapper.update(video);
    }

    @Override
    public int delete(long id) {
       return peopleMapper.delete(id);
    }

    @Override
    public int save(People people) {
        int rows = peopleMapper.save(people);
        System.out.println("保存对象的id= "+people.getId());

        return rows;
    }
}
