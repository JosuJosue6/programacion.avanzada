package com.programacion.avanzada.listas;

public class Principal {
    public static void main(String[] args) {

        var n5 = new Cons<>(5,Lista.Empty);
        var n4 = new Cons<>(4,n5);
        var n3 = new Cons<>(3,n4);
        var n2 = new Cons<>(2,n3);
        var n1 = new Cons<>(1,n2);

        System.out.println(n1);

        System.out.println("empty: "+n1.isEmpty());
        System.out.println("empty: "+Lista.Empty.isEmpty());

        var tmp = n1;
        /*while (!tmp.isEmpty()){
            System.out.println(tmp.head());
            tmp = tmp.tail();
        }*/


        Lista<Integer> ls2 = new Cons<>(1,
                new Cons<>(2,
                        new Cons<Integer>(3,
                                new Cons<>(4,
                                        new Cons<>(5,
                                                Lista.Empty)))));
        System.out.println(ls2);
    }
}
