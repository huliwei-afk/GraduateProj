package com.example.graduateproj.loginPack.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.graduateproj.R
import com.example.graduateproj.commonUtil.AppNavigator
import com.example.graduateproj.commonUtil.RxClickUtil
import com.example.graduateproj.loginPack.util.VerifyCodeDialogManager
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 * Use the [ForgetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ForgetFragment : Fragment() {
    private lateinit var arrowBack: ImageView
    private lateinit var getVerifyCode: TextView

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_forget, container, false)
        initViews(view)
        initEvents()

        return view
    }

    private fun initViews(view: View) {
        arrowBack = view.findViewById(R.id.login_back)
        getVerifyCode = view.findViewById(R.id.get_verify_code)
    }

    private fun initEvents() {
        RxClickUtil.clickEvent(arrowBack)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                AppNavigator.openLoginFragment(arrowBack)
            }

        RxClickUtil.clickEvent(getVerifyCode)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                context?.let { it -> VerifyCodeDialogManager.showDialog(it) }
            }

    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ForgetFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): ForgetFragment {
            val fragment = ForgetFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}