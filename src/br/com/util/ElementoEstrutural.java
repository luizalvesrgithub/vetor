package br.com.util;

import java.util.Observable;
import java.util.Observer;

public class ElementoEstrutural extends Observable {

	private int edicao;

	public void setNovaEdicao(int novaEdicao) {
		this.edicao = novaEdicao;
		
		setChanged();
		notifyObservers();
	}
	
	public int getEdicao() {
		return this.edicao;
	}
	
	public static void main(String[] args) {
		//poderia receber a nova edicao atraves de um recurso externo
		int novaEdicao = 3;
		ElementoEstrutural elestrut = new ElementoEstrutural();		
		ProjetoEstrutural projeto = new ProjetoEstrutural(elestrut);
		
		elestrut.setNovaEdicao(novaEdicao);
	}

}

class ProjetoEstrutural implements Observer {
	
	Observable projetoEstrutura;
	
	int idElemento;
	
	public ProjetoEstrutural(Observable elementoproj) {
		this.projetoEstrutura = elementoproj;
		projetoEstrutura.addObserver(this);
	}
	
	@Override
	public void update(Observable projetoInSubject, Object arg1) {
		if (projetoInSubject instanceof ElementoEstrutural) {
			ElementoEstrutural elementoEstrutural = (ElementoEstrutural) projetoInSubject;
			idElemento = elementoEstrutural.getEdicao();
			System.out.println("Atenção, já chegou a mais uma edição da Revista Informatica. " +
					"Esta é a sua edição número: " + idElemento);
		}
	}	
}