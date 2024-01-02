package com.talaini.craftwood.serviceImp;

import com.sun.corba.se.spi.presentation.rmi.StubAdapter;
import com.talaini.craftwood.entity.Article;
import com.talaini.craftwood.repository.ArticleRepository;
import com.talaini.craftwood.service.I_Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ArticleDAOTest {
    @Autowired
    private I_Article articleDAO;

    @Autowired
    private ArticleRepository articleRepository;
    private Article testArticle;


    @BeforeEach
    public void setup(){
        System.out.println("calling the before each");
        testArticle = new Article.ArticleBuilder()
                .setLibelle("Article de test")
                .setCategorie("TestCategory")
                .setPrix(10.0)
                .setStock(50)
                .build();
    }

    @Test
    void ajouterArticle() {
        System.out.println("running add test");
        Article result = articleDAO.ajouterArticle(testArticle);
        System.out.println(result);
      assertNotNull(result);


        
    }

    @Test
    void modifierArticle() {
    }

    @Test
    void afficherArticleAvecId() {
    }

    @Test
    void afficherArticles() {
    }

    @Test
    void supprimeArticle() {
    }
}