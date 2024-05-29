package com.example.tarefa5_regissonandre.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("{cep}/json/")
    Call<Endereco> getEnderecoByCep(@Path("cep") String cep);
}

