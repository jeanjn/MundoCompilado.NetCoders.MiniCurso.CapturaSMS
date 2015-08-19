package com.mundocompilado.estudo.qualcombustivel;

/**
 * Created by jean.almeida on 14/08/2015.
 * Classe responsavel por enviar os smss recebidos para a nuvem
 * de forma assincrona
 */
public class SmsAsyncTask extends android.os.AsyncTask<Void, Void, String> {
    private String _numero; //quem enviou
    private String _mensagem; //mensagem
    private IEvento _iEvento; //evento apos o envio para a nuvem

    public SmsAsyncTask(String numero, String mensagem, IEvento iEvento){
        _numero = numero;
        _mensagem = mensagem;
        _iEvento = iEvento;
    }


    @Override
    protected String doInBackground(Void... params) {
        //Utilizamos a classe RestClient para envio dos dados
        RestClient restClient = new RestClient("http://estudo.mundocompilado.com/api/Sms/Post");
        restClient.AddParam("numero", _numero);
        restClient.AddParam("mensagem", _mensagem);

        try {
            restClient.Execute(RestClient.RequestMethod.GET);

            //se a resposta for OK/200 significa que tudo ocorreu como esperado
            //entao retornamos o numero para o metodo onPostExecute
            if(restClient.getResponseCode() == 200){
                return _numero;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //caso de erro retornamos null
        return null;
    }

    //Metodo chamado apos a execucao do doInBackground, recebendo o retorno do mesmo
    @Override
    protected void onPostExecute(String s) {
        //chamamos o metodo executar do evento com o numero caso tudo tenha corrido bem
        _iEvento.executar(s);
    }
}
