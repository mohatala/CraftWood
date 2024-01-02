package com.talaini.craftwood.serviceImp;
import java.util.List;


import com.talaini.craftwood.config.JpaConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talaini.craftwood.entity.Article;
import com.talaini.craftwood.service.I_Article;
import com.talaini.craftwood.repository.ArticleRepository;

@Service
public class ArticleDAO implements I_Article{

	@Autowired
	private ArticleRepository articleRepository;
    Logger log = Logger.getLogger(CommandeDAO.class.getName());
	@Override
	@Transactional
	public Article ajouterArticle(Article c) {
		 // Ajouter Article
		Article ar=articleRepository.save(c);
		log.debug("Article Ajouter"+ar.getId_article());
		return this.afficherArticleAvecId(ar.getId_article());
	}
	@Override
	@Transactional
	public Article modifierArticle(Article c) {
		 // Modifier Article
		Article ar=articleRepository.save(c);
		articleRepository.flush();
		log.debug("Article Modifie"+ar.getId_article());
		   return this.afficherArticleAvecId(c.getId_article());
	}
	
	@Override
	@Transactional
	public Article afficherArticleAvecId(int id){
		//Afficher Les Article Par id article
		System.out.println("hello");
		System.out.println(id);
		 Article ar=articleRepository.findById(id).get();
			log.debug("Article afficher avec id="+id);

		return ar; 
	}
	
	@Override
	@Transactional
	public List afficherArticles(){
		//Afficher tous Les Articles

		List articlesList=articleRepository.findAll();
			log.debug("afficher list des Articles");

		return articlesList; 
	}
	
	@Override
	@Transactional
	public boolean supprimeArticle(int id) {
			//Supprimer Article
        	articleRepository.deleteById(id);
        	if(!articleRepository.existsById(id)) {
        		log.debug("Article Supprimer");
     		   return true;
     	   }
     		return false;
	}
}
