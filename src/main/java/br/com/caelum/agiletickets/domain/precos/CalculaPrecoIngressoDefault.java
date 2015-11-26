package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class CalculaPrecoIngressoDefault extends CalculaPrecoIngresso {

	private Sessao sessao;
	private int quantidade;

	public CalculaPrecoIngressoDefault(Sessao sessao, int quantidade) {
		super();
		this.sessao = sessao;
		this.quantidade = quantidade;
	}

	@Override
	public BigDecimal calcula() {
		BigDecimal preco = sessao.getPreco();
		return preco.multiply(BigDecimal.valueOf(this.quantidade));
	}
}