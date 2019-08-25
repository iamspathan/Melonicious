package abhilash.example.com.melonicious.addmentee;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import abhilash.example.com.melonicious.model.Mentee;
import abhilash.example.com.melonicious.retrofit.RetrofitInstance;
import abhilash.example.com.melonicious.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMenteeRepository {
    private RetrofitService service;

    private static AddMenteeRepository addMenteeRepository;

    private AddMenteeRepository() {
        // Default constructor
    }

    public static AddMenteeRepository getInstance() {
        if (addMenteeRepository == null) {
            addMenteeRepository = new AddMenteeRepository();
        }
        return addMenteeRepository;
    }

    public LiveData<Mentee> getGitHubUser(String username) {
        final MutableLiveData<Mentee> data = new MutableLiveData<>();
        service = RetrofitInstance.getRetrofit().create(RetrofitService.class);
        service.getMentee(username).enqueue(new Callback<Mentee>() {
            @Override
            public void onResponse(Call<Mentee> call, Response<Mentee> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Mentee> call, Throwable t) {

            }
        });

        return data;
    }
}
