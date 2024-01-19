package progra.avanzada;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Principal {

    public static void main(String[] args) {
        //Lista
        List<String> ls = new ArrayList<>();
        ls.add("test");
        ls.add("Test");
        ls.add("prueba");
        ls.add("Prueba");

        System.out.println(ls);

        //METODOS --------------------------------------------------------
        //Insertar
        var ret = OperacionesListas.prepend(ls,"aaa");
        System.out.println(ret);

        //insertar final
        var ret2 = OperacionesListas.append(ls,"AAA");
        System.out.println(ret2);

        //insertar posicion fija
        var ret3 = OperacionesListas.insert(ls,"ABC",2);
        System.out.println(ret3);

        //eliminar posicion fija
        var ret4 = OperacionesListas.remove(ls,2);
        System.out.println(ret4);

        //obtener valor
        var ret5 = OperacionesListas.get(ls,1);
        System.out.println(ret5);

        System.out.println(ls);


        //FUNCIONES -----------------------------------------

        List<String> ls2 = new ArrayList<>();
        ls2.add("Test");
        ls2.add("1");
        ls2.add("prueba");
        String t = "a";

        /*Function<List<String>,String> fn = x-> y -> x.add(0,);

        var res = fn.apply(ls2);

        System.out.println(res);*/

    }
}
