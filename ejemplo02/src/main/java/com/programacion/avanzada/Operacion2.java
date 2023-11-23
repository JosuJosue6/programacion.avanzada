package com.programacion.avanzada;

import java.util.Optional;

public class Operacion2 {

    public static Optional<Double> division(int x){
        if (x == 0 ){
            return Optional.empty();
        }else{
            return Optional.of(1.0/x);
        }
    }
}
