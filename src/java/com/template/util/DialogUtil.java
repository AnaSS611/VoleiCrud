package com.template.util;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DialogUtil {

    public static void showError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText("Digite uma idade válida (apenas números).");
        alert.showAndWait();
    }

    public static boolean showConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Excluir jogador");
        alert.setContentText("Deseja realmente excluir este jogador?");

        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public static void showInformation(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}

/* Da prof:
Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();*/

/* Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
        return alert.showANdWait().get() == ButtonType.OK;*/