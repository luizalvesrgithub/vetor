package br.com.util;

public class Util {
    public static <K, V, T> boolean compare(UtilToGenerics<K, V, T> p1, UtilToGenerics<K, V, T> p2) {
        return p1.getKey().equals(p2.getKey()) &&
               p1.getValue().equals(p2.getValue());
    }
}