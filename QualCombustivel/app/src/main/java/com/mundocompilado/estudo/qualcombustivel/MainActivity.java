package com.mundocompilado.estudo.qualcombustivel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //No layout activity_main foi programado para o botao chamar este metodo
    //no evento onClick
    //O view passado por parametro e o botao
    public void onClick(View view){

        //Capturamos os elementos da tela
        EditText editTextEtanol = (EditText) findViewById(R.id.editTextEtanol);
        EditText editTextGasolina = (EditText) findViewById(R.id.editTextGasolina);
        TextView textViewResultado = (TextView) findViewById(R.id.textViewResultado);

        //validamos os campos
        if(editTextEtanol.length() == 0){
            editTextEtanol.setError("Campo obrigatório");
        }

        if(editTextGasolina.length() == 0){
            editTextGasolina.setError("Campo obrigatório");
        }

        double valorEtanol = toDouble(editTextEtanol);
        double valorGasolina = toDouble(editTextGasolina);

        //restauramos o valor original do resultado caso nao possa ser feito o calculo
        if(valorEtanol < 0 || valorGasolina < 0){
            textViewResultado.setText("...");
            return;
        }

        double resultado = valorEtanol / valorGasolina;

        //exibimos o resultado na tela
        if(resultado > 0.7){
            textViewResultado.setText("Gasolina");
        }
        else{
            textViewResultado.setText("Etanol");
        }
    }

    //metodo de apoio para converter o valor digitado em um campo para double
    private double toDouble(EditText editText){
        try {
            return Double.parseDouble(editText.getText().toString());
        }
        catch (NumberFormatException e){
            editText.setError("Digite um valor válido!");
            return -1;
        }
    }
}
