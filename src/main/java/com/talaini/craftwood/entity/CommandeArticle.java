package com.talaini.craftwood.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "commande_article")
public class CommandeArticle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_cmd_art;
	@ManyToOne
	@JoinColumn(name = "id_commande")
	private Commande commande;
	@ManyToOne
	@JoinColumn(name = "id_article")
	private Article article;
	
	private int qty;

	public CommandeArticle() {

	}
	public CommandeArticle(Commande commande, Article article, int qty) {
		super();
		this.commande = commande;
		this.article = article;
		this.qty = qty;
	}

	public int getId_cmd_art() {
		return id_cmd_art;
	}

	public void setId_cmd_art(int id_cmd_art) {
		this.id_cmd_art = id_cmd_art;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
	
}
