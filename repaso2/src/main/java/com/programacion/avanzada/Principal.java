package com.programacion.avanzada;

import com.programacion.avanzada.listas.Lista;

public class Principal {
    public static void main(String[] args) {
        var ls = Lista.of(12,10,16,11,9,7);

        System.out.println(Repaso.insertSort(ls));
    }
}
