package com.mundocompilado.estudo.qualcombustivel;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

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

    }
}
