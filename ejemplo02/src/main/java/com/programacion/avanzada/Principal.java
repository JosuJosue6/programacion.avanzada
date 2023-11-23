package com.programacion.avanzada;

import java.util.Optional;
import java.util.function.Function;

public class Principal {

    public static void main(String[] args) {
        Optional<Double> ret = Operacion2.division(4);
        System.out.println("Test Josue "+ ret);

        //Profe *****************************
        //Funcion
        Function<Integer, Optional<Integer>> fn = x->{
            if(x==0){
                return Optional.empty();
            }else {
                return Optional.of(1/x);

            }
        };

        var ret2 = fn.apply(0);
        System.out.println("Test  "+ ret2);

        var t =Operaciones.sumar(1,2);
        System.out.println("Test 2, suma: "+ t);
    }

}
