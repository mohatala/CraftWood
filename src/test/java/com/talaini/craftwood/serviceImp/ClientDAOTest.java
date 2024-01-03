package com.talaini.craftwood.serviceImp;

import com.talaini.craftwood.config.JpaConfig;
import com.talaini.craftwood.entity.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ContextConfiguration(classes = {JpaConfig.class})
@ExtendWith(SpringExtension.class)
class ClientDAOTest {

    @Autowired
    ClientDAO clientDAO;
    @Test
    void ajouterClient() {
        Client c=new Client.ClientBuilder().setNom("hi").setAdresse("adr").setTel("45454").setPrenom("qwerty").build();
        Client c1=clientDAO.ajouterClient(c);
        System.out.println(c1);
    }

    @Test
    void modifierClient() {
    }

    @Test
    void afficherClientAvecId() {
    }

    @Test
    void afficherClients() {
        System.out.println("rajaeeeeee");
    }

    @Test
    void supprimeClient() {
    }
}