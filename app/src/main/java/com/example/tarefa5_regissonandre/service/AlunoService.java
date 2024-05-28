package com.example.tarefa5_regissonandre.service;

import com.example.tarefa5_regissonandre.Aluno;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AlunoService {
    @POST("alunos")
    Call<Aluno> criarAluno(@Body Aluno aluno);
}
