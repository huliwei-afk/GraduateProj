package com.example.graduateproj.loginPack.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.graduateproj.R
import com.example.graduateproj.commonUtil.AppNavigator
import com.example.graduateproj.commonUtil.RxClickUtil
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private lateinit var policyCheckbox: CheckBox
    private lateinit var environmentPolicy: TextView
    private lateinit var noAccount: TextView
    private lateinit var forgetPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        initViews(view)
        initEvents()

        return view
    }

    private fun initViews(view: View) {
        policyCheckbox = view.findViewById(R.id.login_checkBox)
        environmentPolicy = view.findViewById(R.id.environment_policy)
        noAccount = view.findViewById(R.id.do_not_have_account)
        forgetPassword = view.findViewById(R.id.forget_password)
    }

    private fun initEvents() {
        RxClickUtil.clickEvent(policyCheckbox)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {

            }

        RxClickUtil.clickEvent(environmentPolicy)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {

            }

        RxClickUtil.clickEvent(noAccount)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {

            }

        RxClickUtil.clickEvent(forgetPassword)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                AppNavigator.openNoAccountFragment(noAccount)
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
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): LoginFragment {
            val fragment = LoginFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}