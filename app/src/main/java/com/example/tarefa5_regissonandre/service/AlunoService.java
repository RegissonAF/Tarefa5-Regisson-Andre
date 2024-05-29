package com.example.tarefa5_regissonandre.service;

import com.example.tarefa5_regissonandre.models.Aluno;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AlunoService {
    @GET("alunos")  // Endpoint do MockAPI para listar alunos
    Call<List<Aluno>> getAlunos();

    @POST("alunos")  // Endpoint do MockAPI para criar aluno
    Call<Aluno> createAluno(@Body Aluno aluno);
}

