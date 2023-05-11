package com.newba.HelpDesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newba.HelpDesk.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
    
}
