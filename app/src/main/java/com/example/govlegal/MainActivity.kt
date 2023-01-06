package com.example.govlegal

import android.annotation.SuppressLint
import android.app.AlertDialog.Builder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.govlegal.databinding.ActivityMainBinding
import com.onesignal.OneSignal;


//5dbbecdd-3968-4918-91e4-a93297afa996  // esse é meu para teste de push notification
//2d365516-416c-4669-861f-7bb048934409  // esse é govlegal produçao


class MainActivity : AppCompatActivity() {

    private final val ONESIGNAL_APP_ID: String = "2d365516-416c-4669-861f-7bb048934409"
    private lateinit var  binding: ActivityMainBinding



    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Habilite o log detalhado do OneSignal para depurar problemas, se necessário.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
        // promptForPushNotifications mostrará o prompt de permissão de notificação nativa do Android.
        OneSignal.promptForPushNotifications();




        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val webWiew = binding.webView


        webWiew.loadUrl("https://app.govlegal.com.br/#/")
        webWiew.settings.javaScriptEnabled = true
        webWiew.settings.domStorageEnabled = true



    }


    override fun onBackPressed() {
        //super.onBackPressed()
        autorisandoSaida();
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