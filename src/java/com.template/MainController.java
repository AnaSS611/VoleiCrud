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

public class MainController {
    //Caixas de Texto
    @FXML private TextField txtId;
    @FXML private TextField txtNome;
    @FXML private TextField txtIdade;
    @FXML private TextField txtPosicao;
    @FXML private TextField txtTime;
    //Botões
    @FXML private Button btnSalvar;
    @FXML private Button btnEditar;
    @FXML private Button btnExcluir;
    //Tabela e Colunas
    @FXML private TableView<JogadorDTO> tblVolei;
    @FXML private TableColumn<JogadorDTO, Integer> colId;
    @FXML private TableColumn<JogadorDTO, String> colNome;
    @FXML private TableColumn<JogadorDTO, Integer> colIdade;
    @FXML private TableColumn<JogadorDTO, String> colPosicao;
    @FXML private TableColumn<JogadorDTO, String> colTime;

    @FXML
    private void initialize() {
        System.out.println("FXML loaded successfully!");
        //Interliga cada TableColumn com os atributos do JogadorDTO
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
        //Inserir os dados vindos do bd na tblVolei
        tblVolei.setItems(FXCollections.observableArrayList(listaJogadores));
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        //Pega os textos
        String nome = txtNome.getText();
        int idade = Integer.parseInt(txtIdade.getText());
        String posicao = txtPosicao.getText();
        String time = txtTime.getText();
        //Instancia o seu DTO e guarda os dados
        JogadorDTO objjogadordto = new JogadorDTO();
        objjogadordto.setNome(nome);
        objjogadordto.setIdade(idade);
        objjogadordto.setPosicao(posicao);
        objjogadordto.setTime(time);
        //Instancia o seu DAO e chama o metodo de inserir
        JogadorDAO objjogadordao = new JogadorDAO();
        objjogadordao.insertPlayer(objjogadordto);
        //Recarrega a tabela p/ o jogador novo aparecer na hora
        carregarJogadores();
    }

    @FXML
    private void btnEditarAction(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        String nome = txtNome.getText();
        int idade = Integer.parseInt(txtIdade.getText());
        String posicao = txtPosicao.getText();
        String time = txtTime.getText();

        JogadorDTO joga = new JogadorDTO();
        joga.setId(id);
        joga.setNome(nome);
        joga.setIdade(idade);
        joga.setPosicao(posicao);
        joga.setTime(time);

        JogadorDAO objJogadorDAO = new JogadorDAO();
        objJogadorDAO.updatePlayer(joga);

        carregarJogadores();
    }

    @FXML
    private void btnExcluirAction(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        JogadorDAO objJogadorDAO = new JogadorDAO();
        objJogadorDAO.deletePlayer(id);

        carregarJogadores();
    }

}
