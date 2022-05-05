package com.example.graduateproj.loginPack.ui

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.graduateproj.R
import com.example.graduateproj.commonUtil.AppNavigator
import com.example.graduateproj.commonUtil.RxClickUtil
import com.example.graduateproj.loginPack.presenter.LoginPresenter
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    internal lateinit var policyCheckbox: CheckBox
    private lateinit var environmentPolicy: TextView
    private lateinit var forgetPassword: TextView
    private lateinit var enterButton: Button
    private lateinit var policyLayout: RelativeLayout
    internal lateinit var accountNumber: EditText
    internal lateinit var passwordNumber: EditText
    private lateinit var loginPresenter: LoginPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        initViews(view)
        initEvents()

        return view
    }

    private fun initViews(view: View) {
        policyCheckbox = view.findViewById(R.id.login_checkBox)
        environmentPolicy = view.findViewById(R.id.environment_policy)
        forgetPassword = view.findViewById(R.id.forget_password)
        enterButton = view.findViewById(R.id.enter_button)
        policyLayout = view.findViewById(R.id.policy_layout)
        accountNumber = view.findViewById(R.id.account_number)
        passwordNumber = view.findViewById(R.id.password_number)

        loginPresenter = LoginPresenter(this)
    }

    private fun initEvents() {
        activity?.let {
            RxClickUtil.clickEvent(policyCheckbox, it)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {

                }

            RxClickUtil.clickEvent(environmentPolicy, it)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {

                }

            RxClickUtil.clickEvent(forgetPassword, it)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    AppNavigator.openNoAccountFragment(forgetPassword)
                }

            RxClickUtil.clickEvent(enterButton, it)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    if(loginPresenter.loginStrategy(requireContext())) {
                        AppNavigator.openMainContentActivity(requireContext())
                    }
                }
        }
    }

    internal fun shakePolicy() {
        val animator: ObjectAnimator = ObjectAnimator.ofFloat(policyLayout, "translationX", 0F, 20F, -20F, 0F)
        animator.apply {
            duration = 100
            repeatCount = 2
            repeatMode = ValueAnimator.RESTART
            setAutoCancel(true)
        }.start()
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