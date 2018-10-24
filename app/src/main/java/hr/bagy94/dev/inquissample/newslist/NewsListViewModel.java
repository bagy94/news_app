package hr.bagy94.dev.inquissample.newslist;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import hr.bagy94.dev.inquissample.BaseViewModel;
import hr.bagy94.dev.inquissample.model.Article;

public class NewsListViewModel extends BaseViewModel<NewsListRouter> {

    private NewsRepository mRepository;
    private String mCategorySelected;
    private LiveData<List<Article>> mArticles;
    private Article mSelectedArticle;

    public NewsListViewModel(@NonNull Application application) {
        super(application, new NewsListRouter());
        mRepository = new NewsRepository();
    }

    private LiveData<List<Article>> getTopHeadlines(String category) {
        return mRepository.getTopHeadlines(category);
    }

    private LiveData<List<Article>> getEverything(String keyWord) {
        return mRepository.getEverything(keyWord);
    }

    public LiveData<List<Article>> categorySelected(String item) {
        if (mCategorySelected != null && mCategorySelected.equalsIgnoreCase(item))
            return mArticles;
        mCategorySelected = item;
        mArticles = getTopHeadlines(item);
        return mArticles;
    }

    public void articleSelected(Article selectedArticle) {
        if (selectedArticle != null) {
            mSelectedArticle = selectedArticle;
            mRouter.getItemSelectedEvent().call();
        }
    }

    public Article getSelectedArticle() {
        return mSelectedArticle;
    }
}
