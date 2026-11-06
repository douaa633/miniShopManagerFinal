package com.example.minishopmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvBienvenue = findViewById<TextView>(R.id.tvBienvenue)
        val btnCatalogue = findViewById<Button>(R.id.btnCatalogue)
        val btnPaiement = findViewById<Button>(R.id.btnPaiement)
        val btnDeconnexion = findViewById<Button>(R.id.btnDeconnexion)

        // Nom envoyé par l'écran de login
        val user = intent.getStringExtra("USER")
        if (user.isNullOrEmpty()) {
            tvBienvenue.text = "Bienvenue 👋"
        } else {
            tvBienvenue.text = "Bienvenue, $user 👋"
        }

        btnCatalogue.setOnClickListener {
            startActivity(Intent(this, CatalogueActivity::class.java))
        }

        btnPaiement.setOnClickListener {
            startActivity(Intent(this, PaiementActivity::class.java))
        }

        btnDeconnexion.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}