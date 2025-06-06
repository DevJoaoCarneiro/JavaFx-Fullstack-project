package agendamentomecanica;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CadastroController implements Initializable {

    @FXML
    private TextField textField_Complemento;

    @FXML
    private TextField textField_Cpf;

    @FXML
    private TextField textField_Endereco;

    @FXML
    private TextField textField_Nacionalidade;

    @FXML
    private TextField textField_Nome;

    @FXML
    private DatePicker DataText;

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
    private ComboBox<String> SexoText;

    @FXML
    private Label label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SexoText.getItems().addAll("Masculino", "Feminino");

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
    private void voltarParaAgendamento(ActionEvent event) throws IOException {
        Parent telaAgendamento = FXMLLoader.load(getClass().getResource("Agendamento.fxml"));
        Scene sceneAgendamento = new Scene(telaAgendamento);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneAgendamento);
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
    private void selecionarGenero() {
        String generoSelecionado = SexoText.getValue();
        System.out.println("Gênero selecionado: " + generoSelecionado);
    }

    @FXML
    private void acaoBotao(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Funcionalidade Indisponível");
        alert.setHeaderText(null); // Sem cabeçalho
        alert.setContentText("Esta funcionalidade ainda não foi implementada.");

        alert.showAndWait();

    }

    @FXML
    private void voltarBotao(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Alerta Usuario");
        alert.setHeaderText(null); // Sem cabeçalho
        alert.setContentText("Usuario nao cadastrado");
        limparCampos();

        alert.showAndWait();

    }

    @FXML
    private void adicionarCliente() {
        if (!camposPreenchidos()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Campos Obrigatórios");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, preencha o nome completo e o CPF.");
            alert.showAndWait();
         
            return;
        }

        String nome = textField_Nome.getText();
        String cpf = textField_Cpf.getText();
        String endereco = textField_Endereco.getText();
        String complemento = textField_Complemento.getText();
        String nacionalidade = textField_Nacionalidade.getText();
        LocalDate dataNascimento = DataText.getValue();

        Cliente novoCliente = new Cliente(nome, cpf, endereco, dataNascimento, nacionalidade);
     
        BancoDadosCliente.listaCliente.add(novoCliente);
        
        habilitarCampos();

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Alerta cadastro");
        alert.setHeaderText(null);
        alert.setContentText("Cadastrado com sucesso!");
        alert.showAndWait();

        // Limpar campos para um novo cliente
        limparCampos();
    }

    private void habilitarCampos() {
        textField_Nome.setDisable(false);
        textField_Complemento.setDisable(false);
        textField_Cpf.setDisable(false);
        textField_Endereco.setDisable(false);
        textField_Nacionalidade.setDisable(false);
        DataText.setDisable(false);
    }

    private void desabilitarCampos() {
        textField_Nome.setDisable(true);
        textField_Complemento.setDisable(true);
        textField_Cpf.setDisable(true);
        textField_Endereco.setDisable(true);
        textField_Nacionalidade.setDisable(true);
        DataText.setDisable(true);
    }

    private void verificarCampos() {
        ProximoButtom.setDisable(!camposPreenchidos());
    }

    private boolean camposPreenchidos() {
        return !textField_Nome.getText().isEmpty()
                && !textField_Cpf.getText().isEmpty();
    }

    private void limparCampos() {
        textField_Nome.clear();
        textField_Complemento.clear();
        textField_Cpf.clear();
        textField_Endereco.clear();
        textField_Nacionalidade.clear();
        DataText.setValue(null);
        SexoText.setValue(null);

    }
}
