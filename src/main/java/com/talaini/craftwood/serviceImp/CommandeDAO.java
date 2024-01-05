package com.talaini.craftwood.serviceImp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talaini.craftwood.DTO.CmdArtDTO;
import com.talaini.craftwood.DTO.CommandeDTO;
import com.talaini.craftwood.entity.Article;
import com.talaini.craftwood.entity.Commande;
import com.talaini.craftwood.entity.CommandeArticle;
import com.talaini.craftwood.service.I_Article;
import com.talaini.craftwood.service.I_Commande;
import com.talaini.craftwood.repository.ArticleRepository;
import com.talaini.craftwood.repository.CmdArticleRepository;
import com.talaini.craftwood.repository.CommandeRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class CommandeDAO implements I_Commande{

    @Autowired
    private CommandeRepository commandeRepository;
    
    @Autowired
    private CmdArticleRepository cmdartRepo;
    
    @Autowired
    private ArticleRepository articleRepository;
    

    private ModelMapper modelMapper=new ModelMapper();
    
    private I_Article artDao;
    LocalDate date = LocalDate.now();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    @Transactional
	public Commande ajouterCommande(Commande c,String listart) {
    	//Ajouter Une Commande

	            date.format(formatter);
                // Ajouter Commande
                Commande cmd=commandeRepository.saveAndFlush(c);
                        
    			JSONArray array = new JSONArray(listart);  
    			for(int i=0; i < array.length(); i++)   
    			{  
    			JSONObject object = array.getJSONObject(i);
    			int id=object.getInt("id_article");
    			Article art=articleRepository.findById(id).get();
    			//System.out.println(art);
    			CommandeArticle cmdart=new CommandeArticle(cmd,art,object.getInt("qty"));
    			cmdartRepo.saveAndFlush(cmdart);
    			}

                return cmd;

	}
	@Override
	@Transactional
	public Commande afficherCommandeAvecId(int id){
		//Afficher une commande avec id commande
		Commande cmd=commandeRepository.findById(id).get();
		return cmd;
	}
	@Override
	@Transactional
	public List afficherCommandes(){
		//afficher tous les commandes
		List<Commande> commandesList=commandeRepository.findAll();
		List<CommandeDTO> listcmd=new ArrayList<>();
		for (Commande commande : commandesList) {
			Commande cmd=new Commande.CommandeBuilder()
														.setId_commande(commande.getId_commande())
														.setclient(commande.getclient())
														.setEtat(commande.getEtat())
														.setcreated_at(commande.getcreated_at())
														.setupdated_at(commande.getupdated_at())
														.build();
			CommandeDTO cmdDTO = this.modelMapper.map(cmd, CommandeDTO.class);
			listcmd.add(cmdDTO);
		}
		return listcmd;
	}
	
	@Override
	@Transactional
	public List<CmdArtDTO> afficherInfosCommande(int id){
		//afficher les informations des commandes
		ArrayList<String> commandesList=new ArrayList<>();
		Commande commande=this.afficherCommandeAvecId(id);
		Commande cmd=new Commande.CommandeBuilder().setId_commande(id).build();
		List<CommandeArticle> commandes_articles=cmdartRepo.findBycommande(cmd);
		//System.out.println(commandes_articles);
		List<CmdArtDTO> list_cmdartDTO=new ArrayList<CmdArtDTO>();
			for(CommandeArticle c:commandes_articles) {
				CmdArtDTO cad=new CmdArtDTO();
				cad.setId_commande(commande.getId_commande());
				cad.setClient(commande.getclient());
				cad.setCreated_at(commande.getcreated_at());
				cad.setEtat(commande.getEtat());
				cad.setTotal(commande.getTotal());
				cad.setArt(c.getArticle());
				cad.setQty(c.getQty());
				list_cmdartDTO.add(cad);
			}

		return list_cmdartDTO; 
	}
		
	@Override
	@Transactional
	public Commande modifieretat(int id,String etat) {
		//modifier l'etat de la commande
		 // Modifier Etat
		/*Commande cmd=this.afficherCommandeAvecId(id);
		ArticleDAO artdao =new ArticleDAO();
		List listart=this.afficherInfosCommande(id);
		//System.out.print(cmd.getEtat());
		Etat enumEtat = null;
		if(cmd.getEtat().equals(enumEtat.EnCours.name().toString())|| cmd.getEtat().equals(enumEtat.EnAttente.name().toString())) {
			if(etat.equals(enumEtat.Complete.name().toString())) {
				for(String std:listart) {
					String[] s=std.split(",");
					Article art=artdao.afficherArticleAvecId(Integer.parseInt(s[2]));
					int qty=art.getStock()-Integer.parseInt(s[3]);
					art=new Article.ArticleBuilder().setId_article(art.getId_article()).setLibelle(art.getLibelle()).setCategorie(art.getCategorie()).setPrix(art.getPrix()).setStock(qty).build();
					//System.out.print(art);
					artdao.modifierArticle(art);
				}
			}
		}
		if(cmd.getEtat().equals(enumEtat.Complete.name().toString())) {
			if(etat.equals(enumEtat.Annuler.name().toString())) {
				for(String std:listart) {
					String[] s=std.split(",");
					Article art=artdao.afficherArticleAvecId(Integer.parseInt(s[2]));
					int qty=art.getStock()+Integer.parseInt(s[3]);
					art=new Article.ArticleBuilder().setId_article(art.getId_article()).setLibelle(art.getLibelle()).setCategorie(art.getCategorie()).setPrix(art.getPrix()).setStock(qty).build();
					//System.out.print(art);
					artdao.modifierArticle(art);
				}
			}
		}
        date.format(formatter);
		 /*String insertQuery = "UPDATE commande set etat='"+etat+"',date_modification='"+date+"' WHERE id_commande="+id;
		 int check= sqloperation.ajouterSql(insertQuery,null);
		 if(check>0) {
			 log.debug("Etat commande modifier");
			   return this.afficherCommandeAvecId(id);
		   }*/
		  return null;
	}
	
	@Override
	@Transactional
	public boolean supprimeCommandes(int id) {
		//Delete from Table Commande Article
		Commande c=commandeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		cmdartRepo.deleteByCommande(c);
		if(!cmdartRepo.existsById(id)){
			commandeRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
}
