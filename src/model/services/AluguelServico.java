package model.services;

import model.entities.AluguelCarro;
import model.entities.Fatura;

public class AluguelServico {
	
	private Double valorPorHora;
	private Double valorPorDia;
	
	private TaxaServicoBrasil taxaServico;
	
	public AluguelServico(Double valorPorHora, Double valorPorDia, TaxaServicoBrasil taxaServico) {
		this.valorPorHora = valorPorHora;
		this.valorPorDia = valorPorDia;
		this.taxaServico = taxaServico;
	}



	public void processaFatura(AluguelCarro aluguelCarro) {
		long data1 = aluguelCarro.getInicio().getTime();
		long data2 = aluguelCarro.getFim().getTime();
		
		//pega a diferença em milesegundos divide por 1000 para trazer para segundos
		// divide por 60 para trazer por minutos 
		// divide por 60 novamente para trazer horas
		double horas = (double) (data2 - data1) / 1000 / 60 / 60; 
		double pagamentoBasico;
		if( horas <= 12.0) {
			pagamentoBasico = Math.ceil(horas) * valorPorHora; //Math.ceil arredonda pra cima
		} 
		else {
			pagamentoBasico = Math.ceil(horas / 24) * valorPorDia; //Math.ceil arredonda pra cima
		}
		
		double taxa = taxaServico.taxa(pagamentoBasico);
		
		aluguelCarro.setFatura(new Fatura(pagamentoBasico, taxa));
	}

}
