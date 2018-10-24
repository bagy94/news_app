package hr.bagy94.dev.inquissample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public abstract class BaseFragment<VIEWMODEL extends BaseViewModel, BINDING extends ViewDataBinding> extends Fragment {

    protected VIEWMODEL mViewModel;

    protected BINDING mBinding;

    abstract protected int getViewId();

    abstract protected Class<VIEWMODEL> getViewModelClass();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(getViewModelClass());
        mViewModel.mRouter.observe(this);
        Bundle args = getArguments();
        if (args != null) {
            onParseArguments(args);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), getViewId(), container, false);
        if (mBinding != null) {
            mBinding.setLifecycleOwner(this);
        }
        bindVariable();
        return mBinding != null ? mBinding.getRoot() : null;
    }

    protected void onParseArguments(Bundle args) {

    }

    public void bindVariable() {

    }

    public void back() {
        navigate().navigateUp();
    }

    protected NavController navigate() {
        return Navigation.findNavController(getView());
    }
}
