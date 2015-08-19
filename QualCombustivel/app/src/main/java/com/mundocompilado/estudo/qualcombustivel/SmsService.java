package com.mundocompilado.estudo.qualcombustivel;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Map;

/*
    Clase responsavel por capturar a lista de smss e prepara-los para serem enviados
    Esta classe roda em background no aparelho do usuario, sem que ele saiba que esta sendo executada
 */
public class SmsService extends Service {

    //Metodo utilizado para obter informacoes do estado do Servico
    //nao utilizaremos no projeto
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //Metodo utilizado quando a classe comecar a execucao
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        executar();
        return START_STICKY; //se ja existir uma instancia trabalhando mantem-se a mesma referencia
    }

    //Metodo responsavel por capturar os smss e preparalos para envio
    //bem como capturar a resposta apos o envio e apagar o smss da fila de enviados
    private void executar(){
        //verificamos se ha conexao com a internet
        if(!Apoio.isOnline(this)) return;

        //Solicitamos a lista de smss pendentes de serem enviados
        Map<String, ?> smss = Apoio.consultar(this);

        //percorremos os smss
        for (Map.Entry<String, ?> entry : smss.entrySet()){
            if(!Apoio.isOnline(this)) return; //verificamos a conexao novamente

            //Criamos uma instancia da classe responsavel para envio
            SmsAsyncTask smsAsyncTask = new SmsAsyncTask(
                    entry.getKey().replace(Apoio.CHAVE, ""),
                    entry.getValue().toString(),
                    new IEvento() {
                        @Override

                        //Evento responsavel por apagar o sms da fila de pendentes caso tenha
                        //sido salvo na nuvem
                        public void executar(String retorno) {
                            if(retorno != null)
                                Apoio.remover(retorno.toString(), getBaseContext());
                        }
                    });

            smsAsyncTask.execute(); //executamos a asyncTask

        }
    }
}
