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

    @Autowired
    ClientDAO clientDAO;
    Client client;
    Article article;
    Commande commande;
    String s;
    Date date;
    
    @BeforeEach
    void setUp() {
        date =new Date();
        System.out.println("calling the before each");
        client=new Client.ClientBuilder().setNom("mohammed").setPrenom("talaini").setTel("06412585325").setAdresse("casablanca").build();
        article =new Article.ArticleBuilder().setLibelle("article2").setCategorie("cat2")
                .setPrix(500).setStock(50).build();
        commande=new Commande.CommandeBuilder().setclient(client).setEtat("ENATTENTE")
                .setcreated_at(date).setupdated_at(date).setTotal(500).build();
        article=articleDAO.ajouterArticle(article);
        client=clientDAO.ajouterClient(client);
        s="[{'id_article':"+article.getId_article()+",'libelle':'"+article.getLibelle()+"','categorie':'"+article.getCategorie()+"','prix':"+article.getPrix()+",'stock':"+article.getStock()+",'qty':1}]";

    }

    @AfterEach
    void tearDown() {
        System.out.println("calling the after each");
        if (client != null && client.getId_client() != 0) {
            clientDAO.supprimeClient(client.getId_client());
        }
        
        if (article != null && article.getId_article() != 0) {
            articleDAO.supprimeArticle(article.getId_article());
        }
    }

    @Test
    void ajouterCommande() {
        assertNotNull(commandeDAO.ajouterCommande(commande,s));
    }

    @Test
    void afficherCommandeAvecId() {
        Commande cmd=commandeDAO.ajouterCommande(commande,s);
        assertNotNull(commandeDAO.afficherCommandeAvecId(cmd.getId_commande()));
    }

    @Test
    void afficherCommandes() {
        Commande cmd=commandeDAO.ajouterCommande(commande,s);
        List result=commandeDAO.afficherCommandes();
        assertNotNull(result);
    }

    @Test
    void afficherInfosCommande() {
        Commande cmd=commandeDAO.ajouterCommande(commande,s);
        List result=commandeDAO.afficherInfosCommande(cmd.getId_commande());
        assertNotNull(result);
    }

    @Test
    void supprimeCommandes() {
        Commande cmd=commandeDAO.ajouterCommande(commande,s);
        assertTrue(commandeDAO.supprimeCommandes(cmd.getId_commande()));
    }
}
