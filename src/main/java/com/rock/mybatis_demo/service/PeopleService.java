package com.rock.mybatis_demo.service;

import com.rock.mybatis_demo.model.People;

import java.util.List;

/**
 * 业务类接口
 */
public interface PeopleService {

    List<People> findAll();

    People findById(long id);

    int update(People Video);

    int delete(long id);

    int save(People video);

}
