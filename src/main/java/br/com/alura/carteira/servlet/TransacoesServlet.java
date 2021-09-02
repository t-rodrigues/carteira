package br.com.alura.carteira.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.carteira.dao.TransacaoDao;
import br.com.alura.carteira.domain.Transacao;
import br.com.alura.carteira.domain.enums.TipoTransacao;
import br.com.alura.carteira.factory.ConnectionFactory;

@WebServlet("/transacoes")
public class TransacoesServlet extends HttpServlet {

	private TransacaoDao dao;

	public TransacoesServlet() {
		this.dao = new TransacaoDao(new ConnectionFactory().getConnection());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Transacao> transacoes = dao.listar();

		req.setAttribute("transacoes", transacoes);
		req.getRequestDispatcher("WEB-INF/jsp/transacoes.jsp").forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String ticker = req.getParameter("ticker");
		BigDecimal preco = new BigDecimal(req.getParameter("preco").replace(",", "."));
		Integer quantidade = Integer.parseInt(req.getParameter("quantidade"));
		LocalDate data = LocalDate.parse(req.getParameter("data"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		TipoTransacao tipo = TipoTransacao.valueOf(req.getParameter("tipo").toUpperCase());

		Transacao transacao = new Transacao(ticker, preco, quantidade, data, tipo);

		dao.cadastrar(transacao);

		res.sendRedirect("transacoes");
	}

}
