package com.bs.blog.service;

import com.bs.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    Type saveType(Type type);//保存

    Type getType(Long id);//查询

    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable);//分页

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    Type updateType(Long id,Type type);//修改

    void deleteType(Long id);//删除

}
