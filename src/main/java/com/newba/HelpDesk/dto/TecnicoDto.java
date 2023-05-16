package com.newba.HelpDesk.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.newba.HelpDesk.Tecnico;
import com.newba.HelpDesk.enums.Perfil;



public class TecnicoDto implements Serializable{
    private static final long serialVersionUID = 1L;

    protected Integer id;
    @NotNull(message = "o campo nome e obrigatorio") // faz que o acampo não aceite que o campo seja null
    protected String nome;
    @NotNull(message = "o campo cpf e obrigatorio")
    protected String cpf;
    @NotNull(message = "o campo email e obrigatorio")
    protected String email;
    @NotNull(message = "o campo senha e obrigatorio")
    protected String senha;
    protected Set<Integer> perfis = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy") // serve para formamtar a data para o padrão que eu quiser
    protected LocalDate dataCriacao = LocalDate.now();
    
    public TecnicoDto(){
        super();
        setPerfil(Perfil.CLIENTE);
    }

    public TecnicoDto(Tecnico obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void setPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setSenha(String encode) {
    }

    
}
