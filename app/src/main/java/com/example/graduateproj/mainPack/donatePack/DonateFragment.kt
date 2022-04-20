package com.example.graduateproj.mainPack.donatePack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.graduateproj.R
import com.example.graduateproj.databinding.FragmentDonateBinding
import com.example.graduateproj.mainPack.donatePack.model.DonateJsonBean
import com.example.graduateproj.mainPack.donatePack.presenter.DonatePresenter
import com.example.graduateproj.mainPack.donatePack.util.DonateItemAdapter
import kotlinx.coroutines.CoroutineScope

class DonateFragment : Fragment() {

    private var _binding: FragmentDonateBinding? = null
    private var donateBeanList : MutableList<DonateJsonBean.DonateItemBean> = ArrayList()
    internal lateinit var donateRecyclerView: RecyclerView
    private lateinit var donatePresenter: DonatePresenter
    internal lateinit var donateRefresh: SwipeRefreshLayout

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val donateViewModel =
            ViewModelProvider(this).get(DonateViewModel::class.java)

        _binding = FragmentDonateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initViews(root)
        initEvents()

        return root
    }

    private fun initViews(root: View) {
        donateRecyclerView = root.findViewById<RecyclerView?>(R.id.donate_recyclerview)
        donateRefresh = root.findViewById<SwipeRefreshLayout?>(R.id.donate_refresh_layout).apply {
            setColorSchemeColors(resources.getColor(R.color.main_FC438C))
        }

        donatePresenter = DonatePresenter(this)
    }

    private fun initEvents() {
        donateRefresh.setOnRefreshListener {
            donatePresenter.getMoreDonateItem(donateBeanList)

        }
    }

    fun initRecyclerView(dataList: List<DonateJsonBean.DonateItemBean>) {
        donateBeanList.addAll(dataList)
        donateRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = context?.let { DonateItemAdapter(it, donateBeanList) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        donatePresenter.getDonateItemAndSet()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}