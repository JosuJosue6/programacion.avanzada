package com.programacion.avanzada;

import com.programacion.avanzada.listas.Lista;

public class Principal {
    public static void main(String[] args) {
        var ls = Lista.of(4,6,2,3,8);

        System.out.println("Lista: "+ ls);
        System.out.println("Maximo: "+Repaso.maximo(ls));
        System.out.println("Minimo: "+Repaso.min(ls));
        //System.out.println(Repaso.min2(ls));

        System.out.println("sublista: "+ Repaso.sublista(ls));
        System.out.println("Burbuja: "+Repaso.burbuja(ls));

    }
}
