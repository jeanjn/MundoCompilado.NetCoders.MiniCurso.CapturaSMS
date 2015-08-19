package com.mundocompilado.estudo.qualcombustivel;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import java.util.Objects;

//Classe responsavel por capturar os novos smss e salva-los
//Para que ela seja chamada e necessario que seja criado uma action no AndroidManifest.xml

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //PDU = Protocol data unit
        //PDU e o formato comum dos smss enviados e recebidos nos celulares

        //capturamos todos os pdus enviados dentro do intent
        final Object[] pdusObj = (Object[]) intent.getExtras().get("pdus");

        //percorremos cada pdu/mensagem
        for (int i = 0; i < pdusObj.length; i++){

            //convertemos o pdu para sms
            SmsMessage currentMenssage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);

            //capturamos o numero ou nome de quem enviou e a mensagem
            String numero = currentMenssage.getDisplayOriginatingAddress();
            String mensagem = currentMenssage.getDisplayMessageBody();

            //Salvamos para posteriormente usa-los em um servico
            Apoio.salvar(numero, mensagem, context);

            //iniciamos o servico para enviar os smss
            context.startService(new Intent(context, SmsService.class));
        }
    }
}
