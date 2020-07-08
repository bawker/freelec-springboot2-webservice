package com.bbaker.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
* 복잡한 SELECT경우 querydsl을 추천
*/
public interface PostsRepository extends JpaRepository <Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY id desc")
    List<Posts> findAllDesc();

}
