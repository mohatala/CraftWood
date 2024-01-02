package com.talaini.craftwood.DTO;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.talaini.craftwood.entity.Article;
import com.talaini.craftwood.entity.Client;

public class CmdArtDTO {
	private int id_commande;
	private Client client;
	private String etat;
	private Date created_at;
	private Article article;
    private int qty;
    private double total;
	public int getId_commande() {
		return id_commande;
	}
	public void setId_commande(int id_commande) {
		this.id_commande = id_commande;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public String getEtat() {
		return etat;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Article getArt() {
		return article;
	}
	public void setArt(Article art) {
		this.article = art;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
    
}
