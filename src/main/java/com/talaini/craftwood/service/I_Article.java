package com.talaini.craftwood.service;

import java.util.List;

import com.talaini.craftwood.entity.Article;

public interface I_Article {
	
	public Article ajouterArticle(Article c);
	public Article modifierArticle(Article c);
	public Article afficherArticleAvecId(int id);
	public List afficherArticles();
	public boolean supprimeArticle(int id);

}
