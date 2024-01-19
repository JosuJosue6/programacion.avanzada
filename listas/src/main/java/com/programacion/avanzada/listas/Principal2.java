package com.programacion.avanzada.listas;

public class Principal2 {
    public static void main(String[] args) {
        var ls = Lista.of(1,2,3,4,5);
        System.out.println(ls);
        System.out.println(ls.count());
        System.out.println("prepend: "+ls.prepend(99));
        System.out.println("insert: "+ls.insert(2,99));

        var ls2 = Lista.of("aa","bb","cc");
        System.out.println(ls2);
        System.out.println(ls2.count());
        System.out.println(ls2.append("xyz"));
    }
}
