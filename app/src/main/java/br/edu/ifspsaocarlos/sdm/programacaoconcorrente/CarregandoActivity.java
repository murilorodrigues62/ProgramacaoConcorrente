package br.edu.ifspsaocarlos.sdm.programacaoconcorrente;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
public class CarregandoActivity extends Activity{
    private final int ABRIR_ACTIVITY_PRINCIPAL = 0; // indica a ação a ser executada
    private final int TEMPO_CARREGAMENTO = 3000; // tempo de atraso na passagem da mensagem ao handler

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carregando);

        Toast.makeText(this, R.string.mensagem_carregando, Toast.LENGTH_SHORT).show();


        /* A classe Message permite o envio de ações (na forma de mensagens) para o
            handler de uma Activity. Essas mensagens são enfileiradas no objeto handler e
            podem ser processadas imediatamente ou com um atraso programado.
            IMPORTANTE: A classe Message não tem nenhuma relação com a mensagem de
            texto mostrada ao usuário por meio da classe Toast.*/
        Message mensagem = new Message();
        mensagem.what = ABRIR_ACTIVITY_PRINCIPAL;
        handler.sendMessageDelayed(mensagem, TEMPO_CARREGAMENTO); // adiciona a mensagem na fila do handler, que executa posteriormente o handleMessage
    }

    private Handler handler = new Handler(){
        // cria classe anônima
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ABRIR_ACTIVITY_PRINCIPAL:
                    Intent intent = new Intent(CarregandoActivity.this, PrincipalActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    break;
            }
        }
    };
}