package treinamento.com.mutbot

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import treinamento.com.mutbot.databinding.ActivityPrincipalBinding

class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalBinding;
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        //hiding the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide();

        super.onCreate(savedInstanceState)

        binding = ActivityPrincipalBinding.inflate(layoutInflater);
        val view = binding.root;
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();
        val user : String? = Firebase.auth.currentUser?.email
        val email = binding.usuario;
        email.setText(user)

        binding.sair.setOnClickListener() {
            mAuth.signOut();
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}

