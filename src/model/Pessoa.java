package model;

import java.time.LocalDate;
import java.time.Period;

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

    // Metodo que retorna a idade da pessoa
    public int getIdade(){
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}
