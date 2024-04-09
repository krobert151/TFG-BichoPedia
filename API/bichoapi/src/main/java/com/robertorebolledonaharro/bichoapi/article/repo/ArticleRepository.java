package com.robertorebolledonaharro.bichoapi.article.repo;

import com.robertorebolledonaharro.bichoapi.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, UUID> {
}
