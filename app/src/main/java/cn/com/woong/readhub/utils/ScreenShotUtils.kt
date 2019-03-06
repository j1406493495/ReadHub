package cn.com.woong.readhub.utils

import android.graphics.Bitmap
import android.widget.Toast
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.os.Environment
import android.widget.ScrollView
import cn.com.woong.readhub.R
import com.blankj.utilcode.util.TimeUtils
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

object ScreenShotUtils {
    private val FILE_SAVE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
    var height = 0

    @JvmStatic
    fun getBitmapByView(mContext: Context, scrollView: ScrollView) {
        height = 0
        for (i in 0 until scrollView.childCount) {
            height += scrollView.getChildAt(i).height
            scrollView.getChildAt(i).setBackgroundResource(android.R.color.white)
        }

        val bitmap = Bitmap.createBitmap(scrollView.width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        scrollView.draw(canvas)

        val head = BitmapFactory.decodeResource(mContext.resources, R.drawable.ic_share_header)
        val foot = BitmapFactory.decodeResource(mContext.resources, R.drawable.ic_share_footer)
        val v = toConformBitmap(head, bitmap, foot)

        val saveDir = File(FILE_SAVE_PATH)
        if (!saveDir.exists()) {
            saveDir.mkdirs()
        }

        val pathFile = FILE_SAVE_PATH + "/" + TimeUtils.getNowString() + ".png"
        var out: FileOutputStream? = null
        try {
            out = FileOutputStream(pathFile)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            Toast.makeText(mContext, R.string.save_pic_success, Toast.LENGTH_SHORT).show()
        }

        try {
            if (null != out) {
                v!!.compress(Bitmap.CompressFormat.PNG, 100, out)
                out!!.flush()
                out!!.close()
                Toast.makeText(mContext, R.string.save_pic_success, Toast.LENGTH_SHORT).show()
            }
        } catch (e: IOException) {
            Toast.makeText(mContext, R.string.save_pic_failed, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 合并图片
     *
     * @param headBitmap
     * @param infoBitmap
     * @param footBitmap
     * @return
     */
    @JvmStatic
    fun toConformBitmap(headBitmap: Bitmap?, infoBitmap: Bitmap, footBitmap: Bitmap): Bitmap? {
        headBitmap?.let {
            val headWidth = headBitmap.width
            val infoBitmapWidth = infoBitmap.width
            val footWidth = footBitmap.width

            val headHeight = headBitmap.height
            val infoBitmapHeight = infoBitmap.height
            val footHeight = footBitmap.height

            val newBitmap = Bitmap.createBitmap(infoBitmapWidth, headHeight + infoBitmapHeight + footHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(newBitmap)

            // 在 0，0坐标开始画入headBitmap
            canvas.drawBitmap(headBitmap, 0f, 0f, null)
            //因为手机不同图片的大小的可能小了 就绘制白色的界面填充剩下的界面
            if (headWidth < infoBitmapWidth) {
                val fillBitmap = Bitmap.createBitmap(infoBitmapWidth - headWidth, headHeight, Bitmap.Config.ARGB_8888)
                val headCanvas = Canvas(fillBitmap)
                headCanvas.drawColor(Color.WHITE)
                canvas.drawBitmap(fillBitmap, headWidth.toFloat(), 0f, null)
            }

            // 在 0，headHeight坐标开始填充infoBitmap
            canvas.drawBitmap(infoBitmap, 0f, headHeight.toFloat(), null)

            // 在 0，headHeight + infoBitmapHeight坐标开始填充infoBitmap
            canvas.drawBitmap(footBitmap, 0f, headHeight.toFloat() + infoBitmapHeight.toFloat(), null)
            //因为手机不同图片的大小的可能小了 就绘制白色的界面填充剩下的界面
            if (footWidth < infoBitmapWidth) {
                val fillBitmap = Bitmap.createBitmap(infoBitmapWidth - footWidth, footHeight, Bitmap.Config.ARGB_8888)
                val footCanvas = Canvas(fillBitmap)
                footCanvas.drawColor(Color.WHITE)
                canvas.drawBitmap(fillBitmap, footWidth.toFloat(), headHeight.toFloat() + infoBitmapHeight.toFloat(), null)
            }

            canvas.save(Canvas.ALL_SAVE_FLAG)
            canvas.restore()// 存储

            headBitmap.recycle()
            infoBitmap.recycle()
            footBitmap.recycle()

            return newBitmap
        } ?: return null
    }
}
