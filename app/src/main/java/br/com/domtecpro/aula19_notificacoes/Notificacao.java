package br.com.domtecpro.aula19_notificacoes;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;

public class Notificacao {

    public static void criarCanalDeNotificacao(String canal, Context c, CharSequence nome,
                                               String descricao, int importancia) {
        // Criar Canal de Notificação, mas somente para API 26 em diante
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel canalDeNotificacao = new NotificationChannel(canal, nome, importancia);
            canalDeNotificacao.setDescription(descricao);
            // Registrar o canal com o sistema; você não pode trocar a importância
            // ou outros comportamentos de notificações depois desta linha
            NotificationManager notificationManager = c.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(canalDeNotificacao);
        }
    }

    public static void criaNotificacao(Context c, String canal, String titulo, String texto,
                                       int importancia, int id, Bundle pacoteDeDados) {
        // Cria uma intenção explícita para uma Atividade no seu app (troca de janela)
        Intent intent = new Intent(c.getApplicationContext(), ExemploExecutaNotificacao.class);
        intent.putExtra("dadosRecebidos", pacoteDeDados);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(c.getApplicationContext(), 0, intent, 0);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(c.getApplicationContext(), canal)
                        .setSmallIcon(R.drawable.ic_notify)
                        .setContentTitle(titulo)
                        .setContentText(texto)
                        .setPriority(importancia)
                        // Define a intenção que irá explodir quando o usuário clicar na notificação
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

        NotificationManagerCompat notificationManager2 =
                NotificationManagerCompat.from(c.getApplicationContext());

        // notificationId is a unique int for each notification that you must define
        notificationManager2.notify(id, builder.build());
    }
}
