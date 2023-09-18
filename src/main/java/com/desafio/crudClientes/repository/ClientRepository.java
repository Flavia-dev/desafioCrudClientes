package com.desafio.crudClientes.repository;

import com.desafio.crudClientes.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
