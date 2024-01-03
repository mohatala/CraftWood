package com.talaini.craftwood.serviceImp;


import com.talaini.craftwood.config.JpaConfig;
import com.talaini.craftwood.entity.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@ContextConfiguration(classes = {JpaConfig.class})
@ExtendWith(SpringExtension.class)
class ClientDAOTest {

    @Autowired
    ClientDAO clientDAO;
    private Client client;


    @BeforeEach
    public void setup(){
        System.out.println("calling the before each");
        client = new Client.ClientBuilder()
                .setNom("test")
                .setPrenom("test")
                .setAdresse("casablanca")
                .setTel("0123456789")
                .build();
    }


    @Test
    void ajouterClient() {
        System.out.println("running add test");
        Client result = clientDAO.ajouterClient(client);
        System.out.println(result);
        assertNotNull(result);
        assertEquals(client.getId_client(), result.getId_client());
    }

    @Test
    void modifierClient() {
        Client result = clientDAO.modifierClient(client);
        assertNotNull(result);
        assertEquals(client.getId_client(), result.getId_client());
    }

    @Test
    void afficherClientAvecId() {
        int  clientId = client.getId_client();
        Client result = clientDAO.afficherClientAvecId(2);
        assertNotNull(result, "le client récupéré ne devrait pas être nul");
    }

    @Test
    void afficherClients() {

        List<Client> clients = clientDAO.afficherClients();

        assertNotNull(clients, "La liste des clients ne devrait pas être nulle");

    }

    @Test
    void supprimerClient() {

        Client deleteClient = new Client.ClientBuilder()
                .setNom("test")
                .setPrenom("test")
                .setAdresse("casablanca")
                .setTel("0123456789")
                .build();


        Client addClient = clientDAO.ajouterClient(deleteClient);
        assertNotNull(addClient, "L'ajout du client a échoué");

        int id = addClient.getId_client();


        clientDAO.supprimeClient(id);

        assertThrows(EntityNotFoundException.class, () -> clientDAO.afficherClientAvecId(id),
                "Une EntityNotFoundException devrait être lancée car le client a été supprimé");

    }
}