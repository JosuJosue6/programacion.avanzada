package com.programacion.avanzada.lista;

import com.programacion.avanzada.recursion.TailCall;

import java.util.function.Function;
import java.util.function.Predicate;

import static com.programacion.avanzada.recursion.TailCall.*;

public interface ListaTC<T> {

    ListaTC Empty = new EmptyTC();

    T head();
    ListaTC<T> tail();

    boolean isEmpty();

    static <T> ListaTC<T> of (T h, ListaTC<T> t){
        return new ConsTC<>(h,t);
    }

    static <T> ListaTC<T> of(T ... elems){
        var tmp = ListaTC.Empty;
        for (int i=elems.length-1;i>=0;i--){
            tmp = new ConsTC(elems[i],tmp);
        }
        return tmp;
    }

    default int count(){
        return 1 + tail().count();
    }

    default ListaTC<T> prepend(T elem){
        return ListaTC.of(elem, this);
    }

    default ListaTC<T> append(T elem){
        return  this.isEmpty()
                ? ListaTC.of(elem, ListaTC.Empty)
                : ListaTC.of(this.head(), this.tail().append(elem))
                ;
    }

    default ListaTC<T> insert(int index, T elem){
        return index == 0
                ? ListaTC.of(elem, this)
                : ListaTC.of(this.head(), this.tail().insert(index-1,elem))
                ;
    }

    default T get(int index){
        return index == 0
                ? this.head()
                : this.tail().get(index-1);
    }

    default ListaTC<T> take (int n){
        return n<=0||this.isEmpty()
                ? ListaTC.Empty
                : ListaTC.of(this.head(), this.tail().take(n-1));
    }

    default ListaTC<T> drop (int n){
        return n<=0 || this.isEmpty()
                ? this
                : this.tail().drop(n-1);
    }

    default ListaTC<T> concat (ListaTC<T> ls){
        return this.isEmpty()
                ? ls
                : ListaTC.of(this.head(), this.tail().concat(ls));
    }

    default <U> ListaTC<U> map(Function<T,U>fn){
        return isEmpty()
                ? ListaTC.Empty
                : ListaTC.of(fn.apply(this.head()), this.tail().map(fn));
    }

    default <U> U foldLeft( U identify, Function<U, Function<T,U>> fn){
        U ret = identify;
        var tmp = this;
        while(!tmp.isEmpty()){
            ret = fn.apply(ret).apply(tmp.head());
            tmp = tmp.tail();
        }
        return ret;
    }

    default <U> U foldRight(U identify, Function<T, Function<U,U>>fn){
        return this.isEmpty()
                ? identify
                : fn.apply(this.head()).apply(this.tail().foldRight(identify,fn));
    }

    default ListaTC<T> invertFolding(){
        return foldLeft(ListaTC.Empty, ls -> t-> ls.prepend(t));
    }

    default <U> ListaTC<U> mapFolding(Function<T,U>fn){
        return foldLeft(ListaTC.Empty, ls -> t -> ls.append(fn.apply(t)));
    }

    default <U> ListaTC<U> mapFold2(Function<T,U>fn){
        return foldRight(ListaTC.Empty, t-> ls-> ls.prepend(fn.apply(t)));
    }

    default Integer countFold (){
        return foldLeft(0,n->t->n+1);
    }

    default ListaTC<T> appendFold(T elem){
        return foldRight(ListaTC.of(elem), t -> ls ->ls.prepend(t));
    }

    default T reduce(T identity, Function<T,Function<T,T>>fn){
        return foldLeft(identity, u->t->fn.apply(u).apply(t));
    }

    default T reduce2(Function<T,Function<T,T>>fn){
        return this.tail().foldLeft(this.head(), u->t ->fn.apply(u).apply(t));
    }

    default ListaTC<T> takeFold(int n){
        return foldLeft(ListaTC.Empty, ls -> t -> ls.count()<n
                                                ?ls.append(t)
                                                : ls
                        );
    }

    default ListaTC<T> dropFold(int n ){
        int tot = this.count() -n;
        return foldRight(ListaTC.Empty, t-> ls -> ls.count()<tot
                ?ls.prepend(t)
                :ls);
    }

    //Imperativa
    static ListaTC<Integer> range (Integer start, Integer end){
        ListaTC<Integer> ret = ListaTC.Empty;

        Integer tmp = end-1;
        while(tmp >= start){
            ret = ListaTC.of(tmp,ret);
            tmp--;
        }
        return ret;
    }

    //recursiva
    static ListaTC<Integer> rangeRec(Integer start, Integer end){
       if(start<end){
           return ListaTC.of(start, range(start+1, end));
       }
       return ListaTC.Empty;
    }

    //-------------------
    static ListaTC<Integer> rangeAux(ListaTC<Integer> tmp, Integer start, Integer end) {
        if(start<end) {
            return rangeAux(tmp.prepend(start), start+1, end);
        }
        else {
            return tmp;
        }

    }

    static ListaTC<Integer> rangeP(Integer start, Integer end) {
        return rangeAux(ListaTC.Empty, start, end).invertFolding();
    }

    static <T> ListaTC<T> unfold(T start, Function<T,T> f, Predicate<T> p) {
        ListaTC<T> ret = ListaTC.Empty;
        var tmp = start;

        while(p.test(tmp)) {
            ret = ret.prepend(tmp);
            tmp = f.apply(tmp);
        }

        return ret.invertFolding();
    }

    static <T> ListaTC<T> unfoldRec(T start, Function<T,T> f, Predicate<T> p) {
        if(p.test(start)==false) {
            return ListaTC.Empty;
        }
        else {
            return ListaTC.of( start, unfoldRec(f.apply(start), f, p) );
        }
    }

    static <T> ListaTC<T> unfoldTailRecAux(ListaTC<T> tmp, T start, Function<T,T> f, Predicate<T> p) {
        if(p.test(start)==false) {
            return tmp;
        }
        else {
            return unfoldTailRecAux(tmp.prepend(start), f.apply(start), f, p);
        }
    }

    static <T> ListaTC<T> unfoldTailRec(T start, Function<T,T> f, Predicate<T> p) {
        return unfoldTailRecAux(ListaTC.Empty, start, f, p)
                .invertFolding();
    }


    /*
    *Instrucciones
    En base a lo revisado en la clase del día de hoy, implementar los algoritmos:

    1 range(start, end): versión tail recursiva, versión utilizando unfold
    2 unfold(start, function, predicate), versiones: imperativa, recursiva, tail recursiva
    */

    //Range tail recursivo ********************************************************************
    default ListaTC<Integer> rangeTail(Integer inicio, Integer fin, ListaTC<Integer> ls){
        return inicio<fin
                ? rangeTail(inicio+1, fin, ListaTC.of(fin-(inicio+1),ls))
                : ls;

        /*if (inicio<fin){
            return rangeTail(inicio+1, fin, Lista.of(fin-(inicio+1),ls),ls));
        }else {
            return ls;
        }*/
    }

    //Range unfold ********************************************************************
    default <T> ListaTC<T> rangeUnfold(T inicio, Function<T,T>fn, Predicate<T> valorV, ListaTC<T> ls){

        return valorV.test(inicio)
                ? rangeUnfold(fn.apply(inicio), fn, valorV, ListaTC.of(inicio,ls))
                : ls;

        /*if(valorV.test(inicio)){
            return rangeUnfold(fn.apply(inicio), fn, valorV, Lista.of(inicio,ls));
        }else{
            return ls;
        }*/
    }

    //Unfold imperativo ********************************************************************
    default <T> ListaTC<T> unfoldImperativo(T inicio, Function<T, T> fn, Predicate<T> valorV) {
        ListaTC<T> ls = ListaTC.Empty;
        while (valorV.test(inicio)) {
            ls = ListaTC.of(inicio, ls);
            inicio = fn.apply(inicio);
        }
        return ls;
    }

    //Unfold Recursivo ********************************************************************
    default <T> ListaTC<T> unfoldRecursivo(T inicio, Function<T, T> fn, Predicate<T> valorV, ListaTC<T> ls) {
        return valorV.test(inicio)
                ? ListaTC.of(inicio, unfoldImperativo(inicio, fn, valorV))
                : ls;

        /*if (valorV.test(inicio)) {
            return Lista.of(inicio, unfoldImperativo(inicio, fn, valorV))
        } else {
            return ls;
        }*/
    }

    //Unfold Tail recursivo ********************************************************************
    default  <T> ListaTC<T> unfoldTail(T inicio, Function<T, T> fn, Predicate<T> valorV, ListaTC<T> ls) {
        return valorV.test(inicio)
                ? unfoldTail(fn.apply(inicio), fn, valorV, ListaTC.of(inicio,ls))
                : ls;

        /*if (valorV.test(inicio)) {
            return unfoldTail(fn.apply(inicio), fn, valorV, Lista.of(inicio, ls));
        } else {
            return ls;
        }*/
    }

    //***************************************************************************
    //TAREA
    //Listas funcionales con métodos Tail-Recursivos
    //Count
    default TailCall<Integer> countR() {
        return countAux(0);
    }

    default TailCall<Integer> countAux(int n) {
        if (tail() == null) {
            return tailReturn(n);
        } else {
            return tailSuspend(() -> tail().countAux(n + 1));
        }
    }

    //Append
    default ListaTC<T> appendR(T elem) {
        return appendAux(elem, this, ListaTC.Empty).invertFolding();
    }

    private ListaTC<T> appendAux(T elem, ListaTC<T> ls, ListaTC<T> ls2) {
        if (ls.isEmpty()) {
            return ls2.prepend(elem);
        } else {
            return appendAux(elem, ls.tail(), ls2.prepend(ls.head()));
        }
    }


    //Prepend
    default ListaTC<T> prependR(T elem) {
        return ListaTC.of(elem, this);
    }






    //Insert
    /*default <T> TailCall<ListaTC<T>> insertR (int index, T elem){
        return index == 0
                ? tailReturn(ListaTC.of(elem, this))
                : tailSuspend(() -> ListaTC.insertR(index-1, elem));
    }*/


    //get
    default TailCall<Integer> getR(int index){
        return index == 0
                ? tailReturn((Integer) this.head())
                : tailSuspend(() -> this.tail().getR(index-1));
    }



    //take
    default <T> TailCall<ListaTC<T>> takeR (int n){
       return n<=0||this.isEmpty()
              ? tailReturn(ListaTC.Empty)
               : tailSuspend(() -> (TailCall<ListaTC<T>>) ListaTC.of(this.head(), this.tail().takeR(n-1)));
   }

   //drop
    default <T> TailCall<ListaTC<T>> dropR (int n){
        return n<=0 || this.isEmpty()
                ? tailReturn((ListaTC<T>) this)
                : tailSuspend(() -> this.tail().dropR(n-1));
    }


    //concat
    default <T> TailCall<ListaTC<T>> concatR (ListaTC<T> ls){
        return this.isEmpty()
                ? tailReturn(ListaTC.Empty)
                : tailSuspend(() -> (TailCall<ListaTC<T>>) ListaTC.of(this.head(), this.tail().concatR(ls)));
    }

    //map
    default  <T,U> TailCall<ListaTC<U>> mapR(Function<T,U> fn){
        return isEmpty()
                ? tailReturn(ListaTC.Empty)
                : tailSuspend(() -> (TailCall<ListaTC<U>>) ListaTC.of(fn.apply((T) this.head()), this.tail().mapR(fn)));
    }

    //invertir
    default ListaTC<T> invertirR() {
        return reverseAux(this, ListaTC.Empty);
    }

    private ListaTC<T> reverseAux(ListaTC<T> ls, ListaTC<T> ls2) {
        if (ls.isEmpty()) {
            return ls2;
        } else {
            return reverseAux(ls.tail(), ls2.prepend(ls.head()));
        }
    }



}
