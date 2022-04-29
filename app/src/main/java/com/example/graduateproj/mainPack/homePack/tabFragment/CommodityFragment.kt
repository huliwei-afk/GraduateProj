package com.example.graduateproj.mainPack.homePack.tabFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.graduateproj.R
import com.example.graduateproj.mainPack.homePack.model.RecyclerBean.RecyclerItemBean
import com.example.graduateproj.mainPack.homePack.presenter.CommodityPresenter
import com.example.graduateproj.mainPack.homePack.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [CommodityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommodityFragment : Fragment() {

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val KIND_KEY = "kind"
        private const val ITEM_SPACE = 10
        private const val LAR_SPACE = 5
        fun newInstance(kind: Int): CommodityFragment {
            val fragment = CommodityFragment()
            val args = Bundle()
            args.putInt(KIND_KEY, kind)
            fragment.arguments = args
            return fragment
        }
    }

    internal lateinit var swipeRefreshLayout: SwipeRefreshLayout
    internal lateinit var recyclerView: RecyclerView
    private lateinit var commodityPresenter: CommodityPresenter
    private val electricItemBeanList: MutableList<RecyclerItemBean> = ArrayList()
    private val dailyItemBeanList: MutableList<RecyclerItemBean> = ArrayList()
    private val otherItemBeanList: MutableList<RecyclerItemBean> = ArrayList()

    private var kind = Int.MIN_VALUE

    override fun onCreate(savedInstanceState: Bundle?) {
        if (arguments != null) {
            kind = requireArguments().getInt(KIND_KEY)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_commodity, container, false)
        initViews(view)
        initEvents()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        commodityPresenter.getItemAndSet(kind)
    }

    private fun initViews(view: View) {
        swipeRefreshLayout = view.findViewById<SwipeRefreshLayout?>(R.id.home_swipe_refresh).apply {
            setColorSchemeColors(resources.getColor(R.color.main_FC438C))
        }

        recyclerView = view.findViewById(R.id.home_recycler)
        commodityPresenter = CommodityPresenter(this)
    }

    private fun initEvents() {
        swipeRefreshLayout.setOnRefreshListener {
            when(kind) {
                RecyclerKind.RECYCLER_NORMAL -> commodityPresenter.getMoreItem(electricItemBeanList)
                RecyclerKind.RECYCLER_GRID -> commodityPresenter.getMoreItem(dailyItemBeanList)
                RecyclerKind.RECYCLER_STAGGERED -> commodityPresenter.getMoreItem(otherItemBeanList)
            }
        }
    }

    fun initRecyclerViewForElectric(list: List<RecyclerItemBean>) {
        electricItemBeanList.addAll(list)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(LinearItemDecoration(ITEM_SPACE))
            layoutAnimation = LayoutAnimationController(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.electric_recycler_animation
                )
            )
            adapter = ElectricItemAdapter(requireActivity(), electricItemBeanList)
        }
    }

    fun initRecyclerViewForDaily(list: List<RecyclerItemBean>) {
        dailyItemBeanList.addAll(list)
        recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            addItemDecoration(GridItemDecoration(ITEM_SPACE, LAR_SPACE))
            layoutAnimation = LayoutAnimationController(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.donate_recycler_animation
                )
            )
            adapter = DailyItemAdapter(requireActivity(), dailyItemBeanList)
        }
    }

    fun initRecyclerViewForOther(list: List<RecyclerItemBean>) {
        otherItemBeanList.addAll(list)
        recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(GridItemDecoration(ITEM_SPACE, LAR_SPACE))
            layoutAnimation = LayoutAnimationController(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.donate_recycler_animation
                )
            )
            adapter = OtherItemAdapter(requireActivity(), otherItemBeanList)
        }
    }
}