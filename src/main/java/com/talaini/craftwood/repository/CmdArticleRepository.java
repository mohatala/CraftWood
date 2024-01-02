package com.talaini.craftwood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.talaini.craftwood.entity.Commande;
import com.talaini.craftwood.entity.CommandeArticle;

@Repository
public interface CmdArticleRepository extends JpaRepository<CommandeArticle, Integer>{
	public List<CommandeArticle> findBycommande(Commande commande);
}
