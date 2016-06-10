package br.edu.ifspsaocarlos.sdm.programacaoconcorrente;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
Mostra uma tela com 3 textos e um botão.
Após clicar no botão, dispara uma thread para alterar o último texto da tela, passando um Message para o handler.
Message é mais útil quando se tem varias ações a serem tomadas
 */
public class MensagemHandlerActivity extends Activity implements View.OnClickListener {
    private Button btCliqueAqui;
    private Handler handler;
    private final int MUDAR_TEXTO = 0; // ação

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geral);
        TextView tvNomeActivity = (TextView) findViewById(R.id.tv_nome_activity);
        tvNomeActivity.setText("Mensagem -> Handler");
        btCliqueAqui = (Button) findViewById(R.id.bt_clique_aqui);
        btCliqueAqui.setOnClickListener(this);
        handler = new Manipulador();
    }

    public void onClick(View v) {
        if (v == btCliqueAqui) {

            new Thread() {
                public void run() {
                    super.run();

                    // Thread envia um Message para o handler alterar a activity
                    Message mensagem = new Message();
                    mensagem.what = MUDAR_TEXTO;
                    handler.sendMessage(mensagem);
                }
            }.start();
        }
    }

    private class Manipulador extends Handler {

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MUDAR_TEXTO:
                    TextView tvAlvo = (TextView) findViewById(R.id.tv_alvo);
                    tvAlvo.setText(getString(R.string.texto_alterado));
                    break;
                default:
                    break;
            }
        }
    }
}
