package com.example.graduateproj.mainPack.donatePack

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.example.graduateproj.commonUtil.RxBus
import com.example.graduateproj.commonUtil.RxClickUtil
import com.example.graduateproj.databinding.FragmentDonateBinding
import com.example.graduateproj.loginPack.util.DialogManager
import com.example.graduateproj.mainPack.donatePack.model.DonateJsonBean
import com.example.graduateproj.mainPack.donatePack.presenter.DonatePresenter
import com.example.graduateproj.mainPack.donatePack.util.DonateItemAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class DonateFragment : Fragment() {

    companion object {
        private const val TAG = "DonateFragment"
    }

    private var _binding: FragmentDonateBinding? = null
    private var donateBeanList: MutableList<DonateJsonBean.DonateItemBean> = ArrayList()
    internal lateinit var donateRecyclerView: RecyclerView
    private lateinit var donatePresenter: DonatePresenter
    internal lateinit var donateRefresh: SwipeRefreshLayout
    private lateinit var donateButton: FloatingActionButton

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private fun initViews(root: View) {
        donateRecyclerView = root.findViewById(R.id.donate_recyclerview)
        donateRefresh =
            root.findViewById<SwipeRefreshLayout?>(R.id.donate_refresh_layout).apply {
                setColorSchemeColors(resources.getColor(R.color.main_FC438C))
            }
        donateButton = root.findViewById(R.id.donate_fab)

        donatePresenter = DonatePresenter(this)
    }

    private fun initEvents() {
        donateRefresh.setOnRefreshListener {
            donatePresenter.getMoreDonateItem(donateBeanList)

        }

        activity?.let {
            RxClickUtil.clickEvent(donateButton, it)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    DialogManager.showAddDonateDialog(requireActivity())
                }
        }
    }

    fun initRecyclerView(dataList: List<DonateJsonBean.DonateItemBean>) {
        donateBeanList.addAll(dataList)
        donateRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            layoutAnimation = LayoutAnimationController(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.donate_recycler_animation
                )
            )
            adapter = context?.let { DonateItemAdapter(requireActivity(), donateBeanList) }
        }
    }

    private fun initRxBus() {
        RxBus.getInstance().toObservable(DonateJsonBean.DonateItemBean::class.java).observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                donatePresenter.insertNewDonateItem(it, donateBeanList)
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView")

        val donateViewModel =
            ViewModelProvider(this).get(DonateViewModel::class.java)

        _binding = FragmentDonateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initViews(root)
        initEvents()
        initRxBus()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        donatePresenter.getDonateItemAndSet()
        Log.d(TAG, "onViewCreated")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
        _binding = null
    }

}