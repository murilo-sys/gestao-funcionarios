import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import model.Funcionario;

public class Principal {
    public static void main(String[] args) throws Exception {
        // Inserção dos funcionários usando list do tipo Funcionario conforme tabela do exercicio/teste
        List<Funcionario> funcionarios = new ArrayList<>();

        // Adicionando os funcionarios a lista
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 05, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 05, 02), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 05), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // Removendo o funcionário João da lista usando equalsIgnoreCase para ignorar diferença entre letras maiúsculas e minúsculas
        funcionarios.removeIf(funcionario -> funcionario.getNome().equalsIgnoreCase("João"));

        // Instancia DateTimeFormatter para formatar dataNascimento padrão pt-br
        DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Instacia DecimalFormat para formatar salario, usando DecimalFormatSymbols onde aplica os símbolos correto (separador de milhar e decimal)
        DecimalFormat formatadorMoeda = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.of("pt","BR")));

        // Printa todos os funcionários usando um loop for
        System.out.println("\n==== LISTA DE FUNCIONÁRIOS ===");
        for (Funcionario f: funcionarios){
            System.out.println(
                "Nome: " + f.getNome() +
                " | Data Nascimento: " + f.getDataNascimento().format(formatadorData) +
                " | Salário: R$ " + formatadorMoeda.format(f.getSalario()) +
                " | Função: " + f.getFuncao()
            );
        }

        // Usa o laço de repetição forEach para aumentar em 10% o salário de cada funcionário
        funcionarios.forEach(f -> f.aumentarSalario(10));

        // Cria um map e separa os funcionários pela suas devidas funções/cargo
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
        .collect(Collectors.groupingBy(Funcionario::getFuncao)); // Usando metodo groupingBy para separar automaticamente por função

        // Printa os funcionários separados por função usando .forEach
        System.out.println("\n=== LISTA DE FUNCIONÁRIOS AGRUPADOS POR FUNÇÃO ===");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("\nCargo: " + funcao);
            lista.forEach(f -> System.out.println("  - " + f.getNome()));
        });

        // Printa os funcionários que fazem aniversário no mes 10 ou 12 usando .filter
        System.out.println("\n=== LISTA DE FUNCIONÁRIOS QUE FAZEM ANIVERSÁRIO MÊS 10 OU 12 ===");
        funcionarios.stream().filter(f -> {
            int mes = f.getDataNascimento().getMonthValue();
            return mes == 10 || mes == 12;
        }).forEach(f -> System.out.println(f.getNome() + " - Data: " + f.getDataNascimento().format(formatadorData)));

        // Printa o funcionário com a maior idade
        Funcionario maisVelho = funcionarios.stream()
        .min(Comparator.comparing(Funcionario::getDataNascimento))
        .orElse(null);

        // Caso não seja null
        if(maisVelho != null){
            System.out.println("\n=== FUNCIONÁRIO COM MAIOR IDADE ===");
            System.out.println("Nome: " + maisVelho.getNome() + " | Idade: " + maisVelho.getIdade() + " anos");
        }

    }
}
