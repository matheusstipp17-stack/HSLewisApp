package com.lewisfog.hslewis

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import com.google.gson.JsonObject

class MainActivity : AppCompatActivity() {
    private lateinit var okHttpClient: OkHttpClient
    private var isAuthenticated = false
    private var precisionActive = false
    private var pescocoActive = false
    
    private lateinit var authLayout: LinearLayout
    private lateinit var panelLayout: ScrollView
    private lateinit var passwordInput: EditText
    private lateinit var loginBtn: Button
    private lateinit var logoutBtn: Button
    
    private val apiUrl = "https://3000-ioxxvfet9guy9xbkpko1m-e6bcf4a6.us2.manus.computer/api/trpc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        okHttpClient = OkHttpClient()
        
        initializeViews()
        setupListeners()
    }

    private fun initializeViews() {
        authLayout = findViewById(R.id.authLayout)
        panelLayout = findViewById(R.id.panelLayout)
        passwordInput = findViewById(R.id.passwordInput)
        loginBtn = findViewById(R.id.loginBtn)
        logoutBtn = findViewById(R.id.logoutBtn)
    }

    private fun setupListeners() {
        loginBtn.setOnClickListener { handleLogin() }
        logoutBtn.setOnClickListener { handleLogout() }
        
        findViewById<Button>(R.id.injectPrecisionBtn).setOnClickListener { handleInjectPrecision() }
        findViewById<Button>(R.id.deactivatePrecisionBtn).setOnClickListener { handleDeactivatePrecision() }
        findViewById<Button>(R.id.injectPescocoBtn).setOnClickListener { handleInjectPescoco() }
        findViewById<Button>(R.id.deactivatePescocoBtn).setOnClickListener { handleDeactivatePescoco() }
    }

    private fun handleLogin() {
        val password = passwordInput.text.toString()
        if (password == "LEWISFOV") {
            isAuthenticated = true
            passwordInput.text.clear()
            authLayout.visibility = android.view.View.GONE
            panelLayout.visibility = android.view.View.VISIBLE
            Toast.makeText(this, "✅ Autenticação bem-sucedida!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "❌ Senha incorreta!", Toast.LENGTH_SHORT).show()
            passwordInput.text.clear()
        }
    }

    private fun handleLogout() {
        isAuthenticated = false
        precisionActive = false
        pescocoActive = false
        authLayout.visibility = android.view.View.VISIBLE
        panelLayout.visibility = android.view.View.GONE
        Toast.makeText(this, "Você foi desconectado!", Toast.LENGTH_SHORT).show()
    }

    private fun handleInjectPrecision() {
        if (precisionActive) return
        injectCode("precision")
    }

    private fun handleInjectPescoco() {
        if (pescocoActive) return
        injectCode("pescoço")
    }

    private fun handleDeactivatePrecision() {
        if (!precisionActive) return
        deactivateCode("precision")
    }

    private fun handleDeactivatePescoco() {
        if (!pescocoActive) return
        deactivateCode("pescoço")
    }

    private fun injectCode(type: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val json = JsonObject().apply {
                    addProperty("json", """{"type":"$type"}""")
                }
                
                val requestBody = json.toString().toRequestBody("application/json".toMediaType())
                val request = Request.Builder()
                    .url("$apiUrl/risk.injectPrecision")
                    .post(requestBody)
                    .build()

                val response = okHttpClient.newCall(request).execute()
                if (response.isSuccessful) {
                    when (type) {
                        "precision" -> precisionActive = true
                        "pescoço" -> pescocoActive = true
                    }
                    Toast.makeText(this@MainActivity, "✅ $type injetado com sucesso!", Toast.LENGTH_SHORT).show()
                    updateUI()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "❌ Erro: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deactivateCode(type: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val json = JsonObject().apply {
                    addProperty("json", """{"type":"$type"}""")
                }
                
                val requestBody = json.toString().toRequestBody("application/json".toMediaType())
                val request = Request.Builder()
                    .url("$apiUrl/risk.deactivate")
                    .post(requestBody)
                    .build()

                val response = okHttpClient.newCall(request).execute()
                if (response.isSuccessful) {
                    when (type) {
                        "precision" -> precisionActive = false
                        "pescoço" -> pescocoActive = false
                    }
                    Toast.makeText(this@MainActivity, "✅ $type desativado com sucesso!", Toast.LENGTH_SHORT).show()
                    updateUI()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "❌ Erro: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUI() {
        val precisionStatus = findViewById<TextView>(R.id.precisionStatus)
        val pescocoStatus = findViewById<TextView>(R.id.pescocoStatus)
        
        precisionStatus.text = if (precisionActive) "✅ ATIVO" else "❌ INATIVO"
        precisionStatus.setTextColor(if (precisionActive) android.graphics.Color.GREEN else android.graphics.Color.RED)
        
        pescocoStatus.text = if (pescocoActive) "✅ ATIVO" else "❌ INATIVO"
        pescocoStatus.setTextColor(if (pescocoActive) android.graphics.Color.GREEN else android.graphics.Color.RED)
    }
}
