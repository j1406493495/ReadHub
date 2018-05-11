package cn.com.woong.readhub.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Environment;
import android.widget.ScrollView;
import android.widget.Toast;

import com.blankj.utilcode.util.TimeUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.com.woong.readhub.R;

/**
 * 截图操作
 * Created by wong on 2018/5/10.
 */

public class ScreenShotUtil {
    private final static String FILE_SAVE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static int height = 0;

    public static void getBitmapByView(Context mContext, final ScrollView scrollView) {
        height = 0;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            height += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundResource(android.R.color.white);
        }

        Bitmap bitmap = Bitmap.createBitmap(scrollView.getWidth(), height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);

        Bitmap head = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_share_header);
        Bitmap foot = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_share_footer);
        Bitmap v = toConformBitmap(head, bitmap, foot);

        File saveDir = new File(FILE_SAVE_PATH);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        String pathFile = FILE_SAVE_PATH + "/" + TimeUtils.getNowString() + ".png";
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(pathFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(mContext, R.string.save_pic_failed, Toast.LENGTH_SHORT).show();
        }

        try {
            if (null != out) {
                v.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
                Toast.makeText(mContext, R.string.save_pic_success, Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Toast.makeText(mContext, R.string.save_pic_failed, Toast.LENGTH_SHORT).show();
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
    public static Bitmap toConformBitmap(Bitmap headBitmap, Bitmap infoBitmap, Bitmap footBitmap) {
        if (headBitmap == null) {
            return null;
        }

        int headWidth = headBitmap.getWidth();
        int infoBitmapWidth = infoBitmap.getWidth();
        int footWidth = footBitmap.getWidth();

        int headHeight = headBitmap.getHeight();
        int infoBitmapHeight = infoBitmap.getHeight();
        int footHeight = footBitmap.getHeight();

        Bitmap newBitmap = Bitmap.createBitmap(infoBitmapWidth, headHeight + infoBitmapHeight + footHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);

        // 在 0，0坐标开始画入headBitmap
        canvas.drawBitmap(headBitmap, 0, 0, null);
        //因为手机不同图片的大小的可能小了 就绘制白色的界面填充剩下的界面
        if (headWidth < infoBitmapWidth) {
            Bitmap fillBitmap = Bitmap.createBitmap(infoBitmapWidth - headWidth, headHeight, Bitmap.Config.ARGB_8888);
            Canvas headCanvas = new Canvas(fillBitmap);
            headCanvas.drawColor(Color.WHITE);
            canvas.drawBitmap(fillBitmap, headWidth, 0, null);
        }

        // 在 0，headHeight坐标开始填充infoBitmap
        canvas.drawBitmap(infoBitmap, 0, headHeight, null);

        // 在 0，headHeight + infoBitmapHeight坐标开始填充infoBitmap
        canvas.drawBitmap(footBitmap, 0, headHeight + infoBitmapHeight, null);
        //因为手机不同图片的大小的可能小了 就绘制白色的界面填充剩下的界面
        if (footWidth < infoBitmapWidth) {
            Bitmap fillBitmap = Bitmap.createBitmap(infoBitmapWidth - footWidth, footHeight, Bitmap.Config.ARGB_8888);
            Canvas footCanvas = new Canvas(fillBitmap);
            footCanvas.drawColor(Color.WHITE);
            canvas.drawBitmap(fillBitmap, footWidth, headHeight + infoBitmapHeight, null);
        }

        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();// 存储

        headBitmap.recycle();
        infoBitmap.recycle();
        footBitmap.recycle();

        return newBitmap;
    }
}
