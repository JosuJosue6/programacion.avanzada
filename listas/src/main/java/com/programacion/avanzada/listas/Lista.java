package com.programacion.avanzada.listas;

import java.util.List;
import java.util.function.Function;

public interface Lista<T> {

    Lista Empty = new Empty();

    T head();
    Lista<T> tail();

    boolean isEmpty();

    static <T> Lista<T> of(T ... elems){
        var tmp = Lista.Empty;
        for (int i=elems.length-1;i>=0;i--){
            tmp = new Cons(elems[i],tmp);
        }
        return tmp;
    }

    static <T> Lista<T> of(T head, Lista<T> tail){
        return new Cons<>(head,tail);
    }

    default int count(){
        return 1 + tail().count();
    }

    default Lista<T> prepend(T elem){
        return new Cons<>(elem, this);
    }

    default Lista<T> append(T elem){
        return  this.isEmpty()
                ? new Cons<>(elem,Lista.Empty)
                : new Cons<>(this.head(), this.tail().append(elem))
                ;
    }

    default Lista<T> insert(int index, T elem){
        return index == 0
                ? new Cons<>(elem, this)
                : new Cons<>(this.head(), this.tail().insert(index-1,elem))
                ;
    }

    default T get(int index){
        return index == 0
                ? this.head()
                : this.tail().get(index-1);
    }

    default Lista<T> take(int n){
        return n<=0  || Lista.Empty.isEmpty()
                ? Lista.Empty
                : Lista.of(this.head(), this.tail().take(n-1))
                ;
    }

    default Lista<T> drop(int n){
        return n<=0 || this.isEmpty()
                ? this
                : this.tail().drop(n-1)
                ;
    }

    default Lista<T> concat (Lista<T> ls){
        return this.isEmpty()
        ? ls
        : Lista.of(this.head(),this.tail().concat(ls));
    }

    default Lista<T> invertir(){
        return foldLeft(Lista.Empty, ls->t->ls.prepend(t));
    }

    Lista<T> foldLeft(Lista empty, Object o);

    default <U> Lista<U> mapFold(Function<T,U> fn){
        return foldLeft(Lista.Empty, ls ->t-> ls.append(fn.apply(t)));
    }

    default <U> Lista<U> mapFold2(Function<T,U> fn){
        return foldRight(Lista.Empty, t ->ls-> ls.prepend(fn.apply(t)));
    }

    default Integer countFold(){
        return foldLeft(0, n-> t-> n+1);
    }

    default Lista<T> appendFold(T elem){
        return foldRight(Lista.of(elem), t->ls -> ls.prepend(t) );
    }

    default T reduce(T identity, Function<T, Function<T,T>>fn){
        return this.tail().foldLeft(identity, u -> t->fn.apply(u).apply(t));
    }

    default T reduce(Function<T, Function<T,T>>fn){
        return this.tail().foldLeft(this.head(), u -> t->fn.apply(u).apply(t));
    }

    default Lista<T> takeFold(int index){
        return  foldLeft(Lista.Empty,ls -> t-> ls.count()<n?ls.append(t):ls);

    }

    default Lista<T> dropFold(int n){
        int tot = this.count() -n;
        return foldRigth(
                Lista.Empty, t -> ls-> ls.count()<tot? ls.prepend(t):ls
        );
    }
}
