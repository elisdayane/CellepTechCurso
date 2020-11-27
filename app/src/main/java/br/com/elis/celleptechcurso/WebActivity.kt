package br.com.elis.celleptechcurso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        //habilitando a execucao de javascriptno webview
        wbvWeb.settings.javaScriptEnabled = true

        //Carregar uma pagina web (URL) no webview
        wbvWeb.loadUrl(("http://br.cellep.com/estacaohack"))

        //definindo o  webview como cliente web padroa
        wbvWeb.webViewClient = WebViewClient()
    }
}