package com.newba.HelpDesk.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.newba.HelpDesk.Pessoa;
import com.newba.HelpDesk.Tecnico;
import com.newba.HelpDesk.Services.exceptions.DataInterfrityViolationException;
import com.newba.HelpDesk.Services.exceptions.ObjectnotFoundexcption;
import com.newba.HelpDesk.dto.TecnicoDto;
import com.newba.HelpDesk.repositories.PessoaRepository;
import com.newba.HelpDesk.repositories.TecnicoRepository;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
	private BCryptPasswordEncoder encoder;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundexcption("objeto não encontrado! id:" + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDto objDto) {
        objDto.setId(null);
        objDto.setSenha(encoder.encode(objDto.getSenha()));
        validaPorCpfeEmail(objDto);
        Tecnico newObj = new Tecnico(objDto);
        return repository.save(newObj);

    }

    private void validaPorCpfeEmail(TecnicoDto objDto) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataInterfrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDto.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataInterfrityViolationException("E-MAIL já cadastrado no sistema!");
        }
    }

    public Tecnico update(Integer id, TecnicoDto objDto) {
        objDto.setId(id);
        Tecnico oldObj = findById(id);
        validaPorCpfeEmail(objDto);
        oldObj = new Tecnico(objDto);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if(obj.getChamados().size() >0){
            throw new DataInterfrityViolationException("Técnico possoui orden de serviço e não pode ser deletado!");
        }
        repository.deleteById(id);
    }
}
