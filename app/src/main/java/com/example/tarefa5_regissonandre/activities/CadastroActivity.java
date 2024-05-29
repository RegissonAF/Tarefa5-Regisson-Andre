package com.example.tarefa5_regissonandre.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tarefa5_regissonandre.R;
import com.example.tarefa5_regissonandre.api.Endereco;
import com.example.tarefa5_regissonandre.api.ApiService;
import com.example.tarefa5_regissonandre.models.Aluno;
import com.example.tarefa5_regissonandre.service.AlunoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastroActivity extends AppCompatActivity {

    private EditText etRa, etNome, etCep, etLogradouro, etComplemento, etBairro, etCidade, etUf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etRa = findViewById(R.id.etRa);
        etNome = findViewById(R.id.etNome);
        etCep = findViewById(R.id.etCep);
        etLogradouro = findViewById(R.id.etLogradouro);
        etComplemento = findViewById(R.id.etComplemento);
        etBairro = findViewById(R.id.etBairro);
        etCidade = findViewById(R.id.etCidade);
        etUf = findViewById(R.id.etUf);
        Button btnBuscarCep = findViewById(R.id.btnBuscarCep);
        Button btnCadastrar = findViewById(R.id.btnCadastrar);

        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarCep();
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarAluno();
            }
        });
    }

    private void buscarCep() {
        String cep = etCep.getText().toString();

        if (cep.isEmpty()) {
            Toast.makeText(this, "Por favor, informe o CEP.", Toast.LENGTH_SHORT).show();
            return;
        }

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<Endereco> call = service.getEnderecoByCep(cep);

        call.enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                if (response.isSuccessful()) {
                    Endereco endereco = response.body();
                    if (endereco != null) {
                        etLogradouro.setText(endereco.getLogradouro());
                        etComplemento.setText(endereco.getComplemento());
                        etBairro.setText(endereco.getBairro());
                        etCidade.setText(endereco.getLocalidade());
                        etUf.setText(endereco.getUf());
                    } else {
                        Toast.makeText(CadastroActivity.this, "Endereço não encontrado.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CadastroActivity.this, "Erro ao buscar CEP.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Endereco> call, @NonNull Throwable t) {
                Toast.makeText(CadastroActivity.this, "Falha na comunicação: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cadastrarAluno() {
        int ra = Integer.parseInt(etRa.getText().toString());
        String nome = etNome.getText().toString();
        String cep = etCep.getText().toString();
        String logradouro = etLogradouro.getText().toString();
        String complemento = etComplemento.getText().toString();
        String bairro = etBairro.getText().toString();
        String cidade = etCidade.getText().toString();
        String uf = etUf.getText().toString();

        Aluno aluno = new Aluno(ra, nome, cep, logradouro, complemento, bairro, cidade, uf);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://65e12911d3db23f7624a7852.mockapi.io/")  // Substitua pela URL base do seu MockAPI
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        AlunoService service = retrofit.create(AlunoService.class);
        Call<Aluno> call = service.createAluno(aluno);

        call.enqueue(new Callback<Aluno>() {
            @Override
            public void onResponse(@NonNull Call<Aluno> call, @NonNull Response<Aluno> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CadastroActivity.this, "Aluno cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    // Limpar os campos
                    etRa.setText("");
                    etNome.setText("");
                    etCep.setText("");
                    etLogradouro.setText("");
                    etComplemento.setText("");
                    etBairro.setText("");
                    etCidade.setText("");
                    etUf.setText("");
                } else {
                    Toast.makeText(CadastroActivity.this, "Erro ao cadastrar aluno.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Aluno> call, @NonNull Throwable t) {
                Toast.makeText(CadastroActivity.this, "Falha na comunicação: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
