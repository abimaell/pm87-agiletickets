package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public abstract class CalculaPrecoIngresso {
	public abstract BigDecimal calcula();

	public static BigDecimal calcularPreco(Sessao sessao, double valor,
			double valorIngresso) {
		BigDecimal preco;
		if ((sessao.getTotalIngressos() - sessao.getIngressosReservados())
				/ sessao.getTotalIngressos().doubleValue() <= valorIngresso) {
			preco = sessao.getPreco().add(
					sessao.getPreco().multiply(BigDecimal.valueOf(valor)));
		} else {
			preco = sessao.getPreco();
		}
		return preco;
	}

	public static BigDecimal calcularPrecoPorDuracao(Sessao sessao,
			BigDecimal preco) {
		if (sessao.getDuracaoEmMinutos() > 60) {
			preco = preco.add(sessao.getPreco().multiply(
					BigDecimal.valueOf(0.10)));
		}
		return preco;
	}
}
