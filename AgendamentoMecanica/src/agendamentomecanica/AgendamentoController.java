package agendamentomecanica;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AgendamentoController implements Initializable {

    @FXML
    private Button ConsultaClienteButtom;

    @FXML
    private Button EmailButtom;

    @FXML
    private Button LogoutButtom;

    @FXML
    private Button MenuButtom;

    @FXML
    private Button PerfilButtom;

    @FXML
    private Button ProximoButtom;

    @FXML
    private Button VoltarButtom;

    @FXML
    private Button button;

    @FXML
    private Label label;

    @FXML
    private TextField textField_Ano;

    @FXML
    private TextField textField_Comentario;

    @FXML
    private TextField textField_Marca;

    @FXML
    private TextField textField_Modelo;

    @FXML
    private TextField textField_Placa;
    
    @FXML
    private TextField textField_CpfCliente;
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Image img = new Image(getClass().getResource("/agendamentomecanica/Imagens/Mail.png").toExternalForm());
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        EmailButtom.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        EmailButtom.setGraphic(imageView);

        Image img2 = new Image(getClass().getResource("/agendamentomecanica/Imagens/Arrow Circle Left.png").toExternalForm());
        ImageView imageView2 = new ImageView(img2);
        imageView2.setFitHeight(50);
        imageView2.setFitWidth(50);
        MenuButtom.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        MenuButtom.setGraphic(imageView2);

        Image img3 = new Image(getClass().getResource("/agendamentomecanica/Imagens/User 1.png").toExternalForm());
        ImageView imageView3 = new ImageView(img3);
        imageView3.setFitHeight(50);
        imageView3.setFitWidth(50);
        PerfilButtom.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        PerfilButtom.setGraphic(imageView3);

        Image img4 = new Image(getClass().getResource("/agendamentomecanica/Imagens/Logout.png").toExternalForm());
        ImageView imageView4 = new ImageView(img4);
        imageView4.setFitHeight(50);
        imageView4.setFitWidth(50);
        LogoutButtom.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        LogoutButtom.setGraphic(imageView4);

    }

    @FXML
    private void adicionarAgendamento() {
        String cpfDigitado = textField_CpfCliente.getText().trim();
        String modelo = textField_Modelo.getText().trim();
        String marca = textField_Marca.getText().trim();
        String ano = textField_Ano.getText().trim();
        String placa = textField_Placa.getText().trim();
        String comentario = textField_Comentario.getText().trim();

       
        if (cpfDigitado.isEmpty()) {
            mostrarAlerta(AlertType.WARNING, "Campo Obrigatório", "O CPF do cliente é obrigatório.");
            return;
        }
        if (placa.isEmpty()) {
            mostrarAlerta(AlertType.WARNING, "Campo Obrigatório", "A Placa do veículo é obrigatória.");
            return;
        }
        if (modelo.isEmpty()) {
            mostrarAlerta(AlertType.WARNING, "Campo Obrigatório", "O Modelo do veículo é obrigatório.");
            return;
        }
        
        if(comentario.isEmpty()){
            mostrarAlerta(AlertType.WARNING, "Campo Obrigatório", "A descrição do problema veículo é obrigatório.");
            return;
        }
        
        Cliente clienteEncontrado = encontrarClientePorCpf(cpfDigitado);

        if (clienteEncontrado != null) {

            Veiculo novoVeiculo = new Veiculo(modelo, marca, ano, placa, comentario, clienteEncontrado.getCpf());

            BancoDadosCliente.listaVeiculo.add(novoVeiculo);
            

            mostrarAlerta(AlertType.INFORMATION, "Sucesso!",
                    "Agendamento para o veículo de placa '" + placa +
                    "' realizado com sucesso para o cliente: " + clienteEncontrado.getNome() + ".");

            limparCampos(); 
        } else {
            
            mostrarAlerta(AlertType.ERROR, "Cliente Não Encontrado",
                    "O CPF '" + cpfDigitado + "' não corresponde a nenhum cliente cadastrado.\n" +
                    "Por favor, cadastre o cliente primeiro utilizando o botão 'Cadastro Cliente'.");
        }
       
    }
    
    private Cliente encontrarClientePorCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return null;
        }
        for (Cliente cliente : BancoDadosCliente.listaCliente) {
            if (cliente.getCpf() != null && cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null; // Nenhum cliente encontrado com este CPF
    }
    
    @FXML
    private void mostrarAlerta(AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null); // Não mostra cabeçalho
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
    
    @FXML
    private void abrirTelaCadastro(ActionEvent event) throws IOException {

        Parent telaCadastroParent = FXMLLoader.load(getClass().getResource("Cadastro.fxml"));

        Scene telaCadastroScene = new Scene(telaCadastroParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(telaCadastroScene);
        window.show();
    }
    
    @FXML
    private void voltarMenu(ActionEvent event) throws IOException {

        Parent menuPrincipal = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));

        Scene telaMenuPrincipal = new Scene(menuPrincipal);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(telaMenuPrincipal);
        window.show();
    }

    

    @FXML
    private void acaoBotao(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Funcionalidade Indisponível");
        alert.setHeaderText(null); // Sem cabeçalho
        alert.setContentText("Esta funcionalidade ainda não foi implementada.");

        alert.showAndWait();

    }

    @FXML
    private void voltarBotao(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerta Usuario");
        alert.setHeaderText(null); // Sem cabeçalho
        alert.setContentText("Usuario nao cadastrado");
        limparCampos();

        alert.showAndWait();

    }

    private void habilitarCampos() {
        textField_Modelo.setDisable(false);
        textField_Marca.setDisable(false);
        textField_Ano.setDisable(false);
        textField_Placa.setDisable(false);
        textField_Comentario.setDisable(false);

    }

    private void desabilitarCampos() {
        textField_Modelo.setDisable(true);
        textField_Marca.setDisable(true);
        textField_Ano.setDisable(true);
        textField_Placa.setDisable(true);
        textField_Comentario.setDisable(true);
    }

    private void verificarCampos() {
        ProximoButtom.setDisable(!camposPreenchidos());
    }

    private boolean camposPreenchidos() {
        return !textField_Marca.getText().isEmpty()
                && !textField_Modelo.getText().isEmpty()
                && !textField_Comentario.getText().isEmpty();
    }

    private void preencherCampos(Veiculo cliente) {
        textField_Modelo.setText(cliente.getModelo());
        textField_Marca.setText(cliente.getMarca());
        textField_Ano.setText(cliente.getAno());
        textField_Placa.setText(cliente.getPlaca());
        textField_Comentario.setText(cliente.getComentario());

    }

    private void limparCampos() {
        textField_Modelo.clear();
        textField_Marca.clear();
        textField_Ano.clear();
        textField_Placa.clear();
        textField_Comentario.clear();
        textField_CpfCliente.clear();
        

    }
}
