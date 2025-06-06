package agendamentomecanica;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDate;

public class Cliente {

    private final StringProperty nome;
    private final StringProperty cpf;
    private String endereco;
    private LocalDate dataNascimento;
    private String nacionalidade;

    public Cliente() {
        this.nome = new SimpleStringProperty(this, "nome");
        this.cpf = new SimpleStringProperty(this, "cpf");
    }

    public Cliente(String nome, String cpf, String endereco, LocalDate dataNascimento, String nacionalidade) {
        this.nome = new SimpleStringProperty(nome);
        this.cpf = new SimpleStringProperty(cpf);
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
    }

    // --- Nome ---
    public String getNome() {
        return nome.get();
    }

    public void setNome(String value) {
        nome.set(value);
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    // --- CPF ---
    public String getCpf() {
        return cpf.get();
    }

    public void setCpf(String value) {
        cpf.set(value);
    }

    public StringProperty cpfProperty() {
        return cpf;
    }

    // --- Outros Getters e Setters (mantêm como estão se não forem para a tabela diretamente) ---
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}
