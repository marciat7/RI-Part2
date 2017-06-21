package index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class Indexador {

	public static void main(String[] args) {

		Arquivo arquivo = new Arquivo("WINE_RI.csv", "compactado.txt");
		Map<String, Integer> dicionarioTitulo = new HashMap<String, Integer>();
		Map<String, Integer> dicionarioUva = new HashMap<String, Integer>();
		Map<String, Integer> dicionarioClassificacao = new HashMap<String, Integer>();
		Map<String, Integer> dicionarioVolume = new HashMap<String, Integer>();
		Map<String, Integer> dicionarioTeorA = new HashMap<String, Integer>();

		ArrayList<Pagina> paginas = new ArrayList<Pagina>();
		// ArrayList<Indice> indices = new ArrayList<Indice>();

		Map<String, ArrayList<Pagina>> indices = new HashMap<String, ArrayList<Pagina>>();
		int cout = 0;

		while (arquivo.scanner.hasNext()) {

			String aux = arquivo.lerString();
		

			String[] atr = limparStopWord(limparTexto(aux)).split(";");

			Pagina pagina = new Pagina(cout, atr[5], atr[0], atr[4], atr[2], atr[1], atr[3]);
			pagina.toquenizar(dicionarioTitulo, dicionarioUva, dicionarioClassificacao, dicionarioVolume, dicionarioTeorA);
			paginas.add(pagina);
			cout++;
		}

		for (int i = 0; i < paginas.size(); i++) {
			for (Map.Entry<String, Integer> par : dicionarioTitulo.entrySet()) {
				if (paginas.get(i).titulo.contains(par.getKey())) {
					if (indices.get(par.getKey() + ".titulo") != null) {
						indices.get(par.getKey() + ".titulo").add(paginas.get(i));
					} else {

						ArrayList<Pagina> temp = new ArrayList<Pagina>();
						temp.add(paginas.get(i));
						indices.put(par.getKey() + ".titulo", temp);
					}
				}
			} // Titulo

			for (Map.Entry<String, Integer> par : dicionarioUva.entrySet()) {
				if (paginas.get(i).uva.contains(par.getKey())) {
					if (indices.get(par.getKey() + ".uva") != null) {
						indices.get(par.getKey() + ".uva").add(paginas.get(i));
					} else {

						ArrayList<Pagina> temp = new ArrayList<Pagina>();
						temp.add(paginas.get(i));
						indices.put(par.getKey() + ".uva", temp);
					}
				}
			} // Uva

			for (Map.Entry<String, Integer> par : dicionarioClassificacao.entrySet()) {
				if (paginas.get(i).teorAlcoolico.contains(par.getKey())) {
					if (indices.get(par.getKey() + ".teorAlcoolico") != null) {
						indices.get(par.getKey() + ".teorAlcoolico").add(paginas.get(i));
					} else {

						ArrayList<Pagina> temp = new ArrayList<Pagina>();
						temp.add(paginas.get(i));
						indices.put(par.getKey() + ".teorAlcoolico", temp);
					}
				}
			} // Classificacao
			
			for (Map.Entry<String, Integer> par : dicionarioVolume.entrySet()) {
				if (paginas.get(i).volume.contains(par.getKey())) {
					if (indices.get(par.getKey() + ".volume") != null) {
						indices.get(par.getKey() + ".volume").add(paginas.get(i));
					} else {

						ArrayList<Pagina> temp = new ArrayList<Pagina>();
						temp.add(paginas.get(i));
						indices.put(par.getKey() + ".volume", temp);
					}
				}
			} // Volume

			for (Map.Entry<String, Integer> par : dicionarioTeorA.entrySet()) {
				if (paginas.get(i).teorAlcoolico.contains(par.getKey())) {
					if (indices.get(par.getKey() + ".teorAlcoolico") != null) {
						indices.get(par.getKey() + ".teorAlcoolico").add(paginas.get(i));
					} else {

						ArrayList<Pagina> temp = new ArrayList<Pagina>();
						temp.add(paginas.get(i));
						indices.put(par.getKey() + ".teorAlcoolico", temp);
					}
				}
			} // TeorA

		} // Paginas

		for (Entry<String, ArrayList<Pagina>> par : indices.entrySet()) {
			arquivo.escrever(par.getKey() + ";");
			for (int i = 0; i < par.getValue().size(); i++) {
				if (i > 0) {
					arquivo.escrever((par.getValue().get(i).id - par.getValue().get(0).id) + ";");
				}else{
					arquivo.escrever(par.getValue().get(i).id + ";");
				}
			}

			arquivo.escrever("\n");
		}
		System.out.println("acabou");
		arquivo.fechar();

	}
	
	public static String limparTexto(String texto) {
		
		return texto.replaceAll(Pattern.quote(")"), "").replaceAll(Pattern.quote("("), "").replaceAll("-", "").replaceAll(Pattern.quote("/"), " ");
	}
	
	public static String limparStopWord(String texto) {
		return texto.replaceAll(" e ", " ").replaceAll(" de", "").replaceAll(" em ", " ");
	}
	

}
