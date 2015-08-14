package com.mundocompilado.estudo.qualcombustivel;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jean.almeida on 14/08/2015.
 */
public class Apoio {
    public static String CHAVE = "SMS-";


    public static void salvar(String identificacao, String valor, Context context){
        SharedPreferences preferences = context.getSharedPreferences(CHAVE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CHAVE + identificacao, valor);
        editor.apply();
    }

    public static Map<String, ?> consultar(String chave, Context context){
        SharedPreferences preferences = context.getSharedPreferences(chave, Context.MODE_PRIVATE);

        ArrayList<String> lista = new ArrayList<>();

         return preferences.getAll();
    }

    public static  void remover(String chave, Context context){
        SharedPreferences preferences = context.getSharedPreferences(chave, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(CHAVE + chave);
        editor.apply();
    }
}
