package com.programacion.avanzada;

import com.programacion.avanzada.listas.Lista;

import java.util.List;

public class Repaso {

    public static  <T extends Comparable<T>> T maximo(Lista<T> ls){

        return maximoAux(ls.head(),ls);
    }

    public static  <T extends Comparable<T>> T maximoAux(T val, Lista<T> ls){
        if(ls.tail().isEmpty()){
            return val;
        }else {

            if (val.compareTo(ls.tail().head())<=0){
                return maximoAux(ls.tail().head(), ls.tail());
            }else {
                return maximoAux(val, ls.tail());
            }
        }
    }


    public static  <T extends Comparable<T>> T min(Lista<T> ls){

        return minAux(ls.head(),ls);
    }

    public static  <T extends Comparable<T>> T minAux(T val, Lista<T> ls){
        if(ls.tail().isEmpty()){
            return val;
        }else {

            if (val.compareTo(ls.tail().head())>=0){
                return minAux(ls.tail().head(), ls.tail());
            }else {
                return minAux(val, ls.tail());
            }
        }
    }

    public static  <T extends Comparable<T>> T min2( Lista<T> ls){
        if(ls.tail().isEmpty()){
            return ls.head();
        }else {

            if (ls.head().compareTo(ls.tail().head())>=0){
                return minAux(ls.tail().head(), ls.tail());
            }else {
                return minAux(ls.head(), ls.tail());
            }
        }
    }

    public static <T extends Comparable<T>> Lista<T> sublista(Lista<T> ls){
        return sublistaAux(maximo(ls), ls, Lista.Empty);
    }

    public static <T extends Comparable<T>> Lista<T> sublistaAux(T value, Lista<T>ls, Lista<T> tmp){
        if(ls.isEmpty()){
            return tmp.invertFolding();
        }else{

            if(ls.head().compareTo(value)==0){
                return sublistaAux(value,ls.tail(),tmp);
            }else {

                return sublistaAux(value, ls.tail(),tmp.prepend(ls.head()));
            }
        }

    }

    public static <T extends Comparable<T>> Lista<T> burbuja(Lista<T> ls){
        return burbujaAux(ls, Lista.Empty) ;
    }

    public static <T extends Comparable<T>> Lista<T> burbujaAux(Lista<T> ls, Lista<T> ls2){
        if(ls.isEmpty()){
            return ls2.invertFolding();
        }else{
            return burbujaAux(sublistaAux(min(ls),ls,Lista.Empty),ls2.prepend(min(ls)));
        }

    }


    //Folding*******************************************

   /* public static <T extends Comparable<T>> T minLeft(Lista<T> ls) {
        return ls.foldLeft(ls.head(), (min, elem) -> {
            if (min.compareTo(elem) >= 0) {
                return elem;
            } else {
                return min;
            }
        });
    }*/


    /*public static <T extends Comparable<T>> T maxRight(Lista<T> ls) {
        return ls.foldRight(ls.head(), (elem, max) -> {
            if (elem.compareTo(max) <= 0) {
                return elem;
            } else {
                return max;
            }
        });
    }*/
}
