package cn.com.woong.readhub.ui.mine

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.woong.readhub.R
import kotlinx.android.synthetic.main.fragment_mine.*


class MineFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_mine, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arrow_about.setOnClickListener {
            AboutActivity.startAboutActivity(activity as Context)
        }

        arrow_read_later.setOnClickListener {
            ReadLaterActivity.startReadLaterActivity(activity as Context)
        }

        arrow_setting.setOnClickListener {
            SettingActivity.startSettingActivity(activity as Context)
        }
    }
}
