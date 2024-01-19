package progra.avanzada;

import java.util.ArrayList;
import java.util.List;

public class OperacionesListas {

    public static List<String> prepend(List<String> ls, String str){

        List<String> nuevaL = new ArrayList<>();
        for(var t : ls){
            nuevaL.add(t);
        }
        nuevaL.add(0,str);
        return nuevaL ;
    }

    public static List<String> append (List<String> ls, String str){

        int poscionFin = ls.size();
        List<String> nuevaL = new ArrayList<>();
        for(var t : ls){
            nuevaL.add(t);
        }
        nuevaL.add(poscionFin,str);

        return nuevaL ;
    }

    public static List<String> insert (List<String> ls, String str, int posicion){

        List<String> nuevaL = new ArrayList<>();
        for(var t : ls){
            nuevaL.add(t);
        }
        nuevaL.add(posicion, str);

        return nuevaL ;
    }

    public static List<String> remove (List<String> ls, int posicion){

        List<String> nuevaL = new ArrayList<>();

        for(int i = 0; i<ls.size(); i++){
            if(i==posicion){
                continue;
            }
            else {
                nuevaL.add(ls.get(i));
            }
        }


        return nuevaL ;

    }

    public static List<String> get (List<String> ls, int indice){

        List<String> nuevaL = new ArrayList<>();

        for(int i = 0; i<ls.size(); i++){
            if(i==indice){
                nuevaL.add(ls.get(i));
            }else{
                continue;
            }
        }


        return nuevaL ;
    }

}
