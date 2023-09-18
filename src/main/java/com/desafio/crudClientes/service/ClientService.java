package com.desafio.crudClientes.service;

import com.desafio.crudClientes.dto.ClientDto;
import com.desafio.crudClientes.entities.Client;
import com.desafio.crudClientes.repository.ClientRepository;
import com.desafio.crudClientes.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public ClientService(ClientRepository repository){
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<ClientDto> findAll(Pageable peageble){
        Page<Client> result = repository.findAll(peageble);
        return result.map(ClientDto::new);
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long id){
       Optional<Client> result = repository.findById(id);
        Client client = result.orElseThrow(
                () -> new ResourceNotFoundException("Recurso não Encontrado!"));
        return new ClientDto(client);
    }

    @Transactional
    public ClientDto insert(ClientDto dto){
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        repository.save(entity);
        return new ClientDto(entity);
    }

    @Transactional
    public ClientDto update(Long id, ClientDto dto){
        Optional<Client> result = repository.findById(id);
        Client entity = result.orElseThrow(
                () -> new ResourceNotFoundException("Recurso não Encontrado!"));
        copyDtoToEntity(dto,entity);
        return new ClientDto(entity);
    }

    @Transactional
    public ClientDto delete(Long id){
        Optional<Client> result = repository.findById(id);
        Client client = result.orElseThrow(
                () -> new ResourceNotFoundException("Recurso não Encontrado!"));
        repository.deleteById(id);
        return new ClientDto(client);
    }

private void copyDtoToEntity(ClientDto dto,Client entity){
    entity.setName(dto.getName());
    entity.setChildren(dto.getChildren());
    entity.setCpf(dto.getCpf());
    entity.setBirthDate(dto.getBirthDate());
    entity.setIncome(dto.getIncome());
}

}
