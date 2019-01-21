package cn.com.woong.readhub

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler

/**
 * SplashActivity
 * @author wong
 * Created by Woong on 19/01/21.
 */
class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if (intent.flags.and(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
//            finish()
//            return
//        }

//        Handler().postDelayed({
//            finish()
//        }, 2000)
    }
}