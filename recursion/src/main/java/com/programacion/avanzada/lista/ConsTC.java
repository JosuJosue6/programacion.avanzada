package com.programacion.avanzada.lista;

record ConsTC<T>(T head, ListaTC<T> tail)implements ListaTC<T> {

    @Override
    public String toString() {
        return String.format("[%s,%s]", head, tail.toString());
    }


    @Override
    public boolean isEmpty() {
        return false;
    }
}
