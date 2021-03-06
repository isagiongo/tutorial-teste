package com.isagiongo.tutorialdevsjava.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.isagiongo.tutorialdevsjava.models.Contact;
import com.isagiongo.tutorialdevsjava.services.ContactService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/contacts")
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	
	@ApiOperation(value = "Lista todos os contatos", notes = "Lista todos os contatos", 
			response = Contact.class, responseContainer = "List" )
	@GetMapping
	public List<Contact> findAll(){
		return contactService.findAll();
	}
	
	@ApiOperation(value = "Busca contato por Id", notes = "Busca contato por Id")
	@GetMapping(value="/{id}")
	public ResponseEntity<Contact> findById(@PathVariable Long id){
		return contactService.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@ApiOperation(value = "Cria novo contato", notes = "Cria novo contato, tendo como campos obrigatórios Name e Email")
	@PostMapping
	public ResponseEntity<Contact> create(@Valid @RequestBody Contact contact){
		Contact obj = contactService.save(contact);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@ApiOperation(value = "Altera contato por Id", notes = "Altera contato por Id")
	@PutMapping(value="/{id}")
	public ResponseEntity update(@PathVariable("id") long id,
	                                      @RequestBody Contact contact) {
	   return contactService.findById(id)
	           .map(record -> {
	               record.setName(contact.getName());
	               record.setEmail(contact.getEmail());
	               record.setPhone(contact.getPhone());
	               Contact updated = contactService.save(record);
	               return ResponseEntity.accepted().body(updated);
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	@ApiOperation(value = "Deleta contato por Id", notes = "Deleta contato por Id")
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable long id) {
	   return contactService.findById(id)
	           .map(record -> {
	               contactService.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}
}