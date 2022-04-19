package com.example.graduateproj.loginPack.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.graduateproj.R
import com.example.graduateproj.commonUtil.AppNavigator
import com.example.graduateproj.commonUtil.RxClickUtil
import com.example.graduateproj.loginPack.presenter.ForgetPresenter
import com.example.graduateproj.loginPack.util.DialogManager
import com.example.graduateproj.loginPack.util.NumberLegalUtil
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 * Use the [ForgetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ForgetFragment : Fragment() {
    private lateinit var arrowBack: ImageView
    private lateinit var getVerifyCode: TextView
    private lateinit var forgetPresenter: ForgetPresenter
    internal lateinit var accountNumber: EditText
    private lateinit var verifyNumber: EditText
    private lateinit var passwordLinear: LinearLayout
    private lateinit var forgetPassword: TextView

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
        accountNumber = view.findViewById(R.id.account_number)
        verifyNumber = view.findViewById(R.id.verify_number)
        passwordLinear = view.findViewById(R.id.password_linear)
        forgetPassword = view.findViewById(R.id.forget_password)

        forgetPresenter = ForgetPresenter(this)
    }

    private fun initEvents() {

        verifyNumber.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (TextUtils.equals(s.toString(), NumberLegalUtil.getVerifyCode().toString()) && passwordLinear.visibility == View.GONE) {
                    passwordLinear.visibility = View.VISIBLE
                    forgetPassword.text = forgetPresenter.info
                }
            }
        })

        activity?.let {
            RxClickUtil.clickEvent(arrowBack, it)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    AppNavigator.backToLoginFragment(arrowBack)
                }

            RxClickUtil.clickEvent(getVerifyCode, it)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    context?.let { it ->
                        if(forgetPresenter.checkCanShowVerifyCode(accountNumber.text.toString())) {
                            DialogManager.showVerifyCodeDialog(it)
                        }

                    }
                }
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