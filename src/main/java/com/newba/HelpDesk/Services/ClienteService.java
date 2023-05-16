package com.newba.HelpDesk.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.newba.HelpDesk.Pessoa;
import com.newba.HelpDesk.Cliente;
import com.newba.HelpDesk.Services.exceptions.DataInterfrityViolationException;
import com.newba.HelpDesk.Services.exceptions.ObjectnotFoundexcption;
import com.newba.HelpDesk.dto.ClienteDto;
import com.newba.HelpDesk.repositories.PessoaRepository;
import com.newba.HelpDesk.repositories.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
	private BCryptPasswordEncoder encoder;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundexcption("objeto não encontrado! id:" + id));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDto objDto) {
        objDto.setId(null);
        objDto.setSenha(encoder.encode(objDto.getSenha()));
        validaPorCpfeEmail(objDto);
        Cliente newObj = new Cliente(objDto);
        return repository.save(newObj);

    }

    private void validaPorCpfeEmail(ClienteDto objDto) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataInterfrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDto.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataInterfrityViolationException("E-MAIL já cadastrado no sistema!");
        }
    }

    public Cliente update(Integer id, ClienteDto objDto) {
        objDto.setId(id);
        Cliente oldObj = findById(id);
        validaPorCpfeEmail(objDto);
        oldObj = new Cliente(objDto);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if(obj.getChamados().size() >0){
            throw new DataInterfrityViolationException("Técnico possoui orden de serviço e não pode ser deletado!");
        }
        repository.deleteById(id);
    }
}
