package com.rock.mybatis_demo.service.impl;

import com.rock.mybatis_demo.mapper.UserMapper;
import com.rock.mybatis_demo.model.User;
import com.rock.mybatis_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findById(long id) {
        return userMapper.findById(id);
    }

    @Override
    public int update(User video) {
       return userMapper.update(video);
    }

    @Override
    public int delete(long id) {
       return userMapper.delete(id);
    }

    @Override
    public int save(User user) {
        int rows = userMapper.save(user);
        System.out.println("保存对象的id= "+user.getId());

        return rows;
    }
}
