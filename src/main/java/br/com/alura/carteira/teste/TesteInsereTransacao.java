package br.com.alura.carteira.teste;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import br.com.alura.carteira.domain.Transacao;
import br.com.alura.carteira.domain.enums.TipoTransacao;

public class TesteInsereTransacao {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/carteira";
		String usuario = "docker";
		String senha = "docker";

		try {
			Connection conexao = DriverManager.getConnection(url, usuario, senha);

			Transacao t = new Transacao("XPTO1", new BigDecimal("99.99"), 100, LocalDate.of(2021, 9, 1),
					TipoTransacao.COMPRA);

			String sql = "INSERT INTO transacoes (ticker, preco, quantidade, data, tipo) values (?, ?, ?, ?, ?)";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, t.getTicker());
			ps.setBigDecimal(2, t.getPreco());
			ps.setInt(3, t.getQuantidade());
			ps.setDate(4, Date.valueOf(t.getData()));
			ps.setString(5, t.getTipo().toString());

			ps.execute();
		} catch (SQLException e) {
			System.out.println("erro ao conectar no MySql");
		}
	}

}
