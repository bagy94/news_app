package hr.bagy94.dev.inquissample.utils;

import java.util.concurrent.atomic.AtomicBoolean;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class SingleLiveEvent extends MutableLiveData {
    private final AtomicBoolean mHasPending = new AtomicBoolean(false);

    @MainThread
    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull final Observer observer) {
        super.observe(owner, new Observer() {
            @Override
            public void onChanged(Object o) {
                if (mHasPending.compareAndSet(true, false)) {
                    observer.onChanged(o);
                }
            }
        });
    }

    @MainThread
    @Override
    public void setValue(Object value) {
        mHasPending.set(true);
        super.setValue(value);
    }

    @MainThread
    public void call() {
        setValue(null);
    }
}
