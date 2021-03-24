package com.bs.blog.dao;

import com.bs.blog.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type,Long> {

    Type findByName(String name);

    @Query(value = "select * from type t order by (select count(b.type_id) from blog b where t.id=b.type_id) desc",nativeQuery = true)
    List<Type> findTop(Pageable pageable);

}
