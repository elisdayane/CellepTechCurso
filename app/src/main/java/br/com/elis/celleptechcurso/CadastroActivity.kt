package br.com.elis.celleptechcurso

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        //criar uma lista de opcoes para o spinner
        val listaGenero = arrayListOf("Selecione o genero", "Feminino", "Masculino", "Não-binário")

        //criar um adaptador para o spinner: layout
        //contexto, layout, dados
        val generoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listaGenero)

        //plugar o adaptador ao spinner
        spnCadastroGenero.adapter = generoAdapter

        //executando o botao cadastrar
        btnCadastroCadastrar.setOnClickListener {
            val nome = edtCadastroNome.text.toString().trim()
            val sobrenome = edtCadastroNome.text.toString().trim()
            val email = edtCadastroEmail.text.toString().trim().toLowerCase()
            val senha = edtCadastroSenha.text.toString().trim()
            val genero = spnCadastroGenero.selectedItem.toString()

            //validacao de campos
            if(nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty() || genero == listaGenero[0])
            {
                //mensagem de erro
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
            else{

                //criando ou acessando o arquivo de shared preferences
                val sharedPrefs = getSharedPreferences("cadastro_$email",Context.MODE_PRIVATE)

                //editar o arquivo criado acima
                val editPrefs = sharedPrefs.edit()

                //preparando os dados a serem salvos
                editPrefs.putString("NOME", nome)
                editPrefs.putString("SOBRENOME", sobrenome)
                editPrefs.putString("EMAIL", email)
                editPrefs.putString("SENHA", senha)
                editPrefs.putString("GENERO", genero)

                //salvar edicoes
                editPrefs.apply()

                //abrir o main activity
                val mIntent = Intent(this, MainActivity::class.java)
                //passando valores para tela de login - put
                mIntent.putExtra("INTENT_EMAIL",email)
                startActivity(mIntent)

                //tira as telas do empilhamento
                finishAffinity()

            }
        }
    }
}