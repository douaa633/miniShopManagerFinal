package com.example.minishopmanager

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PaiementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paiement)

        val tvRecap = findViewById<TextView>(R.id.tvRecap)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val etNomCarte = findViewById<EditText>(R.id.etNomCarte)
        val etNumero = findViewById<EditText>(R.id.etNumero)
        val etCvv = findViewById<EditText>(R.id.etCvv)
        val btnPayer = findViewById<Button>(R.id.btnPayer)

        val db = DBHelper(this).writableDatabase

        // 1. Lire le panier depuis SQLite
        var total = 0
        val recap = StringBuilder()
        val cursor = db.rawQuery("SELECT nom, prix FROM panier", null)
        while (cursor.moveToNext()) {
            val nom = cursor.getString(0)
            val prix = cursor.getInt(1)
            recap.append("• $nom : $prix DT\n")
            total += prix
        }
        cursor.close()

        if (total == 0) {
            recap.append("Votre panier est vide")
        }
        tvRecap.text = recap.toString()
        tvTotal.text = "Total : $total DT"

        // 2. Clic sur Payer
        btnPayer.setOnClickListener {
            if (total == 0) {
                Toast.makeText(this, "Le panier est vide", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (etNomCarte.text.toString().isEmpty() ||
                etNumero.text.toString().length != 16 ||
                etCvv.text.toString().length != 3
            ) {
                Toast.makeText(this, "Vérifiez les informations de la carte", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Paiement simulé : on vide le panier
            db.execSQL("DELETE FROM panier")
            Toast.makeText(this, "Paiement effectué ✅ Merci !", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}