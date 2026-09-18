package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    
    // Declaração dos atributes de Funcionario extendendo de pessoa (nome e dataNascimento)
    private BigDecimal salario;
    private String funcao;

    // Construtor recebendo atributos
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao){
        // Passa os atributes nome e dataNascimento para a classe herdada
        super(nome, dataNascimento);

        this.salario = salario;
        this.funcao = funcao;
    }

    // Metodo getSalario
    public BigDecimal getSalario(){
        return salario;
    }

    // Metodo getFuncao
    public String getFuncao(){
        return funcao;
    }
}
