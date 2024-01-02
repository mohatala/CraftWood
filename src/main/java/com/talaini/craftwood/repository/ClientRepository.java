package com.talaini.craftwood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talaini.craftwood.entity.Client;

@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
