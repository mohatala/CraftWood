package com.talaini.craftwood.serviceImp;


import com.talaini.craftwood.config.JpaConfig;
import com.talaini.craftwood.entity.Article;
import com.talaini.craftwood.entity.Client;
import com.talaini.craftwood.entity.Commande;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@ContextConfiguration(classes = {JpaConfig.class})
@ExtendWith(SpringExtension.class)
class ClientDAOTest {

    @Autowired
    ClientDAO clientDAO;
    @Autowired
    CommandeDAO commandeDAO;
    @Autowired
    ArticleDAO articleDAO;
    private Client clientaajouter;
    @BeforeEach
    public void setup(){
        System.out.println("calling the before each");
        Client client = new Client.ClientBuilder()
                .setNom("test")
                .setPrenom("test")
                .setAdresse("casablanca")
                .setTel("0123456789")
                .build();
        clientaajouter = clientDAO.ajouterClient(client);
    }
    @AfterEach
    void tearDown() {
        System.out.println("calling the after each");
        if (clientaajouter != null && clientaajouter.getId_client() != 0) {
            clientDAO.supprimeClient(clientaajouter.getId_client());
        }
    }

    @Test
    void ajouterClient() {
        System.out.println("running add test");
        assertNotNull(clientaajouter);
    }

    @Test
    void modifierClient() {
        clientaajouter=new Client.ClientBuilder().setId_client(clientaajouter.getId_client())
                .setNom(clientaajouter.getNom()).setPrenom(clientaajouter.getPrenom())
                .setTel("02315258").setAdresse("Merrakech").build();
        Client result = clientDAO.modifierClient(clientaajouter);
        assertNotNull(result);
        assertEquals(clientaajouter.getId_client(), result.getId_client());
    }

    @Test
    void afficherClientAvecId() {
        int  clientId = clientaajouter.getId_client();
        Client result = clientDAO.afficherClientAvecId(clientId);
        assertNotNull(result, "le client récupéré ne devrait pas être nul");
    }

    @Test
    void afficherClients() {
        List<Client> clients = clientDAO.afficherClients();
        assertNotNull(clients, "La liste des clients ne devrait pas être nulle");
    }

    @Test
    void supprimerClient() {
        int id = clientaajouter.getId_client();
        clientDAO.supprimeClient(id);
        assertThrows(EntityNotFoundException.class, () -> clientDAO.afficherClientAvecId(id),
                "Une EntityNotFoundException devrait être lancée car le client a été supprimé");
        clientaajouter=null;
    }




















}