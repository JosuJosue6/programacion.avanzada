package com.programacion.avanzada.arbol;

public class Empty implements BinTree{
    @Override
    public Object head() {
        return null;
    }

    @Override
    public BinTree tail() {
        return null;
    }

    public boolean isEmpty() {
        return true;
    }

    @Override
    public String toString() {
        return "Empty";
    }

    public int count(){
        return 0;
    }
}
