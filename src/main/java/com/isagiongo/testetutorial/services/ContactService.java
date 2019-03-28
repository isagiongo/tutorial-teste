package com.isagiongo.testetutorial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isagiongo.testetutorial.models.Contact;
import com.isagiongo.testetutorial.repositories.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	public Optional<Contact> findById(Long id) {
		return contactRepository.findById(id);
	}
	
	public List<Contact> findAll(){
		return contactRepository.findAll();
	}
	
	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}
	
	public void delete(Long id) {
		contactRepository.deleteById(id);
	}
}
