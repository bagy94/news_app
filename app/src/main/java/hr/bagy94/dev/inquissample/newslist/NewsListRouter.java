package hr.bagy94.dev.inquissample.newslist;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import hr.bagy94.dev.inquissample.BaseRouter;
import hr.bagy94.dev.inquissample.utils.SingleLiveEvent;

class NewsListRouter extends BaseRouter {
    private SingleLiveEvent mItemSelected = new SingleLiveEvent();

    @Override
    public void observe(final LifecycleOwner owner) {
        super.observe(owner);
        mItemSelected.observe(owner, new Observer() {
            @Override
            public void onChanged(Object o) {
                if (owner instanceof NewsListFragment) {
                    ((NewsListFragment) owner).openDetails();
                }
            }
        });
    }

    public SingleLiveEvent getItemSelectedEvent() {
        return mItemSelected;
    }
}
