package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Administrateur;
import com.example.demo.repository.AdministrateurRepository;

import com.example.demo.service.AdministrateurService;
import com.example.demo.service.DevlopeurService;


@Controller

public class AdministrateurController {
	
	@Autowired
	AdministrateurRepository administrateurRepository;
	
	
	private AdministrateurService administrateurService;
	private DevlopeurService devlopeurService;

	public AdministrateurController(AdministrateurService administrateurService) {
		super();
		this.administrateurService = administrateurService;
	}


	// handler method to handle list administrateurs and return mode and view
	@GetMapping("/administrateurs")
	public String listAdministrateurs(Model model) {
		model.addAttribute("administrateurs", administrateurService.getAllAdministrateurs());
		return "administrateurs";
	}
	
	
	@GetMapping("/administrateurs/new")
	public String createStudentForm(Model model) {
		
		// create student object to hold student form data
		Administrateur administrateur = new Administrateur();
		model.addAttribute("administrateur", administrateur);
		return "create_administrateur";		
	}
	@PostMapping("/administrateurs")
	public String saveAdministrateur(@ModelAttribute("administrateur") Administrateur administrateur) {
		administrateurService.saveAdministrateur(administrateur);
		return "redirect:/administrateurs";
	}
	
	@GetMapping("/administrateur/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("administrateur", administrateurService.getAdministrateurById(id));
		return "edit_administrateur";
	}

	@PostMapping("/administrateurs/{id}")
	public String updateAdministarteur(@PathVariable Long id,
			@ModelAttribute("administrateur") Administrateur administrateur,
			Model model) {
		
		// get student from database by id
		Administrateur existingAdministrateur = administrateurService.getAdministrateurById(id);
		existingAdministrateur.setId(id);
		existingAdministrateur.setName(administrateur.getName());
		existingAdministrateur.setList(administrateur.getList());
		
		// save updated student object
		administrateurService.updateAdministrateur(existingAdministrateur);
		return "redirect:/administrateur";		
	}
	
	// handler method to handle delete student request
	
	@GetMapping("/administrateurs/{id}")
	public String deleteAdministrateur(@PathVariable Long id) {
		administrateurService.deleteAdministrateurById(id);
		return "redirect:/adminstrateurs";
	}
	/*
	@PostMapping("/administrateurs")
	public String AffecterTicketDev(@PathVariable("idTick") Long idTick, @PathVariable("iddev") Long iddev) {
		administrateurService.affecterTicket(iddev, idTick);
		return "admin_affectation";
				}
	*/
}
