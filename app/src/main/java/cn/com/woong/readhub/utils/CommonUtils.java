package cn.com.woong.readhub.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import cn.com.woong.readhub.bean.NewsDetailMo;
import cn.com.woong.readhub.bean.NewsMo;

/**
 * Created by wong on 2018/4/15.
 */

public class CommonUtils {

    public static Long getTimeStampByReadhubDateString(String date){
        if (date == null) {
            return 0L;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.CHINA);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return format.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static NewsMo changeFromNewsDetailMo(NewsDetailMo newsDetailMo) {
        NewsMo newsMo = new NewsMo();
        newsMo.title = newsDetailMo.title;
        newsMo.summary = newsDetailMo.summary;
        newsMo.summaryAuto = newsDetailMo.summaryAuto;
        newsMo.url = newsDetailMo.url;
        newsMo.mobileUrl = newsDetailMo.mobileUrl;
        newsMo.siteName = newsDetailMo.siteName;
        newsMo.language = newsDetailMo.language;
        newsMo.authorName = newsDetailMo.authorName;
        newsMo.publishDate = newsDetailMo.publishDate;

        return newsMo;
    }
}

