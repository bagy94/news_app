package hr.bagy94.dev.inquissample.newsdescription;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import hr.bagy94.dev.inquissample.BaseRouter;
import hr.bagy94.dev.inquissample.BaseViewModel;
import hr.bagy94.dev.inquissample.model.Article;

public class NewsViewModel extends BaseViewModel<BaseRouter> {

    private Article article;

    public NewsViewModel(@NonNull Application application) {
        super(application, new BaseRouter() {
            @Override
            public void observe(LifecycleOwner owner) {
                super.observe(owner);
            }
        });
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
