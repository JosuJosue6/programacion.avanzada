package com.programacion.avanzada.recursion;

public interface TailCall <T>{
    T eval ();
    TailCall<T> resume();
    boolean isSuspend();

    static <T> TailCall<T> tailReturn(T value){
        return new Return<>(value);
    }

    static <T> TailCall<T> tailSuspend(TailCall<T> next){
        return new Suspend<>(next);
    }
}
