package treinamento.com.mutbot

import android.R.attr.password
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import treinamento.com.mutbot.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleSignInCliente: GoogleSignInClient
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(layoutInflater);
        val view = binding.root;

        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        binding.botaoEntrar.setOnClickListener {

            val email =  binding.editTextTextUsuario.getText().toString().trim();
            val senha = binding.editTextTextSenha.getText().toString().trim();
            mAuth!!.signInWithEmailAndPassword(
                email,
                senha
            )
                .addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCustomToken:success")
                        val user = mAuth!!.currentUser

                        Toast.makeText(
                            baseContext, "Autenticação efetuada",
                            Toast.LENGTH_SHORT
                        ).show()
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCustomToken:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Erro de autenticação",
                            Toast.LENGTH_SHORT
                        ).show()
                        //updateUI(null)
                    }

                    // ...
                }

        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        // val currentUser = mAuth!!.currentUser
        //updateUI(currentUser)
    }
}