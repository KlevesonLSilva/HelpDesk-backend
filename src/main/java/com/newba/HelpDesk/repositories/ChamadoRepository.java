package com.newba.HelpDesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newba.HelpDesk.Chamado;



public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{
    
}
