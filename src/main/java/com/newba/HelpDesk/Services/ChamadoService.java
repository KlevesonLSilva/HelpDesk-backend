package com.newba.HelpDesk.Services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newba.HelpDesk.Chamado;
import com.newba.HelpDesk.Cliente;
import com.newba.HelpDesk.Tecnico;
import com.newba.HelpDesk.dto.ChamadoDto;
import com.newba.HelpDesk.enums.Prioridade;
import com.newba.HelpDesk.enums.Status;
import com.newba.HelpDesk.repositories.ChamadoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;

@Service
public class ChamadoService {
    
    @Autowired
    private ChamadoRepository repository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    

    public Chamado findById(Integer id){
        Optional<Chamado> obj= repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID:" + id, obj));
    }

    public List<Chamado> findAll() {
        return repository.findAll();
    }

    public Chamado create(@Valid ChamadoDto objDto) {
        return repository.save(newChamado(objDto));
    }

    private Chamado newChamado(ChamadoDto obj){
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        Chamado chamado = new Chamado();
        if(obj.getId() != null){
            chamado.setId(obj.getId());
        }
        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        return chamado;
    }
}
