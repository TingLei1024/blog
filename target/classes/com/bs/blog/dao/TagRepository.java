package com.bs.blog.dao;

import com.bs.blog.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {

    Tag findByName(String name);

    @Query(value = "select * from tag t order by (select count(b.tags_id) from blog_tags b where t.id=b.tags_id) desc",nativeQuery = true)
    List<Tag> findTop(Pageable pageable);

}
