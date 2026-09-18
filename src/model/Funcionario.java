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

    // Metodo para aumentar o salario, recebendo como parametro o percentual
    public void aumentarSalario(int percentual){

        // Verifica se o valor de entrada não é menor ou igual a 0
        if (percentual <= 0) {
            throw new IllegalArgumentException("O percentual deve ser maior que zero");
        }

        // Transforma o percentual em um fator de multiplicação. 10% vira 1.10
        // Pelo fato da variavel percentual ser "int", se faz necessário fazer o calculo dentro do BigDecimal
        BigDecimal fatorAumento = BigDecimal.valueOf(percentual).divide(new BigDecimal("100"));
        
        // Calcula o valor que será aumentado do salário
        BigDecimal aumento = this.salario.multiply(fatorAumento);
        
        // Usa a função add para acrescentar o valor de aumento
        this.salario = this.salario.add(aumento);
    }
}
