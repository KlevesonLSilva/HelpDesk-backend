package com.newba.HelpDesk.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newba.HelpDesk.Tecnico;
import com.newba.HelpDesk.Services.exceptions.ObjectnotFoundexcption;
import com.newba.HelpDesk.dto.TecnicoDto;
import com.newba.HelpDesk.repositories.TecnicoRepository;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundexcption("objeto não encontrado! id:" + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDto objDto) {
        objDto.setId(null);
        Tecnico newObj = new Tecnico(objDto);
        return repository.save(newObj);
    
}
}
