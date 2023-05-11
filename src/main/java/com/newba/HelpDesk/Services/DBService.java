package com.newba.HelpDesk.Services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newba.HelpDesk.Chamado;
import com.newba.HelpDesk.Cliente;
import com.newba.HelpDesk.Tecnico;
import com.newba.HelpDesk.enums.Perfil;
import com.newba.HelpDesk.enums.Prioridade;
import com.newba.HelpDesk.enums.Status;
import com.newba.HelpDesk.repositories.ChamadoRepository;
import com.newba.HelpDesk.repositories.ClienteRepository;
import com.newba.HelpDesk.repositories.TecnicoRepository;

@Service
public class DBService {

    @Autowired 
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
    
    public void instanciaDB(){
        Tecnico tec1 = new Tecnico(null, "Newba", "04340538132", "kleveson_x3@hotmail.com", "tetas");
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Linus Torvals", "12345678910", "torval@email.com", "123");

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "CHAMADO 01", "primeiro chamado", tec1, cli1);

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
