package com.sbyparking.car.surabayaparking;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.sbyparking.car.surabayaparking.util.CustomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;


/**
 * Created by Meidika on 19/05/2018.
 */
public class ZoomDialog extends CustomDialog {

    public static final String TAG = "ZoomDialog";
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.ivZoomable)
    PhotoView ivZoomable;

    @BindView(R.id.pbLoading) ProgressBar pbLoading;

    @BindView(R.id.ivClose) ImageView ivClose;

    @BindView(R.id.tvProgress) TextView tvProgress;

    FragmentActivity fragmentActivity;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_zoom, container, false);

        unbinder = ButterKnife.bind(this, view);

        if(getDialog().getWindow() != null)
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        fragmentActivity = this.getActivity();

        if(getArguments() != null){

            String saranZona = getArguments().getString("saranZona");
            int photo = getArguments().getInt("photo");

            tvTitle.setText(saranZona);

            ivZoomable.setMinimumScale(1f);
            ivZoomable.setScale(2f);

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_placeholder)
                    .error(R.drawable.ic_image_not_found)
                    .priority(Priority.HIGH);

            Glide.with(fragmentActivity)
                    .load(photo)
                    .apply(options)
                    .thumbnail(0.5f)
                    .into(ivZoomable);
        }

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        DisplayMetrics displayMetrics = new DisplayMetrics();

        int width;

        if(getActivity() != null)
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        else
            return;

        width = displayMetrics.widthPixels;

        Window window = getDialog().getWindow();
        if(window == null) return;
        WindowManager.LayoutParams params = window.getAttributes();

        if(width == 0)
            width = 1200;

        width = width * 90/100;

        params.width = width;
        params.height = MATCH_PARENT;
        window.setAttributes(params);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();

        Glide.get(fragmentActivity).clearMemory();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        Glide.get(fragmentActivity).clearMemory();
    }
}
