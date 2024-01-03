package com.talaini.craftwood.serviceImp;

import com.talaini.craftwood.config.JpaConfig;
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


    @BeforeEach
    void setUp() {
        System.out.println("calling the before each");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void ajouterCommande() {
        Client client=new Client.ClientBuilder().setId_client(5).setNom("mohammed").build();
        Date date =new Date();
        Commande commande=new Commande.CommandeBuilder().setclient(client).setEtat("ENATTENTE")
                .setcreated_at(date).setupdated_at(date).setTotal(500).build();
        String s="[{'id_article':6,'libelle':'article2','categorie':'cat2','prix':500,'stock':50,'qty':1}]";
        assertNotNull(commandeDAO.ajouterCommande(commande,s));
    }

    @Test
    void afficherCommandeAvecId() {
        assertNotNull(commandeDAO.afficherCommandeAvecId(14));
    }

    @Test
    void afficherCommandes() {
        List result=commandeDAO.afficherCommandes();
        assertNotNull(result);

    }

    @Test
    void afficherInfosCommande() {

        List result=commandeDAO.afficherInfosCommande(14);
        assertNotNull(result);
    }

    @Test
    void supprimeCommandes() {
        assertTrue(commandeDAO.supprimeCommandes(5));
    }
}