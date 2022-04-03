package com.example.graduateproj.mainPack.mePack

import android.animation.ObjectAnimator
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.graduateproj.R
import com.example.graduateproj.commonUtil.AppNavigator
import com.example.graduateproj.commonUtil.RxClickUtil
import com.example.graduateproj.databinding.FragmentMeBinding
import com.example.graduateproj.loginPack.util.DialogManager
import com.example.graduateproj.mainPack.mePack.presenter.MePresenter
import com.example.graduateproj.mainPack.mePack.util.DetailStateUtil
import de.hdodenhof.circleimageview.CircleImageView
import java.util.concurrent.TimeUnit


class MeFragment : Fragment() {

    private var _binding: FragmentMeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var editLinear: LinearLayout
    private lateinit var notificationIcon: ImageView
    private lateinit var relativeLayoutPrefer: RelativeLayout
    private lateinit var relativeLayoutSuggest: RelativeLayout
    private lateinit var relativeLayoutVersion: RelativeLayout
    private lateinit var relativeLayoutOther: RelativeLayout
    private lateinit var relativeLayoutVerInfo: RelativeLayout
    private lateinit var rotateArrow: ImageView
    private lateinit var meName: TextView
    private lateinit var meSign: TextView
    internal lateinit var circleImageHead: CircleImageView

    private lateinit var mePresenter: MePresenter

    private fun initViews(root: View) {
        editLinear = root.findViewById(R.id.me_fragment_info_edit_layout)
        notificationIcon = root.findViewById(R.id.me_fragment_notification_icon)
        relativeLayoutPrefer = root.findViewById(R.id.me_fragment_prefer_settings)
        relativeLayoutSuggest = root.findViewById(R.id.me_fragment_suggest)
        relativeLayoutVersion = root.findViewById(R.id.me_fragment_version)
        relativeLayoutOther = root.findViewById(R.id.me_fragment_other)
        relativeLayoutVerInfo = root.findViewById(R.id.relative_version_info)
        rotateArrow = root.findViewById(R.id.rotate_arrow)
        circleImageHead = root.findViewById(R.id.me_fragment_head)

        meName = root.findViewById(R.id.me_fragment_name)
        meSign = root.findViewById(R.id.me_fragment_sign)

        mePresenter = MePresenter(this)
    }

    private fun initEvents() {
        activity?.let {
            RxClickUtil.clickEvent(editLinear, it)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    AppNavigator.openEditDetailActivity(requireContext())
                }

            RxClickUtil.clickEvent(notificationIcon, it)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    DialogManager.showNotificationDialog(requireContext(), false)
                }

            RxClickUtil.clickEvent(relativeLayoutPrefer, it)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    AppNavigator.openPreferActivity(requireContext())
                }

            RxClickUtil.clickEvent(relativeLayoutSuggest, it)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    AppNavigator.openAppMarket(requireContext())
                }

            RxClickUtil.clickEvent(relativeLayoutVersion, it)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    if (relativeLayoutVerInfo.visibility == View.GONE) {
                        relativeLayoutVerInfo.visibility = View.VISIBLE
                        rotateNinetyAngle()
                    } else {
                        relativeLayoutVerInfo.visibility = View.GONE
                        rotateNinetyAngleBack()
                    }
                }

            RxClickUtil.clickEvent(circleImageHead, it)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    mePresenter.requestPermissionAndTryOpen()
                }

            RxClickUtil.clickEvent(relativeLayoutOther, it)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    AppNavigator.openOtherActivity(requireContext())
                }
        }

    }

    private fun initPersonalInfo() {
        val name = DetailStateUtil.getInstance(requireContext()).localSelfNameOrDefault
        if(!TextUtils.equals(name, "")) {
            meName.text = name
        }

        val sign = DetailStateUtil.getInstance(requireContext()).localSignOrDefault
        if(!TextUtils.equals(sign, "")) {
            meSign.text = sign
        }
    }

    private fun rotateNinetyAngle() {
        val rotate = ObjectAnimator.ofFloat(rotateArrow, "rotation", 0F, 90F).apply {
            duration = 200
        }.start()
    }

    private fun rotateNinetyAngleBack() {
        val rotate = ObjectAnimator.ofFloat(rotateArrow, "rotation", 90F, 0F).apply {
            duration = 200
        }.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val meViewModel =
            ViewModelProvider(this).get(MeViewModel::class.java)

        _binding = FragmentMeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initViews(root)
        initEvents()

        return root

//        return ComposeView(requireContext()).apply {
//            // Dispose the Composition when viewLifecycleOwner is destroyed
//            setViewCompositionStrategy(
//                ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
//            )
//
//            setContent {
//                MaterialTheme {
//                    Column {
//                        TopTitle()
//                        RecordToday()
//                        OtherSettings()
//                    }
//                }
//            }
//        }
    }

    override fun onResume() {
        super.onResume()
        initPersonalInfo()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


@Composable
fun TopTitle() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 28.dp, top = 80.dp, end = 28.dp, bottom = 16.dp),
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.ic_launcher_background
            ),
            contentDescription = "头像",
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(size = 64.dp)
        )

        Column(
            modifier = Modifier
                .padding(start = 14.dp)
                .weight(weight = 1F)

        ) {
            Text(
                text = "student",
                fontSize = 19.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(text = "说说最近的生活状态吧", fontSize = 9.sp, color = Color.Gray)
        }

        Surface(modifier = Modifier.clip(shape = CircleShape), color = Color.Black) {
            Image(
                painter = painterResource(id = R.drawable.me_fragment_notification_icon),
                contentDescription = "通知铃铛",
                modifier = Modifier
                    .padding(all = 10.dp)
                    .size(18.dp)
            )
        }
    }
}

@Composable
fun RecordToday() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(28.dp, 15.dp, 28.dp, 15.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = Color.Black
        ),
        content = {
            Column {
                Calendar()
                TodayDynamic()
            }
        }
    )
}

@Composable
fun Calendar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 15.dp, end = 10.dp, bottom = 10.dp)
    ) {
        Text(text = "今日动态", fontSize = 13.sp, color = Color.Black)
        Spacer(modifier = Modifier.weight(1F))
        Text(text = "03.29 | Tue.", fontSize = 13.sp, color = Color.Black)
    }
}

@Composable
fun TodayDynamic() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 5.dp, 10.dp, 20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        DynamicItem(dynamic = 0, title = "动态")
        DynamicItem(dynamic = 0, title = "评论")
        DynamicItem(dynamic = 0, title = "获赞")
    }
}

@Composable
fun DynamicItem(dynamic: Int, title: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = dynamic.toString(), fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = title, fontSize = 14.sp)
    }
}


/*
 设置选项
 */
@Composable
fun OtherSettings() {
    Column(modifier = Modifier.fillMaxHeight()) {
        NavItem(title = "偏好设置")
        NavItem(title = "鼓励建议")
        NavItem(title = "版本信息")
        NavItem(title = "其他")
    }
}

@Composable
fun ColumnScope.NavItem(title: String) {
    Row(
        modifier = Modifier
            .padding(28.dp, 25.dp, 28.dp, 2.dp)
            .fillMaxWidth()
            .clickable(onClick = {}, indication = null, interactionSource = remember {
                MutableInteractionSource()
            })
    ) {
        Text(text = title, fontSize = 13.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1F))
        Icon(
            painter = painterResource(id = R.drawable.me_fragment_arrow_next_icon),
            contentDescription = "跳转更多"
        )
    }
}
