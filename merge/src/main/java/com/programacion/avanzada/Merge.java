package com.programacion.avanzada;

import com.programacion.avanzada.listas.Lista;

public class Merge {

    //separar en dos listas por posicion par e impar
    public static <T> DosListas<T> split (Lista<T> ls){

        return splitAux(ls, Lista.Empty, Lista.Empty, true);
    }

    public static <T>DosListas<T> splitAux(Lista<T> ls, Lista<T> lsI, Lista<T> lsD, boolean isLeft){
        if(ls.isEmpty()){
            return new DosListas<>(lsI.invertFolding(),lsD.invertFolding());
        }else {
            if (isLeft){
                return splitAux(ls.tail(), lsI.prepend(ls.head()), lsD, false);
            }else{
                return splitAux(ls.tail(),lsI, lsD.prepend(ls.head()),true);
            }
        }
    }

    //Ordenar
    public static <T extends Comparable<T>> Lista<T> merge(Lista<T> ls){

        if(ls.isEmpty()||ls.tail().isEmpty()){
            return ls;
        }else {
            DosListas<T> dLs = split(ls);
            var lsI = dLs.izquierda();
            var lsD = dLs.derecha();

            lsI = merge(lsI);
            lsD = merge(lsD);

            return mergeAux(lsI,lsD);
        }

    }

    private static <T extends Comparable<T>> Lista<T> mergeAux(Lista<T> lsI, Lista<T> lsD){
        Lista<T> tmp = Lista.Empty;

        while (!lsI.isEmpty() && !lsD.isEmpty()) {
            if (lsI.head().compareTo(lsD.head()) <= 0) {
                tmp = tmp.append(lsI.head());
                lsI = lsI.tail();
            } else {
                tmp = tmp.append(lsD.head());
                lsD = lsD.tail();
            }
        }

        while (!lsI.isEmpty()) {
            tmp = tmp.append(lsI.head());
            lsI = lsI.tail();
        }

        while (!lsD.isEmpty()) {
            tmp = tmp.append(lsD.head());
            lsD = lsD.tail();
        }

        return tmp;
    }
    //***************************************************************
    static <T extends Comparable<T>> Lista<T> merge(Lista<T> left, Lista<T> right) {
        return mergeAux(left, right, Lista.Empty);
    }

    private static <T extends Comparable<T>> Lista<T> mergeAux(Lista<T> left, Lista<T> right, Lista<T> acc) {
        if (left.isEmpty()) {
            return acc.concat(right);
        } else if (right.isEmpty()) {
            return acc.concat(left);
        } else {
            if (left.head().compareTo(right.head()) <= 0) {
                return mergeAux(left.tail(), right, acc.append(left.head()));
            } else {
                return mergeAux(left, right.tail(), acc.append(right.head()));
            }
        }
    }
}
