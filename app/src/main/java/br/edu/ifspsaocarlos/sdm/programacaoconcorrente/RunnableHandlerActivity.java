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
Após clicar no botão, dispara uma thread para alterar o último texto da tela, passando um runnable para o handler.
Usar Runnable é mais prático que Message, porém pode complicar se tiver varias ações.
 */
public class RunnableHandlerActivity extends Activity implements View.OnClickListener {
    private Button btCliqueAqui;
    private Handler handler;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geral);
        TextView tvNomeActivity = (TextView) findViewById(R.id.tv_nome_activity);
        tvNomeActivity.setText("Runnable -> Handler");
        btCliqueAqui = (Button) findViewById(R.id.bt_clique_aqui);
        btCliqueAqui.setOnClickListener(this);
        handler = new Handler();
    }
    public void onClick(View v) {
        if (v == btCliqueAqui){
            new Thread(){
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            TextView tvAlvo = (TextView) findViewById(R.id.tv_alvo);
                            tvAlvo.setText(getString(R.string.texto_alterado));
                        }
                        }
                    );
                }
            }.start();
        }
    }
}