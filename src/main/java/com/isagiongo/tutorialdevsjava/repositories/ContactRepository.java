package com.isagiongo.tutorialdevsjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isagiongo.tutorialdevsjava.models.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
