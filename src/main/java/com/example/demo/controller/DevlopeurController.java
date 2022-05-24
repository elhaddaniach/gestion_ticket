package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Devlopeur;
import com.example.demo.repository.DevlopeurRepository;
import com.example.demo.service.DevlopeurService;

@Controller
public class DevlopeurController {
	
	@Autowired
	DevlopeurRepository devlopeurRepository;
	
	private DevlopeurService devlopeurService;

	public DevlopeurController(DevlopeurService devlopeurService) {
		super();
		this.devlopeurService = devlopeurService;
	}

	
	// handler method to handle list devlopeurs and return mode and view
			@GetMapping("/devlopeurs")
			public String listDevlopeurs(Model model) {
				model.addAttribute("devlopeurs", devlopeurService.getAllDevlopeurs());
				return "devlopeurs";
			}
			
			@GetMapping("/devlopeurs/new")
			public String createDevlopeurForm(Model model) {
				
				// create student object to hold student form data
				Devlopeur devlopeur = new Devlopeur();
				model.addAttribute("devlopeur", devlopeur);
				return "create_devlopeur";
				
			}
			
			
			@PostMapping("/devlopeurs")
			public String saveClient(@ModelAttribute("devlopeur") Devlopeur devlopeur) {
				devlopeurService.saveDevlopeur(devlopeur);
				return "redirect:/devlopeurs";
			}
			

			@GetMapping("/devlopeurs/edit/{id}")
			public String editDevlopeurForm(@PathVariable Long id, Model model) {
				model.addAttribute("devlopeur", devlopeurService.getDevlopeurById(id));
				return "edit_devlopeur";
			}

			@PostMapping("/devlopeurs/{id}")
			public String updateDevlopeur(@PathVariable Long id,
					@ModelAttribute("devlopeur") Devlopeur devlopeur,
					Model model) {
				
				// get student from database by id
				Devlopeur existingDevlopeur = devlopeurService.getDevlopeurById(id);
				existingDevlopeur.setId(id);
				existingDevlopeur.setName(devlopeur.getName());
				existingDevlopeur.setList(devlopeur.getList());
				
				// save updated student object
				devlopeurService.updateDevlopeur(existingDevlopeur);
				return "redirect:/devlopeurs";		
			}
			
			// handler method to handle delete student request
			
			@GetMapping("/devlopeurs/{id}")
			public String deleteDevlopeur(@PathVariable Long id) {
				devlopeurService.deleteDevlopeurById(id);
				return "redirect:/devlopeurs";
			}
}
