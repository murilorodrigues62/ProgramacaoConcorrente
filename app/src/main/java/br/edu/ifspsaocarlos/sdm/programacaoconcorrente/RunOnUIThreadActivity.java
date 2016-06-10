package br.edu.ifspsaocarlos.sdm.programacaoconcorrente;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
Mostra uma tela com 3 textos e um botão.
Após clicar no botão, dispara uma thread para alterar o último texto da tela.

Neste caso, não foi usado um handler explícito para processar uma mensagem ou
um Runnable. Foi usado um método da classe Activity que permite passar um
objeto Runnable para um handler implícito da Activity. Esse método facilita o
desenvolvimento pois não é necessário implementar um handler e todas as suas funcionalidades explicitamente.
 */
public class RunOnUIThreadActivity extends Activity implements View.OnClickListener {
    private Button btCliqueAqui;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geral);
        TextView tvNomeActivity = (TextView) findViewById(R.id.tv_nome_activity);
        tvNomeActivity.setText("RunOnUIThread");
        btCliqueAqui = (Button) findViewById(R.id.bt_clique_aqui);
        btCliqueAqui.setOnClickListener(this);
    }
    public void onClick(View v) {
        if (v == btCliqueAqui) {
            new Thread(){
                public void run() {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            TextView tvAlvo = (TextView) findViewById(R.id.tv_alvo);
                            tvAlvo.setText(getString(R.string.texto_alterado));
                        }
                    });
                }
            }.start();
        }
    }
}