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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view){
        EditText editTextEtanol = (EditText) findViewById(R.id.editTextEtanol);
        EditText editTextGasolina = (EditText) findViewById(R.id.editTextGasolina);
        TextView textViewResultado = (TextView) findViewById(R.id.textViewResultado);

        if(editTextEtanol.length() == 0){
            editTextEtanol.setError("Campo obrigatório");
        }

        if(editTextGasolina.length() == 0){
            editTextGasolina.setError("Campo obrigatório");
        }

        double valorEtanol = toDouble(editTextEtanol);
        double valorGasolina = toDouble(editTextGasolina);

        if(valorEtanol < 0 || valorGasolina < 0){
            textViewResultado.setText("...");
            return;
        }

        double resultado = valorEtanol / valorGasolina;

        if(resultado > 0.7){
            textViewResultado.setText("Gasolina");
        }
        else{
            textViewResultado.setText("Etanol");
        }
    }

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
