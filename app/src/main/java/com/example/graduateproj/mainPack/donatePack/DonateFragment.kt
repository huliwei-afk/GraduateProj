package com.example.graduateproj.mainPack.donatePack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class DonateFragment : Fragment() {

    private var _binding: FragmentDonateBinding? = null
    private lateinit var donateRecyclerView: RecyclerView
    private lateinit var donatePresenter: DonatePresenter
    private lateinit var donateRefresh: SwipeRefreshLayout

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
        donateRecyclerView = root.findViewById(R.id.donate_recyclerview)
        donateRefresh = root.findViewById<SwipeRefreshLayout?>(R.id.donate_refresh_layout).apply {
            setColorSchemeColors(resources.getColor(R.color.main_FC438C))
        }

        donatePresenter = DonatePresenter(this)
    }

    private fun initEvents() {
        donateRefresh.setOnRefreshListener(object :SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {

            }
        })
    }

    fun initRecyclerView(dataList: List<DonateJsonBean.DonateItemBean>) {
        donateRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = context?.let { DonateItemAdapter(it, dataList) }
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