package treinamento.com.mutbot

import android.R.attr.password
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import treinamento.com.mutbot.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleSignInCliente: GoogleSignInClient
    private var mAuth: FirebaseAuth? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        //hiding the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide();

        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(layoutInflater);
        val view = binding.root;
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        binding.botaoEntrar.setOnClickListener {
            try {
                loginUsuario();
            } catch (e: Exception) {
                infoUser("Insira usuário e senha", 0)
            }

        }
    }

    private fun loginUsuario() {
        val email = binding.editTextTextUsuario.getText().toString().trim();
        val senha = binding.editTextTextSenha.getText().toString().trim();
        mAuth!!.signInWithEmailAndPassword(
            email,
            senha
        )
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    //val user = mAuth!!.currentUser
                    infoUser("Login efetuado", 1)
                    limpaCampos();
                    abreForum();
                } else {
                    infoUser("Email ou senha incorreto", 0)
                }

            }
    }

    private fun abreForum() {
        startActivity(Intent(this, PrincipalActivity::class.java))
        finish();
    }

    private fun limpaCampos() {
        binding.editTextTextUsuario.setText("");
        binding.editTextTextSenha.setText("");
    }

    private fun infoUser(text: String, type: Int) {
        val info = binding.info;
        info.visibility = View.VISIBLE;
        if (type == 0) {
            info.setText(text);
            info.setTextColor(Color.parseColor("#FF0000"));
        } else {
            info.setText(text);
            info.setTextColor(Color.parseColor("#00FF00"));
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = mAuth?.currentUser
        try {
            if (currentUser != null) {
                abreForum();
            }
        } catch (e: Exception) {
        }
    }
}

