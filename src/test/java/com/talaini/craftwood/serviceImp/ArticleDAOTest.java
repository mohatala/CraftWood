package com.talaini.craftwood.serviceImp;

import com.talaini.craftwood.config.JpaConfig;
import com.talaini.craftwood.entity.Article;
import com.talaini.craftwood.service.I_Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {JpaConfig.class})
@ExtendWith(SpringExtension.class)
class ArticleDAOTest {

    @Autowired
    ArticleDAO articleDAO;
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
    void ajouterArticleTest() {
        System.out.println("running add test");
        Article result = articleDAO.ajouterArticle(testArticle);
        System.out.println(result);
        assertNotNull(result);
        assertEquals(testArticle.getId_article(), result.getId_article());
    }


    @Test
    void modifierArticle() {
        Article result = articleDAO.modifierArticle(testArticle);
        assertNotNull(result);
        assertEquals(testArticle.getId_article(), result.getId_article());

    }

    @Test
    void afficherArticleAvecId() {

    }

    @Test
    void afficherArticles() {
        List l=articleDAO.afficherArticles();
        assertNotNull(l);
    }


}