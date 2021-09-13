package co.xware_tech.pagingtest.network.service

import co.xware_tech.pagingtest.dto.PhotoDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

const val BASE_URL = "https://picsum.photos/v2/"

val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

val okHttpClient = OkHttpClient.Builder().apply {
    addInterceptor(httpLoggingInterceptor)
}.build()


val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

interface PhotoServiceInterface {
    @GET("list")
    suspend fun getPhotoLists(
        @Query("page") pagerNumber: Int,
        @Query("limit") pageLimit: Int
    ): Response<List<PhotoDto>>
}

object PhotoService {
    val photoService =  retrofit.create(PhotoServiceInterface::class.java)
}