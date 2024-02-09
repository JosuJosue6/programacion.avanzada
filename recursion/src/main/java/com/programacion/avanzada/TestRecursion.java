package com.programacion.avanzada;

import com.programacion.avanzada.lista.ListaTC;
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
        //*********************************Tarea
        var ls = ListaTC.of(1,2,3,4,5);
        System.out.println("Count: "+ls.countR().eval());

        System.out.println("Append: "+ls.appendR(99));

        System.out.println("Get: "+ls.getR(1).eval());

        //System.out.println("Take: "+ls.takeR(2).eval());

        System.out.println("Drop: "+ls.dropR(1).eval());

        var ls2 =ListaTC.of(6,7,8);
        System.out.println("Concat: "+ls.concat(ls2));

        System.out.println("Map: "+ls.map(x->x+1));

        System.out.println("invertir: "+ls.invertirR());

    }
}
