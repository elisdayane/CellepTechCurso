package br.com.elis.celleptechcurso

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //executando (escutando) o clique do botao entrar
        btnLoginEntrar.setOnClickListener {

            //capturando os dados digitados
            val email = edtLoginEmail.text.toString().trim().toLowerCase()
            val senha = edtLoginSenha.text.toString().trim()

            //validação dos campos
            if(email.isEmpty()){
                edtLoginEmail.error = "Campo obrigatório!"
                edtLoginEmail.requestFocus()
            }else if(senha.isEmpty()){
                edtLoginSenha.error = "Campo Obrigatório!"
                edtLoginSenha.requestFocus()
            } else{
                //acessando a referencia para o SherdPreference
                val sharedPrefs = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)

                // Recuperando os dados do Shared Preference
                val emailPrefs = sharedPrefs.getString("EMAIL", "")
                val senhaPrefs = sharedPrefs.getString("SENHA", "")

                // Verificar o email e senha
                if (email == emailPrefs && senha == senhaPrefs) {

                    Toast.makeText(this, "Usuário Logado", Toast.LENGTH_LONG).show()

                    // Abrir a MainActivity
                    val mIntent = Intent(this, MainActivity::class.java)

                    mIntent.putExtra("INTENT_EMAIL", email)

                    startActivity(mIntent)
                    finish()

                    } else{
                    //mensagem de erro
                    Toast.makeText(this, "Usuário ou senha invalidos", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //executando o clique do botao cadastrar
        btnLoginCadastrar.setOnClickListener {
            val mIntent = Intent(this, CadastroActivity::class.java)
            startActivity(mIntent)
        }

    }
}