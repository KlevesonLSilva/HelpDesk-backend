package com.newba.HelpDesk.Services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newba.HelpDesk.Chamado;
import com.newba.HelpDesk.repositories.ChamadoRepository;

@Service
public class ChamadoService {
    
    @Autowired
    private ChamadoRepository repository;

    public Chamado findById(Integer id){
        Optional<Chamado> obj= repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID:" + id, obj));
    }

    public List<Chamado> findAll() {
        return repository.findAll();
    }
}
