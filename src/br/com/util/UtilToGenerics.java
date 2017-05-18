package br.com.util;

public class UtilToGenerics<K, V, T> {

	private K key;
	private V value;
	private T tabela;

	public UtilToGenerics(K key, V value, T tabela) {
		
		this.key = key;
		this.value = value;
		this.tabela = tabela;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}
	
	public T getTabela() {
		return tabela;
	}

	public void setTabela(T tabela) {
		this.tabela = tabela;
	}
	
	public static void main(String args[]) throws Exception {
		UtilToGenerics<Integer, String, String> p1 = new UtilToGenerics<>(1, "apple", "tabela");
		UtilToGenerics<Integer, String, String> p2 = new UtilToGenerics<>(2, "pear", "pastabela");
		boolean same = Util.<Integer, String, String>compare(p1, p2);
		System.out.println(same?"são iguais":"Diferentes");
	}
}