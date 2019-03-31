package com.isagiongo.testetutorial.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isagiongo.testetutorial.models.Contact;
import com.isagiongo.testetutorial.services.ContactService;

@RestController
@RequestMapping("/contacts")
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping
	public List<Contact> findAll(){
		return contactService.findAll();
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Contact> findById(@PathVariable Long id){
		return contactService.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Contact create(@Valid @RequestBody Contact contact){
	   return contactService.save(contact);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity update(@PathVariable("id") long id,
	                                      @RequestBody Contact contact) {
	   return contactService.findById(id)
	           .map(record -> {
	               record.setName(contact.getName());
	               record.setEmail(contact.getEmail());
	               record.setPhone(contact.getPhone());
	               Contact updated = contactService.save(record);
	               return ResponseEntity.ok().body(updated);
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable long id) {
	   return contactService.findById(id)
	           .map(record -> {
	               contactService.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}



}
