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


public class MainActivity extends AppCompatActivity {

    Button cadastrar_Button;
    EditText editNameText, editRAText, editCEPText, editBairroText, editLogradouroText,
            editComplementoText, editCidadeText, editUFText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editNameText = (EditText) findViewById(R.id.editNameText);
        editRAText = (EditText) findViewById(R.id.editRAText);
        editCEPText = (EditText) findViewById(R.id.editCEPText);
        editBairroText = (EditText) findViewById(R.id.editBairroText);
        editLogradouroText = (EditText) findViewById(R.id.editLogradouroText);
        editComplementoText = (EditText) findViewById(R.id.editComplementoText);
        editCidadeText = (EditText) findViewById(R.id.editCidadeText);
        editUFText = (EditText) findViewById(R.id.editUFText);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void Cadastrar(){
    }
}