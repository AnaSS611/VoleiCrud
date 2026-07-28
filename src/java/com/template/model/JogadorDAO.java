package com.template.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Classe responsável pelas operações CRUD na tabela de jogadores */
public class JogadorDAO {
    //registra mensagens no console
    private static final Logger logger = Logger.getLogger(JogadorDAO.class.getName());

    ArrayList<JogadorDTO> listaJogadores = new ArrayList<>();

    public ArrayList<JogadorDTO> listarJogadores() {
        String sql = "SELECT * FROM jogadores";

        try (Connection c = new Conection().conectaBD();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                JogadorDTO jogador = new JogadorDTO();
                jogador.setId(rs.getInt("id"));
                jogador.setNome(rs.getString("nome"));
                jogador.setIdade(rs.getInt("idade"));
                jogador.setPosicao(rs.getString("posicao"));
                jogador.setTime(rs.getString("time"));

                listaJogadores.add(jogador);
            }

        } catch (SQLException e) {
            //mostra o que quebrou
            logger.log(Level.SEVERE, "Erro ao listar jogadores", e);
        }

        return listaJogadores;
    }

    public void insertPlayer(JogadorDTO joga) {
        String sql = "INSERT INTO jogadores (nome, idade, posicao, time) VALUES (?, ?, ?, ?)";

        try (Connection c = new Conection().conectaBD();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, joga.getNome());
            ps.setInt(2, joga.getIdade());
            ps.setString(3, joga.getPosicao());
            ps.setString(4, joga.getTime());

            ps.execute();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao inserir jogador", e);
        }
    }
    public void updatePlayer(JogadorDTO joga) {
        String sql = "UPDATE jogadores SET nome=?, idade=?, posicao=?, time=? WHERE id=?";
        try (Connection c = new Conection().conectaBD();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, joga.getNome());
            ps.setInt(2, joga.getIdade());
            ps.setString(3, joga.getPosicao());
            ps.setString(4, joga.getTime());
            ps.setInt(5, joga.getId());

            ps.execute();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar jogador", e);
        }
    }

    public void deletePlayer(int id) {
        String sql = "DELETE FROM jogadores WHERE id=?";
        try (Connection c = new Conection().conectaBD();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao excluir jogador", e);
        }
    }
}