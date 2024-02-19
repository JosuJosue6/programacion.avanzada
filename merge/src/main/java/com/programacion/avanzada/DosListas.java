package com.programacion.avanzada;

import com.programacion.avanzada.listas.Lista;

record DosListas <T>(Lista<T>izquierda, Lista<T>derecha) {

    @Override
    public String toString() {
        return ("izquierda: "+izquierda+ ", derecha: "+derecha);
    }
}
