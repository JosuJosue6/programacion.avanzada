package com.programacion.avanzada.listas;

public class Principal2 {
    public static void main(String[] args) {
        var ls = Lista.of(1,2,3,4,5);
        System.out.println(ls);
        //System.out.println(ls.count());
        System.out.println("prepend: "+ls.prepend(99));
        System.out.println("insert: "+ls.insert(2,99));

        var ls2 = Lista.of("aa","bb","cc");
        System.out.println(ls2);
        //System.out.println(ls2.count());
        System.out.println(ls2.append("xyz"));
        //******************************************
        System.out.println("take(2): "+ls.take(2));
        System.out.println("take(20): "+ls.take(20));
        System.out.println("take(2): "+Lista.Empty.take(2));
        System.out.println("drop(2); "+ls.drop(2));
        System.out.println("concat: "+ls.concat(Lista.of(6,7,8)));
        System.out.println("invertir: " +ls.invertir());
        System.out.println("fold-map: "+ ls.mapFold(t->"x"+t));
        System.out.println("fold-map2: "+ ls.mapFold2(t->"x"+t));
        System.out.println("fold-count: " +ls.countFold());
        System.out.println("fold-reduce: " +ls.reduce(0,x->y->x+y));
        System.out.println("fold-reduce: " +ls.reduce(x->y->x+y));
    }
}
