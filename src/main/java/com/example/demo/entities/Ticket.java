package com.example.demo.entities;

import javax.persistence.*;



@Entity
@Table(name = "Tickets")

public class Ticket {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name = "description")
	private String description;
	

	@Column(name = "urgence")
	private String urgence;
	

	@Column(name = "environement")
	private String environement;
	

	@Column(name = "logiciel")
	private String logiciel;
	
	
	//@ManyToOne(fetch =FetchType.LAZY)
	//@JoinColumn(name="ticket_id", nullable = false)
	//private Client client;
	
	

	//@ManyToOne(fetch =FetchType.LAZY)
	//@JoinColumn(name="ticket_id", nullable = false)
	//private Devlopeur devlopeur;
	

	//@ManyToOne(fetch =FetchType.LAZY)
	//@JoinColumn(name="tikcet_id", nullable = false)
	//private Administrateur admin;	


	public Ticket() {
		super();
	}
	public Ticket(Long id, String description, String urgence, String environement, String logiciel) {
		super();
		this.id = id;
		this.description = description;
		this.urgence = urgence;
		this.environement = environement;
		this.logiciel = logiciel;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrgence() {
		return urgence;
	}
	public void setUrgence(String urgence) {
		this.urgence = urgence;
	}
	public String getEnvironement() {
		return environement;
	}
	public void setEnvironement(String environement) {
		this.environement = environement;
	}
	public String getLogiciel() {
		return logiciel;
	}
	public void setLogiciel(String logiciel) {
		this.logiciel = logiciel;
	}

	

	

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", description=" + description + ", urgence=" + urgence + ", environement="
				+ environement + ", logiciel=" + logiciel + "+ ]";
	}
	public Ticket(String description, String urgence, String environement, String logiciel) {
		super();
		this.description = description;
		this.urgence = urgence;
		this.environement = environement;
		this.logiciel = logiciel;
	}

	

	
	
	
	
}