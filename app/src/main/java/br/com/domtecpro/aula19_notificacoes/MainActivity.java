package br.com.domtecpro.aula19_notificacoes;

import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String canal = "1";
    private int id = 1;
    private CharSequence nomeCanal;
    private String descricaoCanal;
    private int importancia = NotificationManager.IMPORTANCE_DEFAULT;
    private String tituloNotificacao = "Notificação Teste";
    private String textoNotificacao = "Teste de Notificação";
    private Bundle pacoteDeDados = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView text = new TextView(this);
        text.setText("Notificação disparada.");
        setContentView(text);

        // Apenas para API 26+
        nomeCanal = getString(R.string.channel_name);
        descricaoCanal = getString(R.string.channel_description);
        Notificacao.criarCanalDeNotificacao(canal, this.getApplicationContext(),
                nomeCanal, descricaoCanal, importancia);

        // Prepara dados/informações que serão exibidas na janela de notificação
        pacoteDeDados = new Bundle();
        pacoteDeDados.putString("dados1", "Informação vinda do banco de dados");

        // Parâmetros da notificação que será disparada
        Notificacao.criaNotificacao(this.getApplicationContext(),
                canal, tituloNotificacao,
                textoNotificacao, importancia, id, pacoteDeDados);
    }


}