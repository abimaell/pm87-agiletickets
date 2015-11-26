package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class CalculaPrecoIngressoOrquestra extends CalculaPrecoIngresso {

	private Sessao sessao;
	private int quantidade;

	public CalculaPrecoIngressoOrquestra(Sessao sessao, int quantidade) {
		super();
		this.sessao = sessao;
		this.quantidade = quantidade;
	}

	@Override
	public BigDecimal calcula() {
		BigDecimal preco = calcularPreco(this.sessao, 0.20, 0.50);
		preco = calcularPrecoPorDuracao(sessao, preco);
		return preco.multiply(BigDecimal.valueOf(this.quantidade));
	}
}