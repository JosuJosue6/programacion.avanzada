package com.programacion.avanzada.arbol;

record Cons<T>(T head, BinTree<T> nodos) implements BinTree<T> {
    @Override
    public String toString() {

        return String.format("%s: ,%s: ", head, nodos.toString());
    }
}
