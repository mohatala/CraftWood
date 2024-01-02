package com.talaini.craftwood.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.talaini.craftwood.service.I_Client;
import com.talaini.craftwood.entity.Client;
@Controller
@RequestMapping("/client")
public class ClientController {
	ModelAndView mv =new ModelAndView();
	
	@Autowired
    private I_Client cliDao;
	
	@GetMapping("/")
	public String listclients(Model md) {
		//System.out.println(cliDao.afficherClients());
		md.addAttribute("data",cliDao.afficherClients());
		return "list_cli";
	}
	@GetMapping("/Add")
	public String addclient(Model md) {
		return "Client";
	}
	
	
	@GetMapping("/Update/{id}")
	public String updateclient(Model md,@PathVariable int id) {
		md.addAttribute("mod", "mod");
		md.addAttribute("clientfr",cliDao.afficherClientAvecId(id));
		return  "modclient";
	}
	@GetMapping("/Delete/{id}")
	public String deleteclient(Model md,@PathVariable int id) {
		if(cliDao.supprimeClient(id)) {
			md.addAttribute("data",cliDao.afficherClientAvecId(id));
		}
		return "redirect:/client/";
	}
	@PostMapping("/Addop")
	public String postaddclient(HttpServletRequest req) {
		String op=req.getParameter("op");
		String nom=req.getParameter("nom");
		String prenom=req.getParameter("prenom");
		String tel=req.getParameter("tel");
		String adresse=req.getParameter("adresse");
		Client cl=new Client.ClientBuilder().setNom(nom).setPrenom(prenom).setTel(tel).setAdresse(adresse).build();
	
		if(cliDao.ajouterClient(cl)!=null) {
				return "redirect:/client/";
		}else {
			System.out.println("Ajout client pas reussi");
		}
		
		
		return null;		
	}
	
	@PostMapping("/Modop" )
	public String postmodclient(@ModelAttribute Client client,Model model) {
		/*String op=req.getParameter("op");
		String id=req.getParameter("id");
		String nom=req.getParameter("nom");
		String prenom=req.getParameter("prenom");
		String tel=req.getParameter("tel");
		String adresse=req.getParameter("adresse");
		Client cl=new Client.ClientBuilder().setId_client(Integer.parseInt(id)).setNom(nom).setPrenom(prenom).setTel(tel).setAdresse(adresse).build();
		if(cliDao.modifierClient(cl)!=null) {
			return new ModelAndView("redirect:/client/");
		}else {
			System.out.println("Ajout client pas reussi");
		}*/
		System.out.println(client.toString());
		return null;	
	}
}

