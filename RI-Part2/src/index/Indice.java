package index;

import java.util.ArrayList;

public class Indice {
	ArrayList<Pagina> paginas;
	String atributo;
	
	public Indice(String atributo) {
		this.atributo = atributo;
		paginas = new ArrayList<Pagina>();
	}
	
	public void adicionarPagina(Pagina pagina) {
		paginas.add(pagina);

	}
	
	@Override
	public String toString() {
		String aux = "";
		for (int i = 0; i < paginas.size(); i++) {
			aux = aux + paginas.get(i).url + "\n";
		}
		return aux;
	}

}
