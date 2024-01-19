package com.programacion.avanzada.listas;

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

    

}
