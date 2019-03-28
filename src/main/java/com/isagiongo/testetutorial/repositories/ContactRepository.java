package com.isagiongo.testetutorial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isagiongo.testetutorial.models.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
