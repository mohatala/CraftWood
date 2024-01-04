package com.talaini.craftwood.serviceImp;

import com.talaini.craftwood.config.JpaConfig;
import com.talaini.craftwood.entity.Article;
import com.talaini.craftwood.entity.Client;
import com.talaini.craftwood.entity.Commande;
import com.talaini.craftwood.entity.CommandeArticle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ContextConfiguration(classes = {JpaConfig.class})
@ExtendWith(SpringExtension.class)
class CommandeDAOTest {

    @Autowired
    CommandeDAO commandeDAO;

    @Autowired
    ArticleDAO articleDAO;

    Client client;
    Article article;
    Date date;
    @BeforeEach
    void setUp() {
        System.out.println("calling the before each");
        client=new Client.ClientBuilder().setId_client(5).setNom("mohammed").build();
        article =new Article.ArticleBuilder().setLibelle("article2").setCategorie("cat2")
                .setPrix(500).setStock(50).build();
        date =new Date();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void ajouterCommande() {
        Article ar=articleDAO.ajouterArticle(article);
        Commande commande=new Commande.CommandeBuilder().setclient(client).setEtat("ENATTENTE")
                .setcreated_at(date).setupdated_at(date).setTotal(500).build();
        String s="[{'id_article':"+ar.getId_article()+",'libelle':'"+ar.getLibelle()+"','categorie':'"+ar.getCategorie()+"','prix':"+ar.getPrix()+",'stock':"+ar.getStock()+",'qty':1}]";
        assertNotNull(commandeDAO.ajouterCommande(commande,s));
    }

    @Test
    void afficherCommandeAvecId() {
        Article ar=articleDAO.ajouterArticle(article);
        Commande commande=new Commande.CommandeBuilder().setclient(client).setEtat("ENATTENTE")
                .setcreated_at(date).setupdated_at(date).setTotal(500).build();
        String s="[{'id_article':"+ar.getId_article()+",'libelle':'"+ar.getLibelle()+"','categorie':'"+ar.getCategorie()+"','prix':"+ar.getPrix()+",'stock':"+ar.getStock()+",'qty':1}]";
        Commande cmd=commandeDAO.ajouterCommande(commande,s);
        assertNotNull(commandeDAO.afficherCommandeAvecId(cmd.getId_commande()));
    }

    @Test
    void afficherCommandes() {
        List result=commandeDAO.afficherCommandes();
        assertNotNull(result);
    }

    @Test
    void afficherInfosCommande() {
        Article ar=articleDAO.ajouterArticle(article);
        Commande commande=new Commande.CommandeBuilder().setclient(client).setEtat("ENATTENTE")
                .setcreated_at(date).setupdated_at(date).setTotal(500).build();
        String s="[{'id_article':"+ar.getId_article()+",'libelle':'"+ar.getLibelle()+"','categorie':'"+ar.getCategorie()+"','prix':"+ar.getPrix()+",'stock':"+ar.getStock()+",'qty':1}]";
        Commande cmd=commandeDAO.ajouterCommande(commande,s);
        List result=commandeDAO.afficherInfosCommande(cmd.getId_commande());
        assertNotNull(result);
    }

    @Test
    void supprimeCommandes() {
        Article ar=articleDAO.ajouterArticle(article);
        Commande commande=new Commande.CommandeBuilder().setclient(client).setEtat("ENATTENTE")
                .setcreated_at(date).setupdated_at(date).setTotal(500).build();
        String s="[{'id_article':"+ar.getId_article()+",'libelle':'"+ar.getLibelle()+"','categorie':'"+ar.getCategorie()+"','prix':"+ar.getPrix()+",'stock':"+ar.getStock()+",'qty':1}]";
        Commande cmd=commandeDAO.ajouterCommande(commande,s);
        System.out.println(cmd.getId_commande());
        assertTrue(commandeDAO.supprimeCommandes(cmd.getId_commande()));
    }
}
