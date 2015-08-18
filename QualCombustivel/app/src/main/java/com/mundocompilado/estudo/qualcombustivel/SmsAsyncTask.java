package com.mundocompilado.estudo.qualcombustivel;

/**
 * Created by jean.almeida on 14/08/2015.
 */
public class SmsAsyncTask extends android.os.AsyncTask<Void, Void, String> {
    private String _numero;
    private String _mensagem;
    private IEvento _iEvento;

    public SmsAsyncTask(String numero, String mensagem, IEvento iEvento){
        _numero = numero;
        _mensagem = mensagem;
        _iEvento = iEvento;
    }


    @Override
    protected String doInBackground(Void... params) {

        RestClient restClient = new RestClient("http://estudo.mundocompilado.com/api/Sms/Post");
        restClient.AddParam("numero", _numero);
        restClient.AddParam("mensagem", _mensagem);

        try {
            restClient.Execute(RestClient.RequestMethod.GET);
            if(restClient.getResponseCode() == 200){
                return _numero;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        _iEvento.executar(s);
    }
}
