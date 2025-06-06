/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package agendamentomecanica;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AgendamentoMecanica extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        
        Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));

        Image img = new Image(getClass().getResource("/agendamentomecanica/Imagens/Hello.png").toExternalForm());
        ImageView imageView = new ImageView(img);
        Image img2 = new Image(getClass().getResource("/agendamentomecanica/Imagens/Analyze Data3.png").toExternalForm());
        ImageView imageView2 = new ImageView(img2);
        Image img3 = new Image(getClass().getResource("/agendamentomecanica/Imagens/User 1.png").toExternalForm());
        ImageView imageView3 = new ImageView(img3);
        Image img4 = new Image(getClass().getResource("/agendamentomecanica/Imagens/Mail.png").toExternalForm());
        ImageView imageView4 = new ImageView(img4);
        Image img5 = new Image(getClass().getResource("/agendamentomecanica/Imagens/Arrow Circle Left.png").toExternalForm());
        ImageView imageView5 = new ImageView(img5);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/agendamentomecanica/Estilo/botao_estilo.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/agendamentomecanica/Estilo/estilo2.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/agendamentomecanica/Estilo/comboBox.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/agendamentomecanica/Estilo/campo.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/agendamentomecanica/Estilo/botao_Voltar.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/agendamentomecanica/Estilo/botao_Proximo.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/agendamentomecanica/Estilo/dataPicker.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/agendamentomecanica/Estilo/botao_estilo.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            System.out.println("Fechando a aplicação, salvando dados...");
         
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
