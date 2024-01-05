package com.talaini.craftwood.serviceImp;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talaini.craftwood.entity.Article;
import com.talaini.craftwood.service.I_Article;
import com.talaini.craftwood.repository.ArticleRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class ArticleDAO implements I_Article{

	@Autowired
	private ArticleRepository articleRepository;
	@Override
	@Transactional
	public Article ajouterArticle(Article c) {
		 // Ajouter Article
		Article ar=articleRepository.save(c);
		return this.afficherArticleAvecId(ar.getId_article());
	}
	@Override
	@Transactional
	public Article modifierArticle(Article c) {
		 // Modifier Article
		Article ar=articleRepository.save(c);
		articleRepository.flush();
		   return this.afficherArticleAvecId(c.getId_article());
	}
	
	@Override
	@Transactional
	public Article afficherArticleAvecId(int id){
		//Afficher Les Article Par id article
		 Article ar=articleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		return ar; 
	}
	
	@Override
	@Transactional
	public List afficherArticles(){
		//Afficher tous Les Articles

		List articlesList=articleRepository.findAll();

		return articlesList; 
	}
	
	@Override
	@Transactional
	public boolean supprimeArticle(int id) {
			//Supprimer Article
        	articleRepository.deleteById(id);
        	if(!articleRepository.existsById(id)) {
     		   return true;
     	   }
     		return false;
	}
}
