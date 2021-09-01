package br.com.alura.carteira.teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.alura.carteira.domain.Transacao;
import br.com.alura.carteira.domain.enums.TipoTransacao;

public class TesteSelectTransacao {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/carteira";
		String usuario = "root";
		String senha = "docker";

		try {
			Connection conexao = DriverManager.getConnection(url, usuario, senha);

			String sql = "SELECT * FROM transacoes";

			PreparedStatement ps = conexao.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Transacao t = new Transacao(rs.getString("ticker"), rs.getBigDecimal("preco"), rs.getInt("quantidade"),
						rs.getDate("data").toLocalDate(), TipoTransacao.valueOf(rs.getString("tipo")));
				System.out.println(t);
			}
		} catch (SQLException e) {
			System.out.println("erro ao conectar no MySql");
		}
	}

}
