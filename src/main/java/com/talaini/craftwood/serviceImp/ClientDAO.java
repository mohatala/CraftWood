package com.talaini.craftwood.serviceImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.talaini.craftwood.entity.Commande;
import com.talaini.craftwood.repository.CommandeRepository;
import com.talaini.craftwood.service.I_Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talaini.craftwood.entity.Client;
import com.talaini.craftwood.repository.ClientRepository;
import com.talaini.craftwood.service.I_Client;

import javax.persistence.EntityNotFoundException;

@Service
public class ClientDAO implements I_Client{
	
    @Autowired
    private ClientRepository clientRepository;

	@Autowired
	CommandeRepository commandeRepository;

	@Autowired
	I_Commande cmdDao;

	@Override
	@Transactional
	public Client ajouterClient(Client c) {
		 // Ajouter Client
		Client cl=clientRepository.saveAndFlush(c);
		
		return this.afficherClientAvecId(cl.getId_client());
	}
	
	@Override
	@Transactional
	public Client modifierClient(Client c) {
		 // Modifier Client
		Client cl=clientRepository.saveAndFlush(c);
		return this.afficherClientAvecId(c.getId_client());
	}
	
	@Override
	@Transactional
	public Client afficherClientAvecId(int id){
		//Afficher Les information de client par id client
		 	Client cl=clientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
			return cl;
    }

	@Override
	@Transactional
	public List<Client> afficherClients(){
		//Afficher tous les clients
			List<Client> clients=clientRepository.findAll();
		return clients;
	}
	
	@Override
	@Transactional
	public boolean supprimeClient(int id) {
		//Supprimer le client et ses commandes
		Client client=clientRepository.findById(id).get();
		Commande cmd=commandeRepository.findByClient(client);
		if(cmd!=null && cmdDao.supprimeCommandes(cmd.getId_commande())){
			clientRepository.delete(client);
			return true;
		}else {
			clientRepository.delete(client);
			return true;
		}
	}
}
