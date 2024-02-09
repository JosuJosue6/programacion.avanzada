package com.programacion.avanzada.listas;


//import com.programacion.avanzada.recursion.TailCall;

import java.util.function.Function;
import java.util.function.Predicate;

//import static com.programacion.avanzada.recursion.TailCall.*;


public interface Lista<T> {

    Lista Empty = new Empty();

    T head();
    Lista<T> tail();

    boolean isEmpty();

    static <T> Lista<T> of (T h, Lista<T> t){
        return new Cons<>(h,t);
    }

    static <T> Lista<T> of(T ... elems){
        var tmp = Lista.Empty;
        for (int i=elems.length-1;i>=0;i--){
            tmp = new Cons(elems[i],tmp);
        }
        return tmp;
    }

    default int count(){
        return 1 + tail().count();
    }

    default Lista<T> prepend(T elem){
        return Lista.of(elem, this);
    }

    default Lista<T> append(T elem){
        return  this.isEmpty()
                ? Lista.of(elem,Lista.Empty)
                : Lista.of(this.head(), this.tail().append(elem))
                ;
    }

    default Lista<T> insert(int index, T elem){
        return index == 0
                ? Lista.of(elem, this)
                : Lista.of(this.head(), this.tail().insert(index-1,elem))
                ;
    }

    default T get(int index){
        return index == 0
                ? this.head()
                : this.tail().get(index-1);
    }

    default Lista<T> take (int n){
        return n<=0||this.isEmpty()
                ? Lista.Empty
                : Lista.of(this.head(), this.tail().take(n-1));
    }

    default Lista<T> drop (int n){
        return n<=0 || this.isEmpty()
                ? this
                : this.tail().drop(n-1);
    }

    default Lista<T> concat (Lista<T> ls){
        return this.isEmpty()
                ? ls
                : Lista.of(this.head(), this.tail().concat(ls));
    }

    default <U> Lista<U> map(Function<T,U>fn){
        return isEmpty()
                ? Lista.Empty
                : Lista.of(fn.apply(this.head()), this.tail().map(fn));
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

    default Lista<T> invertFolding(){
        return foldLeft(Lista.Empty, ls -> t-> ls.prepend(t));
    }

    default <U> Lista<U> mapFolding(Function<T,U>fn){
        return foldLeft(Lista.Empty, ls -> t -> ls.append(fn.apply(t)));
    }

    default <U> Lista<U> mapFold2(Function<T,U>fn){
        return foldRight(Lista.Empty, t-> ls-> ls.prepend(fn.apply(t)));
    }

    default Integer countFold (){
        return foldLeft(0,n->t->n+1);
    }

    default Lista<T> appendFold(T elem){
        return foldRight(Lista.of(elem), t -> ls ->ls.prepend(t));
    }

    default T reduce(T identity, Function<T,Function<T,T>>fn){
        return foldLeft(identity, u->t->fn.apply(u).apply(t));
    }

    default T reduce2(Function<T,Function<T,T>>fn){
        return this.tail().foldLeft(this.head(), u->t ->fn.apply(u).apply(t));
    }

    default Lista<T> takeFold(int n){
        return foldLeft(Lista.Empty, ls ->t -> ls.count()<n
                                                ?ls.append(t)
                                                : ls
                        );
    }

    default Lista<T> dropFold(int n ){
        int tot = this.count() -n;
        return foldRight(Lista.Empty, t-> ls -> ls.count()<tot
                ?ls.prepend(t)
                :ls);
    }

    //Imperativa
    static Lista<Integer> range (Integer start, Integer end){
        Lista<Integer> ret = Lista.Empty;

        Integer tmp = end-1;
        while(tmp >= start){
            ret = Lista.of(tmp,ret);
            tmp--;
        }
        return ret;
    }

    //recursiva
    static Lista<Integer> rangeRec(Integer start, Integer end){
       if(start<end){
           return Lista.of(start, range(start+1, end));
       }
       return Lista.Empty;
    }

    //-------------------
    static Lista<Integer> rangeAux(Lista<Integer> tmp, Integer start, Integer end) {
        if(start<end) {
            return rangeAux(tmp.prepend(start), start+1, end);
        }
        else {
            return tmp;
        }

    }

    static Lista<Integer> rangeP(Integer start, Integer end) {
        return rangeAux(Lista.Empty, start, end).invertFolding();
    }

    static <T> Lista<T> unfold(T start, Function<T,T> f, Predicate<T> p) {
        Lista<T> ret = Lista.Empty;
        var tmp = start;

        while(p.test(tmp)) {
            ret = ret.prepend(tmp);
            tmp = f.apply(tmp);
        }

        return ret.invertFolding();
    }

    static <T> Lista<T> unfoldRec(T start, Function<T,T> f, Predicate<T> p) {
        if(p.test(start)==false) {
            return Lista.Empty;
        }
        else {
            return Lista.of( start, unfoldRec(f.apply(start), f, p) );
        }
    }

    static <T> Lista<T> unfoldTailRecAux(Lista<T> tmp, T start, Function<T,T> f, Predicate<T> p) {
        if(p.test(start)==false) {
            return tmp;
        }
        else {
            return unfoldTailRecAux(tmp.prepend(start), f.apply(start), f, p);
        }
    }

    static <T> Lista<T> unfoldTailRec(T start, Function<T,T> f, Predicate<T> p) {
        return unfoldTailRecAux(Lista.Empty, start, f, p)
                .invertFolding();
    }


    /*
    *Instrucciones
    En base a lo revisado en la clase del día de hoy, implementar los algoritmos:

    1 range(start, end): versión tail recursiva, versión utilizando unfold
    2 unfold(start, function, predicate), versiones: imperativa, recursiva, tail recursiva
    */

    //Range tail recursivo ********************************************************************
    default Lista<Integer> rangeTail(Integer inicio, Integer fin, Lista<Integer> ls){
        return inicio<fin
                ? rangeTail(inicio+1, fin, Lista.of(fin-(inicio+1),ls))
                : ls;

        /*if (inicio<fin){
            return rangeTail(inicio+1, fin, Lista.of(fin-(inicio+1),ls),ls));
        }else {
            return ls;
        }*/
    }

    //Range unfold ********************************************************************
    default <T> Lista<T> rangeUnfold(T inicio, Function<T,T>fn, Predicate<T> valorV, Lista<T> ls){

        return valorV.test(inicio)
                ? rangeUnfold(fn.apply(inicio), fn, valorV, Lista.of(inicio,ls))
                : ls;

        /*if(valorV.test(inicio)){
            return rangeUnfold(fn.apply(inicio), fn, valorV, Lista.of(inicio,ls));
        }else{
            return ls;
        }*/
    }

    //Unfold imperativo ********************************************************************
    default <T> Lista<T> unfoldImperativo(T inicio, Function<T, T> fn, Predicate<T> valorV) {
        Lista<T> ls = Lista.Empty;
        while (valorV.test(inicio)) {
            ls = Lista.of(inicio, ls);
            inicio = fn.apply(inicio);
        }
        return ls;
    }

    //Unfold Recursivo ********************************************************************
    default <T> Lista<T> unfoldRecursivo(T inicio, Function<T, T> fn, Predicate<T> valorV, Lista<T> ls) {
        return valorV.test(inicio)
                ? Lista.of(inicio, unfoldImperativo(inicio, fn, valorV))
                : ls;

        /*if (valorV.test(inicio)) {
            return Lista.of(inicio, unfoldImperativo(inicio, fn, valorV))
        } else {
            return ls;
        }*/
    }

    //Unfold Tail recursivo ********************************************************************
    default  <T> Lista<T> unfoldTail(T inicio, Function<T, T> fn, Predicate<T> valorV, Lista<T> ls) {
        return valorV.test(inicio)
                ? unfoldTail(fn.apply(inicio), fn, valorV, Lista.of(inicio,ls))
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
//    default TailCall<Integer> countR() {
//        return this.isEmpty()
//                ? tailReturn(0)
//                : tailSuspend(() -> this.countR(x -> x++));
//    }
//
//    //Append
//    default <T> TailCall<Lista<T>> appendR (T elem){
//        return ls.isEmpty()
//                ? tailReturn(0)
//                : tailSuspend(() -> ls.count(x -> x + 1));
//    }
//
//    default Lista<T> appendR(T elem) {
//        return appendTailRec(elem, Lista.Empty);
//    }
//
//    private Lista<T> appendTailRec(T elem, Lista<T> accumulator) {
//        if (this.isEmpty()) {
//            return accumulator.invertFolding().prepend(elem);
//        } else {
//            return this.tail().appendTailRec(elem, accumulator.prepend(this.head()));
//        }
//    }
//
//
//    //Prepend
//    default <T> TailCall<Lista<T>> prependR (T elem){
//        return ls.isEmpty()
//                ? tailReturn(0)
//                : tailSuspend(() -> ls.count(x -> x + 1));
//    }
//
//    //Insert
//    default <T> TailCall<Lista<T>> insertR (int index, T elem){
//        return index == 0
//                ? tailReturn(Lista.of(elem))
//                : tailSuspend(() -> Lista.insertR(index, elem));
//    }


    //get
//    default TailCall<Integer> getR(int index){
//        return index == 0
//                ? tailReturn((Integer) head())
//                : tailSuspend(() -> this.getR(index-1));
//    }
//


    //take
//    default <T> TailCall<Lista<T>> takeR (int n){
//        return ls.isEmpty()
//                ? tailReturn(0)
//                : tailSuspend(() -> ls.count(x -> x + 1));
//    }
//
//    //drop
//    default <T> TailCall<Lista<T>> dropR (int n){
//        return ls.isEmpty()
//                ? tailReturn(0)
//                : tailSuspend(() -> ls.count(x -> x + 1));
//    }
//
//
//    //concat
//    default <T> TailCall<Lista<T>> concatR (Lista<T> ls){
//        return ls.isEmpty()
//                ? tailReturn(0)
//                : tailSuspend(() -> ls.count(x -> x + 1));
//    }
//
//    //map
//    default  <T,U> TailCall<Lista<U>> mapR(Function<T,U> fn){
//        return ls.isEmpty()
//                ? tailReturn(0)
//                : tailSuspend(() -> ls.count(x -> x + 1));
//    }
//
//    //invertir
//    default <T> TailCall<Lista<T>> invertFoldingR(){
//        return ls.isEmpty()
//                ? tailReturn(0)
//                : tailSuspend(() -> ls.count(x -> x + 1));
//    }

    //mapping

    //reduce

    //

}
