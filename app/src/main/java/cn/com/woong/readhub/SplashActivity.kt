package cn.com.woong.readhub

/**
 * Created by wong on 2018/12/29.
 */
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler

/**
 * SplashActivity
 * @author wong
 * Created by woong on 18/04/24.
 */
class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.flags.and(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish()
            return
        }

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000)
    }
}