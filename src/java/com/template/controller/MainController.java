package com.template.controller;

import com.template.model.JogadorDAO;
import com.template.model.JogadorDTO;
import com.template.util.DialogUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class MainController {

    // TextFields
    @FXML private TextField txtId;
    @FXML private TextField txtNome;
    @FXML private TextField txtIdade;
    @FXML private TextField txtPosicao;
    @FXML private TextField txtTime;

    // Botões
    @FXML private Button btnSalvar;
    @FXML private Button btnEditar;
    @FXML private Button btnExcluir;
    @FXML private Button btnLimpar;

    // Tabela
    @FXML private TableView<JogadorDTO> tblVolei;
    @FXML private TableColumn<JogadorDTO, Integer> colId;
    @FXML private TableColumn<JogadorDTO, String> colNome;
    @FXML private TableColumn<JogadorDTO, Integer> colIdade;
    @FXML private TableColumn<JogadorDTO, String> colPosicao;
    @FXML private TableColumn<JogadorDTO, String> colTime;

    @FXML private ImageView imgVolei;

    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        colPosicao.setCellValueFactory(new PropertyValueFactory<>("posicao"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        carregarJogadores();
    }

    @FXML
    private void carregarJogadores() {
        JogadorDAO objJogadorDAO = new JogadorDAO();
        ArrayList<JogadorDTO> listaJogadores = objJogadorDAO.listarJogadores();
        tblVolei.setItems(FXCollections.observableArrayList(listaJogadores));
    }

    @FXML
    private void carregarCampos() {
        JogadorDTO objJogadorDTO = tblVolei.getSelectionModel().getSelectedItem();

        if (objJogadorDTO != null) {
            txtId.setText(String.valueOf(objJogadorDTO.getId()));
            txtNome.setText(objJogadorDTO.getNome());
            txtIdade.setText(String.valueOf(objJogadorDTO.getIdade()));
            txtPosicao.setText(objJogadorDTO.getPosicao());
            txtTime.setText(objJogadorDTO.getTime());
        }
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {

        String nome = txtNome.getText();
        String idadeTexto = txtIdade.getText();
        int idade;
        try {
            idade = Integer.parseInt(idadeTexto);
        } catch (NumberFormatException e) {
            DialogUtil.showError();
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
    private void btnExcluirAction(ActionEvent event) {

        int id = Integer.parseInt(txtId.getText());

        if (DialogUtil.showConfirmation()) {

            JogadorDAO objJogadorDAO = new JogadorDAO();
            objJogadorDAO.deletePlayer(id);

            carregarJogadores();
            btnLimparAction(null);
        }
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        txtId.clear();
        txtNome.clear();
        txtIdade.clear();
        txtPosicao.clear();
        txtTime.clear();
        txtNome.requestFocus();
    }
}