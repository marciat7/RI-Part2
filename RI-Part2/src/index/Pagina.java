package index;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Pagina {

	String url, titulo, classificacao, uva, teorAlcoolico;
	int volume, id;
	
	
	public Pagina(int id, String url, String titulo, String classificacao, String uva, 
			int volume, String teorAlcoolico) {
		this.id = id;
		this.url = url;
		this.titulo = titulo;
		this.classificacao = classificacao;
		this.uva = uva;
		this.volume = volume;
		this.teorAlcoolico = teorAlcoolico;
		
	}
	
	public void toquenizar(Map<String,Integer> dicionarioTitulo, Map<String,Integer> dicionarioUva, Map<String,Integer> dicionarioClassificacao) {
		
		String[] atrTitulo = titulo.split(" ");
		
		for (int i = 0; i < atrTitulo.length; i++) {
			int x = 0; // Pega o número de vezes que ela apareceu
			if (dicionarioTitulo.get(atrTitulo[i]) != null) {
				x = dicionarioTitulo.get(atrTitulo[i]);
			}
			dicionarioTitulo.put(atrTitulo[i], x+1);			
			
			}
		
		String[] atrUva = uva.split(" ");
		
		for (int i = 0; i < atrUva.length; i++) {
			int x = 0; 
			if (dicionarioUva.get(atrUva[i]) != null) {
				x = dicionarioUva.get(atrUva[i]);
			}
			dicionarioUva.put(atrUva[i], x+1);
		}
		
		String[] atrClassificacao = classificacao.split(" ");
		
		for (int i = 0; i < atrClassificacao.length; i++) {
			int x = 0; 
			if (dicionarioClassificacao.get(atrClassificacao[i]) != null) {
				x = dicionarioClassificacao.get(atrClassificacao[i]);
			}
			dicionarioClassificacao.put(atrClassificacao[i], x+1);
		}
	}
	
	
	

}
