package hr.bagy94.dev.inquissample.newslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import hr.bagy94.dev.inquissample.R;
import hr.bagy94.dev.inquissample.databinding.ItemNewsListBinding;
import hr.bagy94.dev.inquissample.model.Article;

class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListItemViewHolder> {

    interface OnArticleSelected {
        void onArticleSelectedListener(Article selectedArticle);
    }

    private List<Article> mArticles;
    private OnArticleSelected mListener;

    public NewsListAdapter(List<Article> mArticles, OnArticleSelected mListener) {
        this.mArticles = mArticles;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public NewsListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemNewsListBinding viewBinding = DataBindingUtil.inflate(inflater, R.layout.item_news_list, parent, false);
        return new NewsListItemViewHolder(viewBinding, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListItemViewHolder holder, int position) {
        holder.mBinding.setArticle(mArticles.get(position));
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    static class NewsListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemNewsListBinding mBinding;
        private OnArticleSelected mListener;

        public NewsListItemViewHolder(ItemNewsListBinding binding, OnArticleSelected listener) {
            super(binding.getRoot());
            binding.getRoot().setOnClickListener(this);
            mBinding = binding;
            mListener = listener;
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onArticleSelectedListener(mBinding.getArticle());
            }
        }
    }

    public void updateDataSet(List<Article> articles) {
        this.mArticles = articles;
        notifyDataSetChanged();
    }
}
