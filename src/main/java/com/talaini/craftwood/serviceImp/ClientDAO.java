package com.talaini.craftwood.serviceImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talaini.craftwood.entity.Client;
import com.talaini.craftwood.repository.ClientRepository;
import com.talaini.craftwood.service.I_Client;

@Service
public class ClientDAO implements I_Client{
	
    @Autowired
    private ClientRepository clientRepository;
    
    static Logger log = Logger.getLogger(CommandeDAO.class.getName());  

	@Override
	@Transactional
	public Client ajouterClient(Client c) {
		 // Ajouter Client
		Client cl=clientRepository.saveAndFlush(c);
		
		log.debug("Client ajouter"+cl.getId_client());
		return this.afficherClientAvecId(cl.getId_client());
	}
	
	@Override
	@Transactional
	public Client modifierClient(Client c) {
		 // Modifier Client
		Client cl=clientRepository.saveAndFlush(c);
		log.debug("Client Modifier"+cl.getId_client());
		return this.afficherClientAvecId(c.getId_client());
	}
	
	@Override
	@Transactional
	public Client afficherClientAvecId(int id){
		//Afficher Les information de client par id client
		 	Client cl=clientRepository.findById(id).get();
			log.debug("Client afficher avec id"+id);
			return cl; 
	}

	@Override
	@Transactional
	public List<Client> afficherClients(){
		//Afficher tous les clients
			List<Client> clients=clientRepository.findAll();
			log.debug("afficher list Clients");
		return clients; 
	}
	
	@Override
	@Transactional
	public boolean supprimeClient(int id) {
		//Supprimer le client et ses commandes
		/*Commande cmd=null;
		CommandeDAO cmddao=new CommandeDAO();
		String qry="select id_commande from commande where id_client="+id;
		st=sqloperation.getSql(qry);
		try {
			while (st.next()) {
				cmd=new Commande.CommandeBuilder().setId_commande(st.getInt(1)).build();
				cmddao.supprimeCommandes(cmd.getId_commande());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String query = "DELETE FROM client WHERE id_client = "+id;
		int check= sqloperation.ajouterSql(query,null);
	   if(check>0) {
			log.debug("Client supprimer");
		   return true;
	   }*/
		return false;
	}
}
