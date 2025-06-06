package agendamentomecanica;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ConsultaController implements Initializable {

    @FXML
    private Button ConsultaClienteButtom;

    @FXML
    private TextField campoBusca;

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
    private Button button;

    @FXML
    private Label label;

    @FXML
    private TableView<Veiculo> table_view;

    @FXML
    private TextField textField_Comentario;

    @FXML
    private TextField textField_Placa;

    @FXML
    private TableColumn<Veiculo, String> colunaNome;
    @FXML
    private TableColumn<Veiculo, String> colunaCpf;
    @FXML
    private TableColumn<Veiculo, String> colunaModelo;
    @FXML
    private TableColumn<Veiculo, String> colunaPlaca;

    private ObservableList<Veiculo> listaCompletaDeVeiculos;
    private FilteredList<Veiculo> listaFiltradaDeVeiculos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        configurarColunas();

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

        listaCompletaDeVeiculos = FXCollections.observableArrayList(BancoDadosCliente.listaVeiculo);
        listaFiltradaDeVeiculos = new FilteredList<>(listaCompletaDeVeiculos, p -> true); // Cria uma lista filtrável
        table_view.setItems(listaFiltradaDeVeiculos);

        table_view.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, veiculoSelecionado) -> {
                    if (veiculoSelecionado != null) {
                        textField_Comentario.setText(veiculoSelecionado.getComentario());
                    } else {
                        textField_Comentario.clear();
                    }
                }
        );

        campoBusca.textProperty().addListener((obs, oldVal, newVal) -> filtrarTabela(newVal));

        System.out.println("Tabela de consulta inicializada com " + listaCompletaDeVeiculos.size() + " agendamentos.");

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
    private void handleBotaoConsulta(ActionEvent event) {
        // Pega o texto que está no campo de busca
        String termoDeBusca = campoBusca.getText();

        // Chama a mesma função de filtro que já existe
        filtrarTabela(termoDeBusca);

        System.out.println("Busca acionada pelo botão com o termo: " + termoDeBusca);
    }

    private void filtrarTabela(String textoDeBusca) {
        String texto = textoDeBusca.toLowerCase().trim();
        listaFiltradaDeVeiculos.setPredicate(veiculo -> {
            if (texto.isEmpty()) {
                return true; // Mostra tudo se a busca estiver vazia
            }
            // Verifica se a placa ou o CPF do cliente contêm o texto da busca
            return veiculo.getPlaca().toLowerCase().contains(texto)
                    || veiculo.getCpfCliente().toLowerCase().contains(texto);
        });
    }

    private void configurarColunas() {
        // Colunas que pegam dados diretamente do Veiculo
        colunaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colunaPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));

        // Colunas que precisam buscar informações do Cliente
        colunaCpf.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCpfCliente()));
        colunaNome.setCellValueFactory(cellData -> {
            String cpf = cellData.getValue().getCpfCliente();
            Cliente cliente = encontrarClientePorCpf(cpf);
            return new SimpleStringProperty(cliente != null ? cliente.getNome() : "N/A");
        });
    }

    private Cliente encontrarClientePorCpf(String cpf) {
        for (Cliente cliente : BancoDadosCliente.listaCliente) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
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
    private void voltarMenu(ActionEvent event) throws IOException {

        Parent menuPrincipal = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));

        Scene telaMenuPrincipal = new Scene(menuPrincipal);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(telaMenuPrincipal);
        window.show();
    }

}
