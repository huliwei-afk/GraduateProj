package com.example.graduateproj.mainPack.mePack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.graduateproj.R
import com.example.graduateproj.commonUI.themeColorGreen
import com.example.graduateproj.commonUI.themeColorPink
import com.example.graduateproj.databinding.FragmentMeBinding

class MeFragment : Fragment() {

    private var _binding: FragmentMeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val meViewModel =
//            ViewModelProvider(this).get(MeViewModel::class.java)
//
//        _binding = FragmentMeBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val textView: TextView = binding.textMe
//        meViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
//        return root

        return ComposeView(requireContext()).apply {
            // Dispose the Composition when viewLifecycleOwner is destroyed
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
            )

            setContent {
                MaterialTheme {
                    Column {
                        TopTitle()
                        RecordToday()
                        OtherSettings()
                    }
                }
            }
        }
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
                .weight(weight = 1f),

            ) {
            Text(
                text = "student",
                fontSize = 19.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(text = "说说最近的生活状态吧", fontSize = 9.sp, color = Color.Gray)
        }

        Surface(modifier = Modifier.clip(shape = CircleShape), color = themeColorPink) {
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

@Preview
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
            color = themeColorGreen
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
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = dynamic.toString(), fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = title, fontSize = 14.sp)
    }
}


/*a
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
