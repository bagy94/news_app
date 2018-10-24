package hr.bagy94.dev.inquissample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public abstract class BaseViewModel<ROUTER extends BaseRouter> extends AndroidViewModel {

    protected ROUTER mRouter;

    public BaseViewModel(@NonNull Application application, ROUTER router) {
        super(application);
        mRouter = router;
    }

    public void onBack() {
        mRouter.mBack.call();
    }
}
