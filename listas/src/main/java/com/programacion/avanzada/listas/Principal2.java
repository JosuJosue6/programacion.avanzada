package com.programacion.avanzada.listas;

public class Principal2 {
    public static void main(String[] args) {
        var ls = Lista.of2(1,2,3,4,5);
        System.out.println(ls);
        System.out.println(ls.count());
        System.out.println("prepend: "+ls.prepend(99));
        System.out.println("insert: "+ls.insert(2,99));
        System.out.println("get: " + ls.get(2));
        System.out.println("take(2): "+ls.take(2));
        System.out.println("take(20): "+ls.take(20));
        System.out.println("take(2): "+Lista.Empty.take(2));
        System.out.println("drop(2): "+ls.drop(2));
        System.out.println("concat: "+ls.concat(Lista.of2(6,7,8)));

        var ls2 = Lista.of2("aa","bb","cc");
        System.out.println(ls2);
        System.out.println(ls2.count());
        System.out.println(ls2.append("xyz"));

        System.out.println("invertir fold: " + ls.invertFolding());

        System.out.println("mapping foldL: " + ls.mapFolding(t -> "x"+t));

        System.out.println("mapping foldR: " + ls.mapFold2(t -> "x"+t));

        System.out.println("count folding: " + ls.countFold());

        System.out.println("Append folding: " + ls.appendFold(99) );

        System.out.println("Reduce folding: " + ls.reduce(0, x -> y -> x+y));

        System.out.println("Reduce folding2: " + ls.reduce2( x -> y -> x+y));

        System.out.println("Take folding: " + ls.takeFold(2));

        System.out.println("Drop folding: " + ls.dropFold(2));


    }
}
