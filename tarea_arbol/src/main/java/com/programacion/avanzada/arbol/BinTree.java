package com.programacion.avanzada.arbol;

import com.programacion.avanzada.Principal;
import com.programacion.avanzada.listas.Lista;

public interface BinTree <T>{

    BinTree Empty = new Empty();

    T head();

    BinTree<T> tail();

    boolean isEmpty();

    static <T> BinTree<T> of (T h, BinTree<T> t){
        return new Cons<>(h,t);
    }

    static <T> BinTree<T> of(T ... elems){
        var tmp = BinTree.Empty;
        for (int i=elems.length-1;i>=0;i--){
            tmp = new Cons(elems[i],tmp);
        }
        return tmp;
    }

    default Integer size(){
        return 1 + this.tail().size();
    }

    static <T>Lista<T> mitadIzquierda(Lista<T> ls){
        return Principal.take(ls, ls.count()/2);
    }

    static <T>Lista<T> mitadDerecha(Lista<T> ls){
        return Principal.drop(ls, ls.count()/2);
    }

    static <T> BinTree<T> buildTreeAux(Lista<T> ls, BinTree<T> binTree){
        if(ls.isEmpty()){
            return binTree;
        }else {
            var lsH = ls.head();
            var lsT = ls.drop(1);
            var lsI = mitadIzquierda(lsT);
            var lsD = mitadDerecha(lsT);
            var tmp = BinTree.of(lsH,lsI.head(), lsD.head());
            if (!lsI.isEmpty()){
                return buildTreeAux(lsI,tmp);
            }else {
                return buildTreeAux(lsD,tmp);
            }

        }
    }
    static <T> BinTree<T> buildTree(Lista<T> ls){

        return buildTreeAux(ls, BinTree.Empty);
    }
}
