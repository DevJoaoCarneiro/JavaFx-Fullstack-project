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
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MenuPrincipalController implements Initializable  {

    @FXML
    private Button EmailButtom;

    @FXML
    private Button LogoutButtom;

    @FXML
    private Button MenuButtom;

    @FXML
    private Button PerfilButtom;

    @FXML
    private Button ProximoButtom1;

    @FXML
    private Button ProximoButtom11;

    @FXML
    private Button ProximoButtom111;

    @FXML
    private Button button;

    @FXML
    private Label label;


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
    private void consulta(ActionEvent event) throws IOException {
        Parent telaconsulta = FXMLLoader.load(getClass().getResource("ConsultaFXML.fxml"));
        Scene sceneConsulta = new Scene(telaconsulta);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneConsulta);
        window.show();
    }
    
    @FXML
    private void agendamento(ActionEvent event) throws IOException {
        Parent telaAgendamento = FXMLLoader.load(getClass().getResource("Agendamento.fxml"));
        Scene sceneAgendamento = new Scene(telaAgendamento);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneAgendamento);
        window.show();
    }
    
    @FXML
    private void cadastro(ActionEvent event) throws IOException {
        Parent telaCadastro = FXMLLoader.load(getClass().getResource("Cadastro.fxml"));
        Scene sceneCadastro = new Scene(telaCadastro);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneCadastro);
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

}