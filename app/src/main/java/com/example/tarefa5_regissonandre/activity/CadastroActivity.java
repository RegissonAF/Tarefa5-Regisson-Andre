package com.example.tarefa5_regissonandre.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tarefa5_regissonandre.R;

public class CadastroActivity extends AppCompatActivity {

    Button cadastrar_Button;
    EditText editNameText, editRAText, editCEPText, editBairroText, editLogradouroText,
            editComplementoText, editCidadeText, editUFText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);

        editNameText = findViewById(R.id.editNameText);
        editRAText = findViewById(R.id.editRAText);
        editCEPText = findViewById(R.id.editCEPText);
        editBairroText = findViewById(R.id.editBairroText);
        editLogradouroText = findViewById(R.id.editLogradouroText);
        editComplementoText = findViewById(R.id.editComplementoText);
        editCidadeText = findViewById(R.id.editCidadeText);
        editUFText = findViewById(R.id.editUFText);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}