package org.lulzm.redsmurf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.stetho.Stetho;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    
    private static final String TAG = MainActivity.class.getSimpleName();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);
        
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://koreanjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        
        KoreanJsonService service = retrofit.create(KoreanJsonService.class);
        service.listTodos().enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                List<Todo> todos = response.body();
                Log.d(TAG, "onResponse: " + todos);
            }
            
            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
            
            }
        });
        
    }
}