package hr.bagy94.dev.inquissample.newslist;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import hr.bagy94.dev.inquissample.BaseFragment;
import hr.bagy94.dev.inquissample.R;
import hr.bagy94.dev.inquissample.databinding.NewsListFragmentBinding;
import hr.bagy94.dev.inquissample.model.Article;

public class NewsListFragment extends BaseFragment<NewsListViewModel, NewsListFragmentBinding> implements NewsListAdapter.OnArticleSelected {


    public static NewsListFragment newInstance() {
        return new NewsListFragment();
    }

    private NewsListAdapter mAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListView();

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.news_categories, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBinding.selectCategory.setAdapter(spinnerAdapter);
        mBinding.selectCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String category = parent.getAdapter().getItem(position).toString();
                mViewModel.categorySelected(category.toLowerCase()).observe(NewsListFragment.this, new Observer<List<Article>>() {
                    @Override
                    public void onChanged(List<Article> articles) {
                        mAdapter.updateDataSet(articles);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initListView() {
        mAdapter = new NewsListAdapter(new ArrayList<Article>(), this);
        mBinding.rvNewsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.rvNewsList.setAdapter(mAdapter);
    }

    @Override
    protected int getViewId() {
        return R.layout.news_list_fragment;
    }

    @Override
    protected Class<NewsListViewModel> getViewModelClass() {
        return NewsListViewModel.class;
    }

    public void openDetails() {
        Bundle args = new Bundle();
        args.putParcelable("selected_article", mViewModel.getSelectedArticle());
        navigate().navigate(R.id.action_item_selected, args);
    }

    @Override
    public void onArticleSelectedListener(Article selectedArticle) {
        mViewModel.articleSelected(selectedArticle);
    }
}
