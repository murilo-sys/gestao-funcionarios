package model;

import java.time.LocalDate;

public class Pessoa {
    
    // Declaração dos atributos de Pessoa
    private String nome;
    private LocalDate dataNascimento;

    // Construtor recebendo nome e dataNascimento como parâmetro
    public Pessoa(String nome, LocalDate dataNascimento){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    // Metodo getNome
    public String getNome(){
        return nome;
    }

     // Metodo getDataNascimento
    public LocalDate getDataNascimento(){
        return dataNascimento;
    }
}
