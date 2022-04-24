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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.graduateproj.R;
import com.example.graduateproj.mainPack.homePack.model.RecyclerBean;
import com.example.graduateproj.mainPack.homePack.presenter.CommodityPresenter;
import com.example.graduateproj.mainPack.homePack.util.DailyItemAdapter;
import com.example.graduateproj.mainPack.homePack.util.ElectricItemAdapter;
import com.example.graduateproj.mainPack.homePack.util.GridItemDecoration;
import com.example.graduateproj.mainPack.homePack.util.LinearItemDecoration;

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
    private static final int LAR_SPACE = 5;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private CommodityPresenter commodityPresenter;
    private List<RecyclerBean.RecyclerItemBean> electricItemBeanList = new ArrayList<>();
    private List<RecyclerBean.RecyclerItemBean> dailyItemBeanList = new ArrayList<>();

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
        commodityPresenter.getItemAndSet(recyclerKind);
    }

    @SuppressLint("ResourceAsColor")
    private void initViews(@NonNull View view) {
        swipeRefreshLayout = view.findViewById(R.id.home_swipe_refresh);
        swipeRefreshLayout.setColorSchemeColors(R.color.main_FC438C);

        recyclerView = view.findViewById(R.id.home_recycler);

        commodityPresenter = new CommodityPresenter(this);
    }

    public void initRecyclerViewForElectric(List<RecyclerBean.RecyclerItemBean> list) {
        electricItemBeanList.addAll(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.addItemDecoration(new LinearItemDecoration(ITEM_SPACE));
        recyclerView.setLayoutAnimation(new LayoutAnimationController(AnimationUtils.loadAnimation(requireContext(), R.anim.electric_recycler_animation)));
        recyclerView.setAdapter(new ElectricItemAdapter(requireActivity(), electricItemBeanList));
    }

    public void initRecyclerViewForDaily(List<RecyclerBean.RecyclerItemBean> list) {
        dailyItemBeanList.addAll(list);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new GridItemDecoration(ITEM_SPACE, LAR_SPACE));
        recyclerView.setAdapter(new DailyItemAdapter(requireContext(), dailyItemBeanList));
    }
}