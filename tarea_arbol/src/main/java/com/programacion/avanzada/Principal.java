package com.programacion.avanzada;

import com.programacion.avanzada.arbol.BinTree;
import com.programacion.avanzada.listas.Lista;

public class Principal {

    public static <T> Lista<T> take(Lista<T> ls, int n){
        return ls.isEmpty() || n <=0
                ?Lista.Empty
                :Lista.of(ls.head(),ls.tail().take(n-1))
                ;
    }

    public static <T> Lista<T> drop(Lista<T> ls, int n){
        return ls.isEmpty() || n <=0
                ?ls
                :ls.tail().drop(n-1)
                ;
    }


    public static void main(String[] args) {

        var ls = Lista.of(1,2,3,4,5,6,7,8);
        System.out.println(ls);

        System.out.println("take:" + take(ls,1));
        System.out.println("drop:" + drop(ls,1));

        System.out.println("Arbol:"+ BinTree.buildTree(ls));
        //System.out.println("Tamaño:"+ BinTree.buildTree(ls).size());
    }
}
