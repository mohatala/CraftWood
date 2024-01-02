package com.talaini.craftwood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talaini.craftwood.entity.Article;

@Repository("articleRepository")
public interface ArticleRepository extends JpaRepository<Article, Integer>{

}
