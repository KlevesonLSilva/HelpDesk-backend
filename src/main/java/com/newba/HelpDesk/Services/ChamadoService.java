package com.newba.HelpDesk.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newba.HelpDesk.Chamado;
import com.newba.HelpDesk.Cliente;
import com.newba.HelpDesk.Tecnico;

import com.newba.HelpDesk.dto.ChamadoDto;
import com.newba.HelpDesk.enums.Prioridade;
import com.newba.HelpDesk.enums.Status;
import com.newba.HelpDesk.repositories.ChamadoRepository;



@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;
    
    public class ObjectNotFoundException extends RuntimeException {
        public ObjectNotFoundException(String message) {
            super(message);
        }
    }

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID:" + id));
    }

    public List<Chamado> findAll() {
        return repository.findAll();
    }

    public Chamado create(@Valid ChamadoDto objDto) {
        return repository.save(newChamado(objDto));
    }

    private Chamado newChamado(ChamadoDto obj) {
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        Chamado chamado = new Chamado();
        if (obj.getId() != null) {
            chamado.setId(obj.getId());
        }
        if (obj.getStatus().equals(2)) {
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        return chamado;
    }

    public Chamado update(Integer id, @Valid ChamadoDto objDto) {
        objDto.setId(id);
        Chamado oldObj = findById(id);
        oldObj = newChamado(objDto);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Chamado obj = findById(id);

        if (obj != null) {
            repository.deleteById(id);
        }
    }
}
