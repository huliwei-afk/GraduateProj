package com.example.graduateproj.mainPack.homePack.tabFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.graduateproj.R;
import com.example.graduateproj.mainPack.homePack.model.ElectricBean;
import com.example.graduateproj.mainPack.homePack.presenter.CommodityPresenter;
import com.example.graduateproj.mainPack.homePack.util.ElectricItemAdapter;
import com.example.graduateproj.mainPack.homePack.util.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommodityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommodityFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String KIND_KEY = "kind";
    private static final int ITEM_SPACE = 15;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView electricRecyclerView;
    private CommodityPresenter commodityPresenter;
    private List<ElectricBean.ElectricItemBean> electricItemBeanList = new ArrayList<>();

    public CommodityFragment() {
        // Required empty public constructor
    }

    public static CommodityFragment newInstance(int kind) {
        CommodityFragment fragment = new CommodityFragment();
        Bundle args = new Bundle();
        args.putInt(KIND_KEY, kind);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_commodity, container, false);
        initViews(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        int recyclerKind = 0;
        if (getArguments() != null) {
            recyclerKind = getArguments().getInt(KIND_KEY);
        }

        switch (recyclerKind) {
            case RecyclerKind.RECYCLER_NORMAL:
                commodityPresenter.getElectricItemAndSet();
                break;
//            case RecyclerKind.RECYCLER_GRID:
//                view = inflater.inflate(R.layout.fragment_commodity1, container, false);
//                break;
            default:
                break;
        }
    }

    @SuppressLint("ResourceAsColor")
    private void initViews(View view) {
        swipeRefreshLayout = view.findViewById(R.id.home_swipe_refresh);
        swipeRefreshLayout.setColorSchemeColors(R.color.main_FC438C);

        electricRecyclerView = view.findViewById(R.id.home_recycler);
        
        commodityPresenter = new CommodityPresenter(this);
    }

    public void initRecyclerViewForElectric(List<ElectricBean.ElectricItemBean> list) {
        electricItemBeanList.addAll(list);
        electricRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        electricRecyclerView.addItemDecoration(new SpaceItemDecoration(ITEM_SPACE));
        electricRecyclerView.setLayoutAnimation(new LayoutAnimationController(AnimationUtils.loadAnimation(requireContext(), R.anim.electric_recycler_animation)));
        electricRecyclerView.setAdapter(new ElectricItemAdapter(requireContext(), electricItemBeanList));
    }
}