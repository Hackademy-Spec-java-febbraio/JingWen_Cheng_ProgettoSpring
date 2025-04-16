package it.aulab.xjava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.aulab.xjava.dtos.ArticleDto;
import it.aulab.xjava.models.Article;
import it.aulab.xjava.models.Category;
import it.aulab.xjava.models.User;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByCategory(Category category);
    List<Article> findByUser(User user);
}
