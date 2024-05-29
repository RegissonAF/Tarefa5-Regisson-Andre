package com.example.tarefa5_regissonandre.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.tarefa5_regissonandre.R;
import com.example.tarefa5_regissonandre.models.Aluno;

import java.util.List;

public class AlunoAdapter extends ArrayAdapter<Aluno> {
    public AlunoAdapter(Context context, List<Aluno> alunos) {
        super(context, 0, alunos);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Aluno aluno = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_aluno, parent, false);
        }

        TextView tvNome = convertView.findViewById(R.id.tvNome);
        TextView tvRa = convertView.findViewById(R.id.tvRa);
        TextView tvCidade = convertView.findViewById(R.id.tvCidade);

        assert aluno != null;
        tvNome.setText(aluno.getNome());
        tvRa.setText("RA: " + aluno.getRa());
        tvCidade.setText("Cidade: " + aluno.getCidade());

        return convertView;
    }
}