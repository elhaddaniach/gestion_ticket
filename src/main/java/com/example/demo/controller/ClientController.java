package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Client;
import com.example.demo.repository.ClientRepository;

import com.example.demo.service.ClientService;


@Controller
public class ClientController {
	
	@Autowired
	ClientRepository clientRepository;
	
	
	private ClientService clientService;

	public ClientController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}
	
	
	
	// handler method to handle list clients and return mode and view
				@GetMapping("/clients")
				public String listClients(Model model) {
					model.addAttribute("clients", clientService.getAllClients());
					return "clients";
				}
	
				@GetMapping("/clients/new")
				public String createClientForm(Model model) {
					
					// create student object to hold student form data
					Client client = new Client();
					model.addAttribute("client", client);
					return "create_client";
					
				}
				
				@PostMapping("/clients")
				public String saveClient(@ModelAttribute("client") Client client) {
					clientService.saveClient(client);
					return "redirect:/clients";
				}
				

				@GetMapping("/clients/edit/{id}")
				public String editStudentForm(@PathVariable Long id, Model model) {
					model.addAttribute("client", clientService.getClientById(id));
					return "edit_student";
				}

				@PostMapping("/clients/{id}")
				public String updateStudent(@PathVariable Long id,
						@ModelAttribute("student") Client client,
						Model model) {
					
					// get student from database by id
					Client existingClient = clientService.getClientById(id);
					existingClient.setId(id);
					existingClient.setName(client.getName());
					existingClient.setList(client.getList());
				
					
					// save updated student object
					clientService.updateClient(existingClient);
					return "redirect:/clients";		
				}
				
				// handler method to handle delete student request
				
				@GetMapping("/clients/{id}")
				public String deleteStudent(@PathVariable Long id) {
					clientService.deleteClientById(id);
					return "redirect:/clients";
				}

}
