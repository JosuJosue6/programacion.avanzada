package com.programacion.avanzada.listas;

import java.util.function.Function;

public class Principal3 {
    public static void main(String[] args) {

        Executable ex0 = () -> {
        }; // Neutro

        Executable ex1 = () -> {
            ex0.exec();
            System.out.println(1);
        };

        Executable ex2 = () -> {
            ex1.exec();
            System.out.println(2);
        };

        Executable ex3 = () -> {
            ex2.exec();
            System.out.println(3);
        };

        Executable ex4 = () -> {
            ex3.exec();
            System.out.println(4);
        };

        Executable ex5 = () -> {
            ex4.exec();
            System.out.println(5);
        };

        ex5.exec();

        ////////////////////////////////////////////////////
        System.out.println("metodo2");
        Effect<Integer> printInteger = x -> {
            System.out.printf("%d", x);
            System.out.println();
        };

        Function<Executable, Function<Integer, Executable>> fn = ex -> elem -> () -> {
            ex.exec();
            printInteger.apply(elem);
        };

        // Function<Executable, Function<Integer, Executable>> fn1 = ex -> elem -> {
        //     ex.exec();
        //     return () -> {
        //         printInteger.apply(elem);
        //     };
        // };

        // var ex11 = fn.apply(ex0).apply(1);
        // var ex22 = fn.apply(ex11).apply(2);
        // var ex33 = fn.apply(ex22).apply(3);
        // var ex44 = fn.apply(ex33).apply(4);
        // var ex55 = fn.apply(ex44).apply(5);

        Lista<Integer> ls = Lista.of2(1, 2, 3, 4, 5);
        var exec = ls.foldLeft(ex0, fn);
        exec.exec();
    }
}