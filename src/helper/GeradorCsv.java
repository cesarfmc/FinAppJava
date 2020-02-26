package helper;

import java.io.FileWriter;
import java.io.IOException;

public class GeradorCsv {
	static FileWriter writer;
	
	public static void tempoFinal(long tempoInicial, String acao) {
		long elapsed = System.currentTimeMillis() - tempoInicial;
		gerarCsv(acao + " "+elapsed+"\n");
	}

	public static void gerarCsv(String acaoTempo) {
		
		try {
			if(writer == null) {
				writer = new FileWriter("teste.csv");
			}

	        writer.append(acaoTempo);

	        writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void finalizarEscrita() {
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
