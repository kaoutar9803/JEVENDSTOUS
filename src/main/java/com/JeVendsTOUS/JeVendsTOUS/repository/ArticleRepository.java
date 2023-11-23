package com.JeVendsTOUS.JeVendsTOUS.repository;

import com.JeVendsTOUS.JeVendsTOUS.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
