package com.programacion.avanzada.arbol;

record Cons<T>(T head, BinTree<T> tail) implements BinTree<T> {
    @Override
    public String toString() {

        return String.format("Head "+head+" Nodos "+ tail.toString());
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
