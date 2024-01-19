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

}
