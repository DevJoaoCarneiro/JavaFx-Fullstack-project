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
import javafx.scene.control.TableRow;
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
    private Button botaoConfirmar;

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

        //AQUI
        table_view.setRowFactory(tv -> {
            TableRow<Veiculo> row = new TableRow<>();

            // Listener que observa o item (Veiculo) contido na linha
            row.itemProperty().addListener((obs, oldItem, newItem) -> {
                if (newItem == null) {
                    // Se a linha estiver vazia, remove todos os estilos
                    row.getStyleClass().removeAll("status-aberto", "status-andamento", "status-finalizado");
                } else {
                    // 1. Aplica o estilo inicial baseado no status atual do item
                    aplicarEstiloDeStatus(row, newItem.getStatus());

                    // 2. Adiciona um listener NO PRÓPRIO ITEM. Se o status do item mudar,
                    // a cor da linha muda junto, AUTOMATICamente.
                    newItem.statusProperty().addListener((statusObs, oldStatus, newStatus) -> {
                        aplicarEstiloDeStatus(row, newStatus);
                    });
                }
            });
            return row;
        });

        // Listener para atualizar a descrição (você já tem este)
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
    
     private void aplicarEstiloDeStatus(TableRow<Veiculo> row, StatusAgendamento status) {
        row.getStyleClass().removeAll("status-aberto", "status-andamento", "status-finalizado");

        if (status != null) {
            switch (status) {
                case ABERTO:
                    row.getStyleClass().add("status-aberto");
                    break;
                case EM_ANDAMENTO:
                    row.getStyleClass().add("status-andamento");
                    break;
                case FINALIZADO:
                    row.getStyleClass().add("status-finalizado");
                    break;
            }
        }
    }
    
    @FXML
    private void handleConfirmarAgendamento(ActionEvent event) {
        Veiculo veiculoSelecionado = table_view.getSelectionModel().getSelectedItem();

        if (veiculoSelecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Nenhum Agendamento Selecionado", "Por favor, selecione um agendamento na tabela para confirmar.");
            return;
        }

        // Lógica para avançar o status
        StatusAgendamento statusAtual = veiculoSelecionado.getStatus();
        StatusAgendamento proximoStatus = statusAtual;

        switch (statusAtual) {
            case ABERTO:
                proximoStatus = StatusAgendamento.EM_ANDAMENTO;
                break;
            case EM_ANDAMENTO:
                proximoStatus = StatusAgendamento.FINALIZADO;
                break;
            case FINALIZADO:
                proximoStatus = StatusAgendamento.ABERTO; // Volta para aberto, por exemplo
                break;
        }
        veiculoSelecionado.setStatus(proximoStatus);

        // Força a atualização visual da tabela e salva os dados
        table_view.refresh();
     

        // Mostra a mensagem de confirmação
        mostrarAlerta(Alert.AlertType.INFORMATION, "Status Alterado", "O status do agendamento para a placa " + veiculoSelecionado.getPlaca() + " foi alterado para: " + proximoStatus.toString());
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

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
