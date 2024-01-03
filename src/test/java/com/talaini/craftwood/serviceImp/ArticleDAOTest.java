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
        int articleId = testArticle.getId_article();
        Article result = articleDAO.afficherArticleAvecId(5);
        assertNotNull(result, "L'article récupéré ne devrait pas être nul");
        // assertEquals(5, result.getId_article(), "L'ID de l'article ne correspond pas");

    }

    @Test
    void afficherArticles() {
        List<Article> articles = articleDAO.afficherArticles();
        assertNotNull(articles, "La liste d'articles ne devrait pas être nulle");
    }
    @Test
    void supprimeArticle() {

        // Ajoute un article à la base de données pour tester la suppression
        Article articleToDelete = new Article.ArticleBuilder()
                .setLibelle("Article à supprimer")
                .setCategorie("TestCategory")
                .setPrix(15.0)
                .setStock(30)
                .build();

        // Ajoute l'article à la base de données
        Article addedArticle = articleDAO.ajouterArticle(articleToDelete);
        assertNotNull(addedArticle, "L'ajout de l'article a échoué");

        // Obtient l'ID de l'article ajouté
        int articleIdToDelete = addedArticle.getId_article();

        // Supprime l'article
        articleDAO.supprimeArticle(articleIdToDelete);

        // Tente de récupérer l'article supprimé
        assertThrows(EntityNotFoundException.class, () -> articleDAO.afficherArticleAvecId(articleIdToDelete),
                "Une EntityNotFoundException devrait être lancée car l'article a été supprimé");
    }


}