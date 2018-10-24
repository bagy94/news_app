package hr.bagy94.dev.inquissample;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import hr.bagy94.dev.inquissample.utils.SingleLiveEvent;

public abstract class BaseRouter {
    protected final SingleLiveEvent mBack = new SingleLiveEvent();

    public void observe(final LifecycleOwner owner) {
        mBack.observe(owner, new Observer() {
            @Override
            public void onChanged(Object o) {
                if (owner instanceof BaseFragment<?, ?>) ((BaseFragment) owner).back();
            }
        });
    }
}
