package com.example.govlegal
import android.annotation.SuppressLint
import android.app.AlertDialog.Builder
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.govlegal.databinding.ActivityMainBinding
import com.onesignal.OneSignal;


//5dbbecdd-3968-4918-91e4-a93297afa996  // esse é meu para teste de push notification
//2d365516-416c-4669-861f-7bb048934409  // esse é govlegal produçao


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {


    private final val ONESIGNAL_APP_ID: String = "2d365516-416c-4669-861f-7bb048934409"
    private lateinit var binding: ActivityMainBinding


    @SuppressLint("SetJavaScriptEnabled", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val webView = binding.webView //findViewById<WebView>(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://app.govlegal.com.br/#/")
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true


        // Habilite o log detalhado do OneSignal para depurar problemas, se necessário.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
        // promptForPushNotifications mostrará o prompt de permissão de notificação nativa do Android.
        OneSignal.promptForPushNotifications();

        // Verifica conexao internet
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            // Estamos conectados à Internet
            Toast.makeText(this,"Conectado a Internet",Toast.LENGTH_SHORT).show()
        } else {
            // Não estamos conectados à Internet
            Toast.makeText(this,"Não conectado a Internet",Toast.LENGTH_SHORT).show()
            setContentView(R.layout.network_error_layout)
        }

    }

    override fun onBackPressed() {
        val webView = binding.webView//findViewById<WebView>(R.id.webView)
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            autorisandoSaida();
           // super.onBackPressed()
        }
    }

    private fun autorisandoSaida(){

        val builder = Builder(this)
        builder.setIcon(R.mipmap.ic_launcher)
        builder.setTitle(R.string.app_name)
        builder.setMessage("Voce Deseja sair do GOVLEGAL?")
        builder.setCancelable(false)
        builder.setPositiveButton("Sim") { dialog, x -> this.finish() }
        builder.setNegativeButton("Não") { dialog, x -> dialog.cancel() }
        val alert = builder.create()
        alert.show()


    }



}