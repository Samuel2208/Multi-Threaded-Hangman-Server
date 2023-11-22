import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements Initializable {
    @FXML
    private Button startButton, endButton;
    @FXML
    private TextField portNumText;
    @FXML
    private Integer clientCount;
    //    @FXML
//    private BorderPane root;
    @FXML
    private ListView<String> listItems;

    Server serverConnection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void startServer(ActionEvent e){
        try{
            serverConnection = new Server(data -> {
                Platform.runLater(()->{
                    listItems.getItems().add(data.toString());
                });

            }, Integer.valueOf(portNumText.getText()));

            endButton.setDisable(false);
            startButton.setDisable(true);

        }catch (NumberFormatException error){
            portNumText.setText("Enter numbers only");
        }

    }

    public void endServer(ActionEvent e){
        startButton.setDisable(false);
        endButton.setDisable(true);

    }


}
