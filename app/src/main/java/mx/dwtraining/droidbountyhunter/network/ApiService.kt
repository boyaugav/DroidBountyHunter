package mx.dwtraining.droidbountyhunter.network

import mx.dwtraining.droidbountyhunter.models.Fugitivo
import mx.dwtraining.droidbountyhunter.models.FugitivoRequest
import mx.dwtraining.droidbountyhunter.models.FugitivoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @GET("fugitivos")
    fun getFugitivos(): Call<List<Fugitivo>>
    @Headers("Content-Type: application/json")
    @POST("atrapados")
    fun postFugitivo(@Body fugitivoRequest: FugitivoRequest): Call<FugitivoResponse>
}