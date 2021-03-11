package br.com.domtecpro.aula19_notificacoes;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ExemploExecutaNotificacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NotificationManager nm =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancel(R.string.app_name);

        Intent it = getIntent();
        Bundle bundle = it.getBundleExtra("dadosRecebidos");
        String dados1 = bundle.getString("dados1");

        TextView text = new TextView(this);
        text.setText("Usuário selecionou a notificação \n\n" + dados1);
        setContentView(text);
    }
}
