package com.programacion.avanzada;

import com.programacion.avanzada.listas.Lista;

public class Principal {
    public static void main(String[] args) {
        var ls = Lista.of(1,3,5,2,6);
        System.out.println(ls);
        System.out.println(Merge.split(ls).izquierda());

        System.out.println(Merge.merge(ls));

        System.out.println("merge 2: "+Merge.merge(Merge.split(ls).izquierda(),Merge.split(ls).derecha()));
    }
}
