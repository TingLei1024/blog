package com.bs.blog.dao;

import com.bs.blog.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> , JpaSpecificationExecutor<Blog> {

    @Query(value = "select * from Blog b where b.recommend = true",nativeQuery = true)
    List<Blog> findTop(Pageable pageable);

    @Query(value = "select * from Blog b where b.title like ?1 or b.content like ?1",nativeQuery = true)
    Page<Blog> findByQuery(String query,Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update Blog b set b.views = b.views+1 where b.id = ?1",nativeQuery = true)
    int updateViews(Long id);

    @Query(value="select DATE_FORMAT(updateTime,'%Y') as year from blog GROUP BY year ORDER BY year DESC;",nativeQuery = true)
    List<String> findGroupYear();

    @Query(value = "select * from blog where DATE_FORMAT(updateTime,'%Y') = ?1",nativeQuery = true)
    List<Blog> findByYear(String year);
}
