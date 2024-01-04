package com.talaini.craftwood.serviceImp;

import com.talaini.craftwood.config.JpaConfig;
import com.talaini.craftwood.entity.Article;
import com.talaini.craftwood.service.I_Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import javax.persistence.EntityNotFoundException;
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
        assertNotNull(result);
        assertEquals(testArticle.getId_article(), result.getId_article());
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/test_article_data.csv", numLinesToSkip = 1)
     void ajouterArticleTestCsv(String libelle, String categorie, double prix, int stock) {

        Article newTestArticle=new Article.ArticleBuilder()
                .setLibelle(libelle)
                .setCategorie(categorie)
                .setPrix(prix)
                .setStock(stock)
                .build();
        Article result = articleDAO.ajouterArticle(newTestArticle);
        assertNotNull(result);
        assertNotNull(libelle);
        assertNotNull(categorie);
        assertNotNull(prix);
        assertNotNull(stock);

             if (newTestArticle != null && newTestArticle.getId_article() != 0) {
            articleDAO.supprimeArticle(newTestArticle.getId_article());
        }

    }



    @Test
    void modifierArticle() {
        Article result = articleDAO.modifierArticle(testArticle);
        assertNotNull(result);
        assertEquals(testArticle.getId_article(), result.getId_article());

    }

    @Test
    void afficherArticleAvecId() {
        Article result = articleDAO.afficherArticleAvecId(articleDAO.ajouterArticle(testArticle).getId_article());
        assertNotNull(result, "L'article récupéré ne devrait pas être nul");
    }

    @Test
    void afficherArticles() {
        List<Article> articles = articleDAO.afficherArticles();
        assertNotNull(articles, "La liste d'articles ne devrait pas être nulle");
    }

    @Test
    void supprimeArticle() {

        Article addedArticle = articleDAO.ajouterArticle(testArticle);
        assertNotNull(addedArticle, "L'ajout de l'article a échoué");

        // Obtient l'ID de l'article ajouté
        int articleIdToDelete = addedArticle.getId_article();

        // Supprime l'article
        articleDAO.supprimeArticle(articleIdToDelete);

        // Tente de récupérer l'article supprimé
        assertThrows(EntityNotFoundException.class, () -> articleDAO.afficherArticleAvecId(articleIdToDelete),
                "Une EntityNotFoundException devrait être lancée car l'article a été supprimé");
    }
    @AfterEach
    void afterEach() {
        System.out.println("deleteing after each");
        if (testArticle != null && testArticle.getId_article() != 0) {
            articleDAO.supprimeArticle(testArticle.getId_article());
        }

    }



}
