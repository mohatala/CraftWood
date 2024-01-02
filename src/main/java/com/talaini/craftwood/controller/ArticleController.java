package com.talaini.craftwood.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.talaini.craftwood.entity.Article;
import com.talaini.craftwood.service.I_Article;

@Controller
@RequestMapping("/article")
public class ArticleController {
	ModelAndView mv =new ModelAndView();
	//ClientDAO cliDao=new ClientDAO();
	
	@Autowired
    private I_Article artDao;
	
	@GetMapping("/")
	public String listarticles(Model md) {
		md.addAttribute("data",artDao.afficherArticles());
		return "list_art";
	}
	@GetMapping("/Add")
	public String addarticle(Model md) {
		return "article";
	}
	
	@GetMapping("/Update/{id}")
	public String updatearticle(Model md,@PathVariable int id) {
		md.addAttribute("mod", "mod");
		md.addAttribute("articledata",artDao.afficherArticleAvecId(id));
		return  "article";
	}
	@GetMapping("/Delete/{id}")
	public String deletearticle(Model md,@PathVariable int id) {
		if(artDao.supprimeArticle(id)) {
			md.addAttribute("data",artDao.afficherArticles());	
		}
		return "list_art";	
	}
	@PostMapping("/operation")
	public String postaddarticle(HttpServletRequest request) {
		String op=request.getParameter("op");
		String libelle=request.getParameter("libelle");
		String categorie=request.getParameter("categorie");
		String prix=request.getParameter("prix");
		String stock=request.getParameter("stock");
		if(op != null) {
			if(op.equals("ajouter")) {
				Article ar=new Article.ArticleBuilder().setLibelle(libelle).setCategorie(categorie).setPrix(Double.parseDouble(prix)).setStock(Integer.parseInt(stock)).build();
				if(artDao.ajouterArticle(ar)!=null) {
					return "redirect:/article/";
				}else {
					System.out.println("Ajout client pas reussi");

				}
			}else if(op.equals("modifier")) {
				/*Client cl=new Client.ClientBuilder().setId_client(Integer.parseInt(id)).setNom(nom).setPrenom(prenom).setTel(tel).setAdresse(adresse).build();
				if(cliDao.modifierClient(cl)!=null) {
					return "redirect:/client/";
				}else {
					System.out.println("Modification client pas reussi");
				}*/
			}
		}
		
		return null;		
	}

}
