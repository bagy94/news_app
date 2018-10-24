package hr.bagy94.dev.inquissample.api_calls;

import androidx.annotation.Nullable;
import hr.bagy94.dev.inquissample.model.NewsListServiceResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NewsService {
    @Headers("X-Api-Key: 57f3e345dd004225a7201d2f93d028f9")
    @GET("v2/top-headlines")
    Call<NewsListServiceResponse> getTopHeadlines(@Nullable @Query("q") String keyWord,
                                                  @Query("category") String category,
                                                  @Nullable @Query("page") Integer page,
                                                  @Nullable @Query("pageSize") Integer pageSize);

    @Headers("X-Api-Key: 57f3e345dd004225a7201d2f93d028f9")
    @GET("v2/everything")
    Call<NewsListServiceResponse> getEverything(@Query("q") String keyWord,
                                                @Nullable @Query("sources") String sources,
                                                @Nullable @Query("language") String language,
                                                @Nullable @Query("domains") String domains,
                                                @Nullable @Query("excludeDomains") String excludeDomains,
                                                @Nullable @Query("from") String dateFrom,
                                                @Nullable @Query("to") String dateTo,
                                                @Nullable @Query("sortBy") String sortBy,
                                                @Nullable @Query("page") Integer page,
                                                @Nullable @Query("pageSize") Integer pageSize);
}
