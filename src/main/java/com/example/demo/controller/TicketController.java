package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Ticket;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.TicketService;

@Controller

public class TicketController {
	 @Autowired
	   TicketRepository ticketRepository;

	private TicketService ticketService;

	public TicketController(TicketService ticketService) {
		super();
		this.ticketService = ticketService;
	}
	
	// handler method to handle list Tickets and return mode and view
		@GetMapping("/tickets")
		public String listTickets(Model model) {
			model.addAttribute("tickets", ticketService.getAllTickets());
			return "Ticket";
		}
		
		@GetMapping("/tickets/new")
		public String createTicketForm(Model model) {
			
			// create student object to hold student form data
			Ticket ticket = new Ticket();
			model.addAttribute("ticket", ticket);
			return "CreateTickets";
			
		}
		@PostMapping("/tickets")
		public String saveTicket(@ModelAttribute("ticket") Ticket ticket) {
			ticketService.saveTicket(ticket);
			return "redirect:/tickets";
		}
		

		@GetMapping("/tickets/edit/{id}")
		public String editTicketForm(@PathVariable Long id, Model model) {
			model.addAttribute("ticket", ticketService.getTicketById(id));
			return "edit_tickets";
		}

		@PostMapping("/tickets/edit/{id}")
		public String updateTicket(@PathVariable Long id,
				@ModelAttribute("ticket") Ticket ticket,
				Model model) {
			
			// get student from database by id
			Ticket existingTicket = ticketService.getTicketById(id);
			
			existingTicket.setDescription(ticket.getDescription());
			existingTicket.setUrgence(ticket.getUrgence());
			existingTicket.setEnvironement(ticket.getEnvironement());
			existingTicket.setLogiciel(ticket.getLogiciel());
			existingTicket.setUrgence(ticket.getUrgence());
			// save updated student object
			ticketService.updateTicket(existingTicket);
			return "redirect:/tickets";		
		}
		
		// handler method to handle delete student request
		
		@GetMapping("/tickets/{id}")
		public String deleteTicket(@PathVariable Long id) {
			ticketService.deleteTicketById(id);
			return "redirect:/tickets";
		}
}
