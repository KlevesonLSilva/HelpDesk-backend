package com.newba.HelpDesk.Resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newba.HelpDesk.Chamado;
import com.newba.HelpDesk.Services.ChamadoService;
import com.newba.HelpDesk.dto.ChamadoDto;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {
    
    @Autowired
    private ChamadoService service;

    @GetMapping(value ="/{id}")
    public ResponseEntity<ChamadoDto> findById(@PathVariable Integer id){
        Chamado obj= service.findById(id);
        return ResponseEntity.ok().body(new ChamadoDto(obj));
        
    }
}
