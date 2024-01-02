package com.talaini.craftwood.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import com.talaini.craftwood.entity.*;
import com.talaini.craftwood.repository.CommandeRepository;
import com.talaini.craftwood.service.I_Commande;
import com.talaini.craftwood.serviceImp.ReportService;
import net.sf.jasperreports.engine.JRException;
import com.talaini.craftwood.service.I_Article;
import com.talaini.craftwood.service.I_Client;

@Controller
@RequestMapping("/commande")
public class CommandeController {
	
	@Autowired
    private I_Commande cmdDao ;
	 @Autowired
	    private CommandeRepository commandeRepository;
	@Autowired
	private I_Article artDao;
	
	@Autowired
	private I_Client cliDao;
	
	@Autowired
    private ReportService service;
	
	@GetMapping("/")
	public String listcommandes(Model md) {
		//System.out.println(commandeRepository.findAll());
		md.addAttribute("datacmd",cmdDao.afficherCommandes());
		return "list_cmd";
	}
	
	@GetMapping("/Add")
	public String addcommandeform(Model md) {
		md.addAttribute("articles", artDao.afficherArticles());
		return "commande";
	}
	@PostMapping("/Add")
	public String addcommande(HttpServletResponse response,HttpServletRequest request) {
		String listart=request.getParameter("listart");
		String total=request.getParameter("total");
		String id_client=request.getParameter("idclient");
		Date date=new Date();
		Client c=cliDao.afficherClientAvecId(Integer.parseInt(id_client));
		Commande cmd=new Commande.CommandeBuilder().setclient(c).setEtat(Etat.EnAttente.toString()).setcreated_at(date).setupdated_at(date).setTotal(Double.parseDouble(total)).build();
		if(cmdDao.ajouterCommande(cmd,listart)!=null) {
			return "commande";
		}
		return "null";
	}
	@GetMapping("/show/{id}")
	public String showcommande(Model md,@PathVariable int id) {
		md.addAttribute("datacmd", cmdDao.afficherInfosCommande(id));
		return  "Infos";
	}
	@GetMapping("/Delete/{id}")
	public String deletecommande(Model md,@PathVariable int id) {
		if(cmdDao.supprimeCommandes(id)) {
			md.addAttribute("datacmd", cmdDao.afficherCommandes());
			return "list_cmd";
		}
		return null;
	}
	@PostMapping("/cl")
	public void showclient(HttpServletResponse response,HttpServletRequest request) {
		String nomclient = request.getParameter("nom");

		if(nomclient != null){
			List<Client> ar= cliDao.afficherClients();
			List client = ar.stream().filter(i ->i.getNom().equals(nomclient)).collect(Collectors.toList());
			//System.out.println(client);
			String toJson = new Gson().toJson(client);
			//System.out.print(toJson);
			response.setContentType("application/json");
			try (PrintWriter writer = response.getWriter()) {
			    writer.write(toJson);
			} catch (IOException e) {
			    e.printStackTrace();
			}
		}
	}

	@PostMapping("/ar")
	public void showarticle(Model md,HttpServletResponse response,HttpServletRequest request) {
		String libelle = request.getParameter("nom");
		if(libelle != null){
			List<Article> ar= artDao.afficherArticles();
			List article = ar.stream().filter(i ->i.getLibelle().equals(libelle)).collect(Collectors.toList());
			String toJson = new Gson().toJson(article);
			//System.out.print(toJson);
			response.setContentType("application/json");
			try {
				response.getWriter().write(toJson);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@GetMapping("/rapport/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return service.exportReport(format);
    }

	
}
