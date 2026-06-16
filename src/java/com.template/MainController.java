package com.template;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class MainController {
    //Texto
    @FXML private TextField txtId;
    @FXML private TextField txtNome;
    @FXML private TextField txtIdade;
    @FXML private TextField txtPosicao;
    @FXML private TextField txtTime;
    //Btns
    @FXML private Button btnSalvar;
    @FXML private Button btnEditar;
    @FXML private Button btnExcluir;
    @FXML private Button btnLimpar;
    //@FXML acessar componentes
    //Tabela-Colunas
    @FXML private TableView<JogadorDTO> tblVolei;
    @FXML private TableColumn<JogadorDTO, Integer> colId;
    @FXML private TableColumn<JogadorDTO, String> colNome;
    @FXML private TableColumn<JogadorDTO, Integer> colIdade;
    @FXML private TableColumn<JogadorDTO, String> colPosicao;
    @FXML private TableColumn<JogadorDTO, String> colTime;
    @FXML private ImageView imgVolei;


    @FXML
    private void initialize() {
        //Interliga cada TableColumn com os atributos do JogadorDTO
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        colPosicao.setCellValueFactory(new PropertyValueFactory<>("posicao"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        carregarJogadores();
    }
    @FXML
    //atualiza os campos
    private void carregarJogadores() {
        JogadorDAO objJogadorDAO = new JogadorDAO();
        ArrayList<JogadorDTO> listaJogadores = objJogadorDAO.listarJogadores();
        //Inserir os dados vindos do bd na tblVolei
        tblVolei.setItems(FXCollections.observableArrayList(listaJogadores));
    }
    @FXML
    private void carregarCampos() {
        // Pega o jogador que foi selecionado na tabela
        JogadorDTO objJogadorDTO = tblVolei.getSelectionModel().getSelectedItem();
        // Se tiver linha selecionada - preenche
        if (objJogadorDTO != null) {
            txtId.setText(String.valueOf(objJogadorDTO.getId()));
            txtNome.setText(objJogadorDTO.getNome());
            txtIdade.setText(String.valueOf(objJogadorDTO.getIdade()));
            txtPosicao.setText(objJogadorDTO.getPosicao());
            txtTime.setText(objJogadorDTO.getTime());
        }
    }
    //Empacota e grava no banco
    @FXML
    private void btnSalvarAction(ActionEvent event) {

        String nome = txtNome.getText();
        String idadeTexto = txtIdade.getText();
        int idade;
        try {
            idade = Integer.parseInt(idadeTexto);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Digite uma idade válida (apenas números).");
            alert.showAndWait();
            return;
        }
        String posicao = txtPosicao.getText();
        String time = txtTime.getText();
        JogadorDTO objJogadorDTO = new JogadorDTO();
        objJogadorDTO.setNome(nome);
        objJogadorDTO.setIdade(idade);
        objJogadorDTO.setPosicao(posicao);
        objJogadorDTO.setTime(time);

        JogadorDAO objJogadorDAO = new JogadorDAO();
        objJogadorDAO.insertPlayer(objJogadorDTO);

        carregarJogadores();
        btnLimparAction(null);
    }

    @FXML
    private void btnEditarAction(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        String nome = txtNome.getText();
        int idade = Integer.parseInt(txtIdade.getText());
        String posicao = txtPosicao.getText();
        String time = txtTime.getText();

        JogadorDTO objJogadorDTO = new JogadorDTO();
        objJogadorDTO.setId(id);
        objJogadorDTO.setNome(nome);
        objJogadorDTO.setIdade(idade);
        objJogadorDTO.setPosicao(posicao);
        objJogadorDTO.setTime(time);

        JogadorDAO objJogadorDAO = new JogadorDAO();
        objJogadorDAO.updatePlayer(objJogadorDTO);

        carregarJogadores();
        btnLimparAction(null);
    }
    @FXML
    private void btnLimparAction(ActionEvent event) {
        txtId.clear();
        txtNome.clear();
        txtIdade.clear();
        txtPosicao.clear();
        txtTime.clear();
        txtNome.requestFocus();// Garante o foco para o usuário continuar digitando via TAB
    }
    @FXML
    private void btnExcluirAction(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Excluir jogador");
        alert.setContentText("Deseja realmente excluir este jogador?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            JogadorDAO objJogadorDAO = new JogadorDAO();
            objJogadorDAO.deletePlayer(id);

            carregarJogadores();
            btnLimparAction(null);
        }
    }


}
