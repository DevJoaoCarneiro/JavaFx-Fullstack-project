# ⚙️ Sistema de Gestão para Oficina Mecânica (Printf.co)

Este é um projeto de um sistema de desktop para gestão de oficina mecânica, desenvolvido em **Java** com a biblioteca **JavaFX**.  
A aplicação foi criada para ser uma solução completa e intuitiva para o gerenciamento de clientes, veículos e agendamentos de serviços, com persistência de dados em arquivos locais.

![Image](https://github.com/user-attachments/assets/87004e91-61f5-409e-927a-8acb28914653)

---

## ✅ Funcionalidades Implementadas

### ✔️ Módulo de Clientes
- Interface dedicada para o cadastro completo de novos clientes (Nome, CPF, Endereço, Data de Nascimento, Sexo, etc.).

### ✔️ Módulo de Agendamentos
- Formulário para registrar agendamentos de serviços para veículos, com campos para **Modelo**, **Marca**, **Ano**, **Placa** e uma **descrição detalhada do problema**.
- **Vínculo Inteligente**: O sistema exige um CPF de um cliente já cadastrado para criar um novo agendamento, garantindo a integridade dos dados.

### ✔️ Painel de Consulta Interativo
- **Visualização em Tabela**: Todos os agendamentos são exibidos em uma `TableView` clara e organizada.
- **Filtro por Texto**: Um campo de busca permite filtrar a tabela em tempo real por **CPF do cliente** ou **Placa do veículo**.
- **Filtro por Status**: Botões dedicados permitem filtrar os agendamentos por status: **Aberto**, **Em Andamento** ou **Finalizado**. Os filtros de texto e de status podem ser combinados.
- **Master-Detail View**: Ao selecionar um agendamento na tabela, os detalhes do problema são exibidos instantaneamente em uma área de texto dedicada.

### ✔️ Gerenciamento de Status
- Cada agendamento possui um status: `ABERTO`, `EM_ANDAMENTO`, `FINALIZADO`.
- Um botão **"Confirmar Etapa"** permite avançar o status de um agendamento selecionado.
- **Estilização Dinâmica**: As linhas da tabela mudam de cor automaticamente de acordo com o status do agendamento:  
  🔴 **Vermelho** para Aberto  
  🟡 **Amarelo** para Em Andamento  
  🟢 **Verde** para Finalizado  
  Isso oferece um feedback visual imediato.

---

## 🚀 Como Executar o Projeto

### Pré-requisitos:
- Ter o **JDK (Java Development Kit) 8** ou superior instalado.
- Ter o **JavaFX SDK** configurado na sua IDE (necessário para JDKs mais recentes que o 8).
- Uma IDE Java como **NetBeans** ou **IntelliJ**.

---

## 👨‍💻 Autor

Feito por:  
- João Victor Simeão Carneiro  
- Gabriel Henrique Vilas Boas da Silva  
- Donato Valentino de Velis Maita
