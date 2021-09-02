package br.com.alura.carteira.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.carteira.domain.Transacao;
import br.com.alura.carteira.domain.enums.TipoTransacao;

public class TransacaoDao {
	
	private Connection conexao;
	
	public TransacaoDao(Connection conexao) {
		this.conexao = conexao;
	}

	public void cadastrar(Transacao transacao) {
		try {
			String sql = "INSERT INTO transacoes (ticker, preco, quantidade, data, tipo) values (?, ?, ?, ?, ?)";

			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, transacao.getTicker());
			ps.setBigDecimal(2, transacao.getPreco());
			ps.setInt(3, transacao.getQuantidade());
			ps.setDate(4, Date.valueOf(transacao.getData()));
			ps.setString(5, transacao.getTipo().toString());

			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Transacao> listar() {
		try {
			String sql = "SELECT * FROM transacoes";

			PreparedStatement ps = conexao.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Transacao> transacoes = new ArrayList<>();

			while (rs.next()) {
				Transacao transacao = new Transacao(rs.getString("ticker"), rs.getBigDecimal("preco"),
						rs.getInt("quantidade"), rs.getDate("data").toLocalDate(),
						TipoTransacao.valueOf(rs.getString("tipo")));
				transacoes.add(transacao);
			}

			return transacoes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
