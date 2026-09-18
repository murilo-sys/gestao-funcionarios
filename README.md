# Desafio Prático: Gestão de Funcionários (Java)

Projeto desenvolvido para resolução do teste prático de programação em Java, focado em **Clean Code**, princípios de **Orientação a Objetos (SOLID)** e recursos modernos da **API do Java 8+** (`java.time`, `java.math.BigDecimal`, `Stream API`).

---

## 🏗️ Estrutura do Projeto

O código foi organizado seguindo separação clara de responsabilidades:

```
gestao-funcionarios/
├── src/
│   ├── model/
│   │   ├── Pessoa.java       # Superclasse de domínio (Nome, Data de Nascimento e cálculo de Idade)
│   │   └── Funcionario.java  # Subclasse (Salário com BigDecimal, Função e regra de reajuste)
│   └── Principal.java        # Orquestrador da execução de todos os requisitos do teste
├── bin/                      # Bytecodes compilados (.class)
└── README.md                 # Documentação técnica do projeto
```

---

## 🎯 Requisitos Atendidos

| Item | Requisito | Implementação / Destaque Técnico |
| :---: | :--- | :--- |
| **1** | Classe `Pessoa` | Atributos `nome` (`String`), `dataNascimento` (`LocalDate`), encapsulamento e método `getIdade()` com `Period.between`. |
| **2** | Classe `Funcionario` | Herança de `Pessoa` via `super()`, atributos `salario` (`BigDecimal`), `funcao` (`String`) e método de domínio `aumentarSalario`. |
| **3.1** | Inserção dos funcionários | Criação de `List<Funcionario>` na mesma sequência da tabela do enunciado. |
| **3.2** | Remoção do funcionário "João" | Uso de `removeIf(f -> f.getNome().equalsIgnoreCase("João"))`, prevenindo `ConcurrentModificationException`. |
| **3.3** | Impressão formatada | Datas formatadas com `DateTimeFormatter` (`dd/MM/yyyy`) e valores monetários com `DecimalFormat` e `Locale.of("pt", "BR")` (separador milhar `.` e decimal `,`). |
| **3.4** | Aumento salarial de 10% | Método de domínio seguro utilizando cálculo percentual preciso com `BigDecimal` e validação Fail-Fast. |
| **3.5** | Agrupamento por Função | Utilização de `Map<String, List<Funcionario>>` gerado via `Collectors.groupingBy(Funcionario::getFuncao)`. |
| **3.6** | Impressão agrupada | Iteração funcional limpa sobre o Map com `forEach((funcao, lista) -> ...)`. |
| **3.8** | Aniversariantes meses 10 e 12 | Filtragem via Streams usando `f.getDataNascimento().getMonthValue()`. |
| **3.9** | Funcionário com maior idade | Identificação com `min(Comparator.comparing(Funcionario::getDataNascimento))` e cálculo dinâmico de idade. |
| **3.10** | Ordenação alfabética | Ordenação com `sorted(Comparator.comparing(Funcionario::getNome))`. |
| **3.11** | Total dos salários | Redução funcional com `stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add)`. |
| **3.12** | Razão de salários mínimos | Divisão de precisão centesimal com `divide(salarioMinimo, 2, RoundingMode.HALF_UP)` baseada em R$ 1.212,00. |

---

## 💡 Decisões Arquiteturais e Boas Práticas

1. **Uso Mandatório de `BigDecimal`:**
   - Valores monetários **nunca** utilizam `double` ou `float` para evitar imprecisões do padrão IEEE 754 de ponto flutuante.
   - Divisões utilizam obrigatoriamente `RoundingMode.HALF_UP` e especificação de escala de 2 casas decimais.

2. **API `java.time` (`LocalDate` e `Period`):**
   - Substituição de APIs legadas (`Date` / `Calendar`).
   - Cálculo de idade dinâmico e fidedigno considerando dia e mês exatos através de `Period.between(dataNascimento, LocalDate.now()).getYears()`.

3. **Programação Funcional e Streams:**
   - Uso intensivo de métodos declarativos (`filter`, `map`, `reduce`, `groupingBy`, `sorted`, `forEach`), gerando código conciso, testável e sem efeitos colaterais acidentais.

---

## 🚀 Como Executar

### Pré-requisitos
- Java JDK 17+ instalado (compatível com Java 21 e superiores).

### Passo a passo pelo Terminal

1. Clone o repositório:
```bash
git clone <URL_DO_REPOSITORIO>
cd gestao-funcionarios
```

2. Compile os fontes:
```bash
javac -d bin -cp bin src/model/*.java src/Principal.java
```

3. Execute a aplicação:
```bash
java -cp bin Principal
```
