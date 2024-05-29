package com.example.tarefa5_regissonandre.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tarefa5_regissonandre.R;
import com.example.tarefa5_regissonandre.models.Aluno;
import com.example.tarefa5_regissonandre.service.AlunoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvAlunos = findViewById(R.id.rvAlunos);
        Button btnAddAluno = findViewById(R.id.btnAddAluno);

        btnAddAluno.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
            startActivity(intent);
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvAlunos.setLayoutManager(layoutManager);

        // Carregar a lista de alunos do MockAPI
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://65e12911d3db23f7624a7852.mockapi.io/")  // Substituir pela URL correta do seu MockAPI
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AlunoService service = retrofit.create(AlunoService.class);
        Call<List<Aluno>> call = service.getAlunos();

        call.enqueue(new Callback<List<Aluno>>() {
            @Override
            public void onResponse(@NonNull Call<List<Aluno>> call, @NonNull Response<List<Aluno>> response) {
                if (response.isSuccessful()) {
                    List<Aluno> alunos = response.body();
                    MainActivity.AlunoAdapter adapter = new AlunoAdapter(alunos);
                    rvAlunos.setAdapter(adapter);
                } else {
                    // Tratar erro
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Aluno>> call, @NonNull Throwable t) {
                // Tratar falha na comunicação
            }
        });
    }
    private static class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {
        private List<Aluno> alunos;

        public AlunoAdapter(List<Aluno> alunos) {
            this.alunos = alunos;
        }

        @NonNull
        @Override
        public AlunoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_aluno, parent, false);
            return new AlunoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(AlunoViewHolder holder, int position) {
            Aluno aluno = alunos.get(position);
            holder.bind(aluno);
        }

        @Override
        public int getItemCount() {
            return alunos.size();
        }

        public static class AlunoViewHolder extends RecyclerView.ViewHolder {
            private TextView tvNome, tvRa, tvCidade;

            public AlunoViewHolder(View itemView) {
                super(itemView);
                tvNome = itemView.findViewById(R.id.tvNome);
                tvRa = itemView.findViewById(R.id.tvRa);
                tvCidade = itemView.findViewById(R.id.tvCidade);
            }

            public void bind(Aluno aluno) {
                tvNome.setText(aluno.getNome());
                tvRa.setText("RA: " + aluno.getRa());
                tvCidade.setText("Cidade: " + aluno.getCidade());
            }
        }
    }
}
