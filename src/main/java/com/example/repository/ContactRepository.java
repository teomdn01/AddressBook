package com.example.repository;

import com.example.domain.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer> {
    public Long countById(Integer id);
}
