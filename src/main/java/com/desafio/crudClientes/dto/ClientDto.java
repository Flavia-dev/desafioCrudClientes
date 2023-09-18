package com.desafio.crudClientes.dto;

import com.desafio.crudClientes.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Optional;


@Getter
@Setter
public class ClientDto {
    private Long id;
    @NotBlank(message = "Campo requerido, não pode ser vazio")
    private String name;
    private String cpf;
    private Double income;
    @PastOrPresent(message = "Não pode ser data futura")
    private LocalDate birthDate;
    private Integer children;


    public ClientDto() {

    }
    public ClientDto(Client entity) {
        id = entity.getId();
        name = entity.getName();
        cpf = entity.getCpf();
        income = entity.getIncome();
        birthDate = entity.getBirthDate();
        children = entity.getChildren();
    }

    public ClientDto(Optional<Client> result) {
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCpf() {
        return cpf;
    }
    public Double getIncome() {
        return income;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public Integer getChildren() {
        return children;
    }

}
