package com.programacion.avanzada;

import com.programacion.avanzada.listas.Lista;
import com.programacion.avanzada.recursion.TailCall;
import static com.programacion.avanzada.recursion.TailCall.*;
public class TestRecursion {

    static  Integer sumOriginal(Integer x, Integer y){
        return y==0
                ? x
                : sumOriginal(x+1,y-1);
    }

    static TailCall<Integer> sumRec(Integer x, Integer y){
        return y==0
                ? tailReturn(x)
                : tailSuspend(()->sumRec(x+1,y-1));//asi usamos la memoria del heap
    }

    static <T> TailCall<Lista<T>> lsRec (T ...elem){
        var ls = Lista.of2(elem);
        return !ls.isEmpty()
                ? tailReturn(ls)
                : tailSuspend(()->lsRec(elem))
                ;
    }

    public static void main(String[] args) {

        /*var ret = tailReturn(10);

        var n1 = tailSuspend(ret);
        var n2 = tailSuspend(n1);
        var n3 = tailSuspend(n2);

        //[n1,n2,n3,ret]
        var res = n3.eval();
        System.out.println(res);*/
        /*var n3 = tailSuspend(
                ()->tailSuspend(
                        ()->tailSuspend(
                                ()->tailReturn(10))));
        System.out.println(n3.eval());

        var ret = sumRec(3,500_000_000);
        var suma = ret.eval();
        System.out.println(suma);
*/
        //*********************************
        var ls = lsRec(1,2,3);

        System.out.println(ls);

    }
}
