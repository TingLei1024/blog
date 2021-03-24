package com.bs.blog.service;

import com.bs.blog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    Tag saveTag(Tag tag);//保存

    Tag getTag(Long id);//查询

    Tag getTagByName(String name);

    Page<Tag> listTag(Pageable pageable);//分页

    List<Tag> listTag();

    List<Tag> listTagTop(Integer size);

    List<Tag> listTag(String ids);

    Tag updateTag(Long id, Tag tag);//修改

    void deleteTag(Long id);//删除

}
