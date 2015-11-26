package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class CalculaPrecoIngressoShow extends CalculaPrecoIngresso {

	private Sessao sessao;
	private int quantidade;

	public CalculaPrecoIngressoShow(Sessao sessao, int quantidade) {
		super();
		this.sessao = sessao;
		this.quantidade = quantidade;
	}

	@Override
	public BigDecimal calcula() {
		BigDecimal preco = calcularPreco(sessao, 0.10, 0.05);
		return preco.multiply(BigDecimal.valueOf(this.quantidade));
	}
}
