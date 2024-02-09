package com.programacion.avanzada.lista;

class EmptyTC implements ListaTC {

    @Override
    public String toString() {
        return "Empty";
    }

    @Override
    public Object head() {
        return null;
    }

    @Override
    public ListaTC tail() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    public int count(){
        return 0;
    }
}
