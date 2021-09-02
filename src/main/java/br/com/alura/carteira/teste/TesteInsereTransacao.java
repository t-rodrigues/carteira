package br.com.alura.carteira.teste;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;

import br.com.alura.carteira.dao.TransacaoDao;
import br.com.alura.carteira.domain.Transacao;
import br.com.alura.carteira.domain.enums.TipoTransacao;

public class TesteInsereTransacao {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/carteira";
		String usuario = "docker";
		String senha = "docker";

		try {
			Connection conexao = DriverManager.getConnection(url, usuario, senha);

			TransacaoDao dao = new TransacaoDao(conexao);

			Transacao t = new Transacao("XPTO1", new BigDecimal("13.90"), 5, LocalDate.of(2021, 9, 1),
					TipoTransacao.VENDA);

			dao.cadastrar(t);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro!");
		}
	}

}
