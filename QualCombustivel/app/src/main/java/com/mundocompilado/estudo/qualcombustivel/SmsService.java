package com.mundocompilado.estudo.qualcombustivel;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Map;

public class SmsService extends Service {

    public SmsService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        executar();
        return START_STICKY;
    }

    private void executar(){
        if(!Apoio.isOnline(this)) return;

        Map<String, ?> smss = Apoio.consultar(this);

        for (Map.Entry<String, ?> entry : smss.entrySet()){
            if(!Apoio.isOnline(this)) return;

            SmsAsyncTask smsAsyncTask = new SmsAsyncTask(
                    entry.getKey().replace(Apoio.CHAVE, ""),
                    entry.getValue().toString(),
                    new IEvento() {
                        @Override
                        public void executar(String retorno) {
                            if(retorno != null)
                                Apoio.remover(retorno.toString(), getBaseContext());
                        }
                    });

            smsAsyncTask.execute();

        }
    }
}
