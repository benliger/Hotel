package benliger.fr.hotel.service;

import android.support.annotation.NonNull;

import com.squareup.moshi.Moshi;

import java.io.IOException;

import benliger.fr.hotel.model.converter.MoshiDateConverter;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class HotelRetrofit {

	private static final String BASE_URL = "http://api.weekendesk.com/api/";

	public HotelApi build() {
		OkHttpClient httpClient = getOkHttpClient();
		Converter.Factory converter = getMoshiConverterFactory();

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(converter)
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.client(httpClient)
				.build();

		return retrofit.create(HotelApi.class);
	}


	// =============================
	// PRIVATE METHODS
	// =============================
	/**
	 * @return le client OkHttp qui log les requetes HTTP et ajoute les headers pour le socle
	 */
	@NonNull
	private OkHttpClient getOkHttpClient() {
		return new OkHttpClient.Builder()
				.addNetworkInterceptor(getHttpLoggingInterceptor())
				.addInterceptor(new Interceptor() {
					@Override
					public Response intercept(Chain chain) throws IOException {
						Request modifiedRequest = addHeaders(chain.request());
						return chain.proceed(modifiedRequest);
					}
				})
				.build();
	}

	/**
	 * @param originalRequest la requete avant modification
	 * @return la requête avec les headers pour le socle
	 */
	private Request addHeaders(Request originalRequest) {
		// Request customization: add request headers
		Request.Builder requestBuilder = originalRequest.newBuilder()
				.addHeader("Content-type", "application/json")
				.addHeader("Accept", "gzip");
		return requestBuilder.build();
	}

	/**
	 * @return interceptor pour loger les requetes HTTP
	 */
	@NonNull
	private HttpLoggingInterceptor getHttpLoggingInterceptor() {
		HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
		httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		return httpLoggingInterceptor;
	}

	/**
	 * @return la factory de converter Moshi (json) qui gère les dates atom
	 */
	@NonNull
	private Converter.Factory getMoshiConverterFactory() {
		Moshi moshi = new Moshi.Builder().add(new MoshiDateConverter()).build();
		return MoshiConverterFactory.create(moshi);
	}

}
