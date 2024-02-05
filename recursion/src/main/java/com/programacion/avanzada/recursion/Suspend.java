package com.programacion.avanzada.recursion;

import com.programacion.avanzada.recursion.TailCall;

import java.util.function.Supplier;

final class Suspend <T> implements TailCall<T> {

    private Supplier<TailCall<T>> next;

    //Constructor
    Suspend(Supplier<TailCall<T>> next){
        this.next = next;
    }

    @Override
    public T eval() {
        TailCall<T> tmp = this;
        while(tmp.isSuspend()){
            tmp = tmp.resume();
        }
        return tmp.eval();
    }

    @Override
    public TailCall<T> resume() {
        return next.get();//get del supplier

    }

    @Override
    public boolean isSuspend() {
        return true;
    }
}
