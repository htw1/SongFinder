package com.htw.songfinder.ui;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.htw.songfinder.callback.RecyclerViewClickListener;
import com.htw.songfinder.R;
import com.htw.songfinder.adapter.RecyclerMainAdapter;
import com.htw.songfinder.databinding.MainFragment;
import com.htw.songfinder.models.CombinedResult;
import com.htw.songfinder.network.Constant;
import com.htw.songfinder.network.procesDialog.ApiResponse;
import com.htw.songfinder.viewModel.ViewModelApp;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.squareup.picasso.Picasso;

public class MainListFragment extends Fragment implements RecyclerViewClickListener, LifecycleOwner {

    public static final String KEY_MAIN_FRAGMENT_ID = "MainListFragment";

    RecyclerMainAdapter mainAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    CombinedResult combinedLocalResult;

    private LifecycleRegistry mLifecycleRegistry;
    ViewModelApp viewModel;
    MainFragment binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_list, container,false);

        //Lifecycle
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);

        // Recycler
        mainAdapter = new RecyclerMainAdapter(getActivity(),this );
        mLayoutManager = new LinearLayoutManager(this.getContext());
        binding.recyclerView.setLayoutManager(mLayoutManager);
        binding.recyclerView.setAdapter(mainAdapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel =
                ViewModelProviders
                .of(getActivity())
                .get(ViewModelApp.class);

        observeViewModel(viewModel);
    }

    private void observeViewModel(ViewModelApp viewModelAppe) {

        viewModelAppe.responseLiveDataCombinedResult.observe(getActivity(),this::consumeResponse);

    }

    private void consumeResponse(ApiResponse apiResponse) {

        switch (apiResponse.status){

            case LOADING:
               binding.setIsLoading(true);
                break;

            case SUCCESS:
                binding.setIsLoading(false);
                loadOnlineView();
                if (apiResponse.combinedResult.getArtistsWithPhoto() != null){
                    mainAdapter.setProjectList(apiResponse.combinedResult.getArtistsWithPhoto());
                    combinedLocalResult = apiResponse.combinedResult;
                }

            case ERROR:
                if (!Constant.checkInternetConnection(getActivity())) {
                    binding.setIsLoading(false);
                    loadOfflineView();
                    Toast.makeText(getActivity(), getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
                }
            default:
                break;
        }

    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

        View view = View.inflate(getActivity(), R.layout.custom_dialog_image_show, null);
        ImageView imgRefInflated = (ImageView) view.findViewById(R.id.dialog_imageview);
        Picasso
                .get()
                .load(combinedLocalResult.getArtistsWithPhoto().get(position).second)
                .placeholder(R.drawable.ic_image_black_24dp)
                .resize(400, 400)
                .into(imgRefInflated);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setMessage(combinedLocalResult.getArtistsWithPhoto().get(position).first);
        dialog.setView(view);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.show();
    }

    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }


    private void loadOfflineView() {
        binding.noConnection.setVisibility(View.VISIBLE);
        binding.recyclerView.setVisibility(View.GONE);

    }
    private void loadOnlineView() {
        binding.noConnection.setVisibility(View.GONE);
        binding.recyclerView.setVisibility(View.VISIBLE);

    }
}
