package cn.com.woong.readhub.ui.mine

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.woong.readhub.R
import cn.com.woong.readhub.R.layout.fragment_mine
import cn.com.woong.readhub.ui.widget.ArrowItemView
import kotlinx.android.synthetic.main.fragment_mine.*


class MineFragment : Fragment() {
//    val arrowAbout: ArrowItemView? = null
//    val arrowReadLater: ArrowItemView? = null
//    val arrowSetting: ArrowItemView? = null
//    val arrowCheckUpdate: ArrowItemView? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_mine, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arrow_about.setOnClickListener {  }
    }
//    @OnClick(R.id.arrow_about, R.id.arrow_read_later, R.id.arrow_setting, R.id.arrow_check_update)
//    fun onClick(view: View) {
//        when (view.getId()) {
//            R.id.arrow_about -> AboutActivity.startAboutActivity(getActivity())
//            R.id.arrow_read_later -> ReadLaterActivity.startReadLaterActivity(getActivity())
//            R.id.arrow_setting -> SettingActivity.startSettingActivity(getActivity())
//            R.id.arrow_check_update -> Beta.checkUpgrade()
//            else -> {
//            }
//        }
//    }
}
