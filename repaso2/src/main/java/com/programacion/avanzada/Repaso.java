package com.programacion.avanzada;

import com.programacion.avanzada.listas.Lista;

public class Repaso {

    public static <T extends Comparable<T>> Lista<T>insertSort(Lista<T> ls){
        return insertSortAux(ls, sublista(ls));
    }

    public static <T extends Comparable<T>> Lista<T> insertSortAux(Lista<T>lsD, Lista<T>lsO){
        if(lsD.isEmpty()||lsD.tail().isEmpty()){
            return lsO;
        }else{/*
            if(lsO.head().compareTo(lsD.tail().head())<=0){
                return insertSortAux(lsD.tail(),lsO.insert(1, lsD.tail().head()));
            }else{
                return insertSortAux(lsD.tail(), lsO.insert(0,lsD.tail().head()));
            }*/
            return insertSortAux(lsD.tail(), compararTodo(lsD.tail().head(),lsO,lsO,0));
        }
    }

    public static <T> Lista<T> sublista(Lista<T> ls){
        return Lista.of(ls.head());
    }

    public static <T extends Comparable<T>> Lista<T> compararTodo(T value, Lista<T>ls, Lista<T> lsR, int cont){
        if(ls.isEmpty()||value.compareTo(ls.head())<=0){
            return lsR.insert(cont,value);
        }else {
            return compararTodo(value, ls.tail(),lsR,cont+1);
        }
    }
}
