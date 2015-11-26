package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		CalculaPrecoIngresso calculaPrecoIngresso = new CalculaPrecoIngresso(sessao.getEspetaculo().getTipo());
		preco = calculaPrecoIngresso.calcula();
		
		
		if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA) || sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.SHOW)) {
			//quando estiver acabando os ingressos... 
			preco = calcularPreco(sessao,0.10,0.05);
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.BALLET)) {
			preco = calcularPreco(sessao,0.20,0.50);
			
			preco = calcularPrecoPorDuracao(sessao, preco);
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.ORQUESTRA)) {
			preco = calcularPreco(sessao,0.20,0.50);

			preco = calcularPrecoPorDuracao(sessao, preco);
		}  else {
			preco = sessao.getPreco();
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static BigDecimal calcularPrecoPorDuracao(Sessao sessao, BigDecimal preco) {
		if(sessao.getDuracaoEmMinutos() > 60){
			preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		}
		return preco;
	}

	private static BigDecimal calcularPreco(Sessao sessao,double valor,double valorIngresso) {
		BigDecimal preco;
		if((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= valorIngresso) { 
			preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(valor)));
		} else {
			preco = sessao.getPreco();
		}
		return preco;
	}

}