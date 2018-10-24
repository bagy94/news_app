package hr.bagy94.dev.inquissample.newsdescription;

import android.os.Bundle;

import hr.bagy94.dev.inquissample.BaseFragment;
import hr.bagy94.dev.inquissample.R;
import hr.bagy94.dev.inquissample.databinding.NewsFragmentBinding;
import hr.bagy94.dev.inquissample.model.Article;

public class NewsFragment extends BaseFragment<NewsViewModel, NewsFragmentBinding> {
    private final static String ARG_ARTICLE = "selected_article";

    public static NewsFragment newInstance(Article article) {
        NewsFragment fragment = new NewsFragment();
        Bundle b = new Bundle();
        b.putParcelable(ARG_ARTICLE, article);
        fragment.setArguments(b);
        return new NewsFragment();
    }


    @Override
    protected void onParseArguments(Bundle args) {
        super.onParseArguments(args);
        if (args.containsKey(ARG_ARTICLE)) {
            Article article = args.getParcelable(ARG_ARTICLE);
            mViewModel.setArticle(article);
        }
    }

    @Override
    public void bindVariable() {
        if (mViewModel.getArticle() != null) {
            mBinding.setViewModel(mViewModel);
        }
    }

    @Override
    protected int getViewId() {
        return R.layout.news_fragment;
    }

    @Override
    protected Class<NewsViewModel> getViewModelClass() {
        return NewsViewModel.class;
    }
}
