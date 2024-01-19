package com.programacion.avanzada;

import com.programacion.avanzada.lista.Lista;

import java.util.List;

public class Principal {

    public static void main(String[] args) {
        List<Integer> ls = Lista.of(1,2,3,4,5);

        System.out.println(ls);

        var ls1 = ls.prepend(99);
        System.out.println(ls1);

        var ls2 = ls.append(99);
        System.out.println(ls2);

        var ls3 = ls.append(2,99);
        System.out.println(ls3);

        var ls4 = ls.remove(2);
        System.out.println(ls4);

        //------------------------------
        //FUNCIONES

        //prepend:
        Lista<Integer> fn1 = Lista.<Integer>fnPrepend()
                .apply(99)
                .apply(ls);
    }


}
