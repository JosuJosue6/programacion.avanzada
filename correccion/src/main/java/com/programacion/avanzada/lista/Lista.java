package com.programacion.avanzada.lista;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Lista <T>{

    private final Nodo<T> start;

    Lista(Nodo<T> start){
        this.start = start;
    }

    public static <T> Lista <T> of(T ... elems){
        Nodo<T> tmp = null;
        for(int i = elems.length -1; i>=0; i--){
            tmp = new Nodo<>(elems[i],tmp);
        }

        return new Lista<>(tmp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        var tmp = this.start;
        while(tmp!=null){
            sb.append(tmp.elem()).append(",");
            tmp.tmp.next();
        }
        sb = sb.delete(sb.length()-1, sb.length());
        sb.append("]");
        return sb.toString();
    }

    private Lista<T> invertit(){

    }

    //------------------------------------

    public Lista<T> prepend(T elem){

        Nodo<T> tmp = new Nodo<>(elem, this.start);


    }

    public Lista<T> append(T elem){
        Nodo<T> nodo = null;
        Nodo<T>tmp = this.start;
         while(tmp != null){
             nodo = new Nodo<>(tmp.elem(), nodo);
             tmp = tmp.next();
         }
         nodo = new Nodo<>(elem,nodo);
         return new Lista<>(nodo).invertit();
    }

    public Lista<T> insert(int index,T elem){
        Nodo<T> nodo = null;
        Nodo<T>tmp = this.start;
        int i =0;
        while(tmp != null && i<index){
            nodo = new Nodo<>(tmp.elem(), nodo);
            tmp = tmp.next();
            i++;
        }
        nodo = new Nodo<>(elem,nodo);
        while(tmp != null){
            nodo = new Nodo<>(tmp.elem(), nodo);
            tmp = tmp.next();
        }
        return new Lista<>(nodo).invertit();
    }

    public Lista<T> remove(int index){
        Nodo<T> nodo = null;
        Nodo<T>tmp = this.start;
        int i =0;
        while(tmp != null && i<index){
            nodo = new Nodo<>(tmp.elem(), nodo);
            tmp = tmp.next();
            i++;
        }
        tmp = tmp.next();
        while(tmp != null){
            nodo = new Nodo<>(tmp.elem(), nodo);
            tmp = tmp.next();
        }
        return new Lista<>(nodo).invertit();
    }

    public Lista<T> get(int index){
        Nodo<T> nodo = null;
        Nodo<T>tmp = this.start;
        int i =0;
        while(tmp != null && i<index){
            nodo = new Nodo<>(tmp.elem(), nodo);
            tmp = tmp.next();
            i++;
        }

        return tmp.elem();
    }

    public void forEach(Consumer<T> ac){
        Nodo<T>tmp = this.start;
        while(tmp != null ){
            ac.accept(tmp.elem());
            tmp = tmp.next();
        }
    }
    //-----------------
    //t->Lista<t>->Lista<T>

    public static <T>Function<T, Function<Lista<T>>> fnPrepend(){
        return elem ->ls->ls.prepend(elem);
    }

    //Lista<T>xT -> Lista<T>
    public  static <T> Function<Lista<T>,Function<T,Lista<T>>> fnAppend(){return elem ->ls->ls.append}
}
