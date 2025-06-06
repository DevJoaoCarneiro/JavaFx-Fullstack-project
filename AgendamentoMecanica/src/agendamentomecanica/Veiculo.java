package agendamentomecanica;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Veiculo {

   
    private final StringProperty modelo;
    private final StringProperty marca;
    private final StringProperty ano;
    private final StringProperty placa;
    private final StringProperty comentario;
    private String cpfCliente; 

    
    public Veiculo(String modelo, String marca, String ano, String placa, String comentario, String cpfCliente) {
        this.modelo = new SimpleStringProperty(modelo);
        this.marca = new SimpleStringProperty(marca);
        this.ano = new SimpleStringProperty(ano);
        this.placa = new SimpleStringProperty(placa);
        this.comentario = new SimpleStringProperty(comentario);
        this.cpfCliente = cpfCliente; // Armazena o CPF do cliente
    }

   
    public Veiculo() {
        this.modelo = new SimpleStringProperty();
        this.marca = new SimpleStringProperty();
        this.ano = new SimpleStringProperty();
        this.placa = new SimpleStringProperty();
        this.comentario = new SimpleStringProperty();
        this.cpfCliente = null;
    }

    
    public String getModelo() {
        return modelo.get();
    }

    public void setModelo(String modelo) {
        this.modelo.set(modelo);
    }

    public StringProperty modeloProperty() {
        return modelo;
    } // Necessário para TableView

    public String getMarca() {
        return marca.get();
    }

    public void setMarca(String marca) {
        this.marca.set(marca);
    }

    public StringProperty marcaProperty() {
        return marca;
    }

    public String getAno() {
        return ano.get();
    }

    public void setAno(String ano) {
        this.ano.set(ano);
    }

    public StringProperty anoProperty() {
        return ano;
    }

    public String getPlaca() {
        return placa.get();
    }

    public void setPlaca(String placa) {
        this.placa.set(placa);
    }

    public StringProperty placaProperty() {
        return placa;
    }

    public String getComentario() {
        return comentario.get();
    }

    public void setComentario(String comentario) {
        this.comentario.set(comentario);
    }

    public StringProperty comentarioProperty() {
        return comentario;
    }

    
    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }
}
