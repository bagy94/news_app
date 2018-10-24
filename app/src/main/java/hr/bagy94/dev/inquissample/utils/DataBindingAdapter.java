package hr.bagy94.dev.inquissample.utils;

import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import androidx.databinding.BindingAdapter;
import hr.bagy94.dev.inquissample.R;

public class DataBindingAdapter {

    @BindingAdapter("imgUrl")
    public static void setImage(ImageView imageView, String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get().load(imageUrl).error(R.drawable.ic_error_black_24dp).fit().centerCrop().into(imageView);
        } else {
            imageView.setVisibility(View.GONE);
        }
    }
}
