package com.programacion.avanzada.recursion;

import static com.programacion.avanzada.recursion.TailCall.*;
public class TestRecursion {

    static  Integer sumOriginal(Integer x, Integer y){
        return y==0
                ? x
                : sumOriginal(x+1,y-1);
    }

    static TailCall<Integer> sumRec(Integer x, Integer y){
        return y==0
                ?tailReturn(x)
                :tailSuspend(sumRec(x+1,y-1));//asi usamos la memoria del heap
    }

    public static void main(String[] args) {

        /*var ret = tailReturn(10);

        var n1 = tailSuspend(ret);
        var n2 = tailSuspend(n1);
        var n3 = tailSuspend(n2);

        //[n1,n2,n3,ret]
        var res = n3.eval();
        System.out.println(res);*/
        var n3 = tailSuspend(tailSuspend(tailSuspend(tailReturn(10))));
        System.out.println(n3.eval());

        var ret = sumRec(3,5);
        var suma = ret.eval();
        System.out.println(suma);
    }
}
