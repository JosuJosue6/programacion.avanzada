package com.programacion.avanzada.listas;

class Empty implements Lista{
    @Override
    public Object head() {
        return null;
    }

    @Override
    public Lista tail() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
