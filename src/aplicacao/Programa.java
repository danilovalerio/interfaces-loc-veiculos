package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.AluguelCarro;
import model.entities.Veiculo;
import model.services.AluguelServico;
import model.services.TaxaServicoBrasil;

public class Programa {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		System.out.println("Bem vindo ao AlugaCaranga\n---------------------------");
		System.out.println("Informe os dados do aluguel");
		System.out.print("Modelo do carro:");
		String modeloCarro = sc.nextLine();
		System.out.print("Retirada (dd/MM/yyyy hh:ss): ");
		Date dataRetirada = sdf.parse(sc.nextLine());
		System.out.print("Devolução (dd/MM/yyyy hh:ss): ");
		Date dataDevolucao = sdf.parse(sc.nextLine());
		System.out.print("Valor da hora: ");
		double valorHora = sc.nextDouble();
		System.out.print("Valor da diária: ");
		double valorDiaria = sc.nextDouble();
		
		AluguelCarro ac = new AluguelCarro(dataRetirada, dataDevolucao, new Veiculo(modeloCarro));
		
		AluguelServico as = new AluguelServico(valorHora, valorDiaria, new TaxaServicoBrasil());
		
		as.processaFatura(ac);
		
		
		
		System.out.println("---------------------------------\nValor do aluguel: \n---------------------------------");
		System.out.println("Pagamento básico: R$ "+ String.format("%.2f", ac.getFatura().getPagamentoBasico()));
		System.out.println("Taxa: R$ "+ String.format("%.2f", ac.getFatura().getTaxa()));
		System.out.println("PAGAMENTO TOTAL: R$ "+ String.format("%.2f", ac.getFatura().getPagamentoTotal()));
		
		sc.close();
		

	}

}
