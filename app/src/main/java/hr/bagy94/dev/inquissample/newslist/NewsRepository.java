package hr.bagy94.dev.inquissample.newslist;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import hr.bagy94.dev.inquissample.BaseRepository;
import hr.bagy94.dev.inquissample.api_calls.NewsService;
import hr.bagy94.dev.inquissample.model.Article;
import hr.bagy94.dev.inquissample.model.NewsListServiceResponse;
import hr.bagy94.dev.inquissample.utils.OnApiServiceFinished;
import hr.bagy94.dev.inquissample.utils.ServiceProvider;
import retrofit2.Call;

class NewsRepository extends BaseRepository implements OnApiServiceFinished<NewsListServiceResponse> {

    private static final String TAG = "NewsRepository";
    protected NewsService mService;
    protected MutableLiveData<List<Article>> mArticles = new MutableLiveData<>();

    public NewsRepository() {
        mService = ServiceProvider.getRetrofit().create(NewsService.class);
    }

    public LiveData<List<Article>> getTopHeadlines(String category) {
        callForData(mService.getTopHeadlines(null, category, null, null), this);
        return mArticles;
    }

    public LiveData<List<Article>> getEverything(String keyWord) {
        Call<NewsListServiceResponse> call = mService.getEverything(keyWord, null, "en", null, null, null, null, null, null, null);
        callForData(call, this);
        return mArticles;
    }

    @Override
    public void onSuccessfulApiService(NewsListServiceResponse response) {
        mArticles.setValue(response.getArticles());
    }
}
