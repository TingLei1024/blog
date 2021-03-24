package com.bs.blog.service;

import com.bs.blog.po.User;

public interface UserService {

    User checkUser(String name,String password);
}
