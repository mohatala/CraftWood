package com.talaini.craftwood.serviceImp;

import com.talaini.craftwood.config.JpaConfig;
import com.talaini.craftwood.entity.Client;
import com.talaini.craftwood.entity.Commande;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

    }

    @Test
    void afficherCommandeAvecId() {
    }

    @Test
    void afficherCommandes() {
        List result=commandeDAO.afficherCommandes();
        assertNotNull(result);

    }

    @Test
    void afficherInfosCommande() {
    }

    @Test
    void supprimeCommandes() {
        assertTrue(commandeDAO.supprimeCommandes(5));
    }
}