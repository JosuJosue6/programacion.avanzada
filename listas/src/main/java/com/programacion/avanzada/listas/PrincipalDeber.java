package com.programacion.avanzada.listas;

public class PrincipalDeber {

    public static void main(String[] args) {

        Lista<Integer> ls = Lista.Empty;
        System.out.println("Range TailRecursivo");
        var tmp = ls.rangeTail(0,5, ls);
        System.out.println(tmp +"\n");

        System.out.println("Range Unfolding");
        //var tmp2 = ls.rangeUnfold(0, x->x+1, x->x<5,ls);
        var tmp2 = ls.rangeUnfold(7, x->x-1, x->x>0,ls);
        System.out.println(tmp2+"\n");

        System.out.println("Unfolding imperativo:");
        //var tmp3 = ls.unfoldImperativo(0, x->x+2, x->x<7);
        var tmp3 = ls.unfoldImperativo(7, x->x-2, x->x>0);
        System.out.println(tmp3+"\n");

        System.out.println("Unfolding recursivo:");
        //var tmp4 = ls.unfoldRecursivo(0, x->x+2, x->x<7,ls);
        var tmp4 = ls.unfoldRecursivo(7, x->x-2, x->x>0,ls);
        System.out.println(tmp4+"\n");

        System.out.println("Unfolding TailRecursivo:");
        //var tmp5 = ls.unfoldTail(0, x->x+2, x->x<7,ls);
        var tmp5 = ls.unfoldTail(7, x->x-2, x->x>0,ls);
        System.out.println(tmp5);
    }
}
