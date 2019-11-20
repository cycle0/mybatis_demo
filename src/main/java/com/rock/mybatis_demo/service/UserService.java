package com.rock.mybatis_demo.service;

import com.rock.mybatis_demo.model.User;

import java.util.List;

/**
 * 业务类接口
 */
public interface UserService {

    List<User> findAll();

    User findById(long id);

    int update(User user);

    int delete(long id);

    int save(User user);

    //用户登录认证

}
