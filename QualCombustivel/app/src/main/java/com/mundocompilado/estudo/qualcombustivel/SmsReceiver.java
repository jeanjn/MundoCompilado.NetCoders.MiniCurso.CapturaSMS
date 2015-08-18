package com.mundocompilado.estudo.qualcombustivel;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import java.util.Objects;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final Object[] pdusObj = (Object[]) intent.getExtras().get("pdus");

        for (int i = 0; i < pdusObj.length; i++){
            SmsMessage currentMenssage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);

            String numero = currentMenssage.getDisplayOriginatingAddress();
            String mensagem = currentMenssage.getDisplayMessageBody();

            Apoio.salvar(numero, mensagem, context);

            context.startService(new Intent(context, SmsService.class));
        }
    }
}
