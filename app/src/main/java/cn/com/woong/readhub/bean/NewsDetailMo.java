package cn.com.woong.readhub.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by wong on 2018/3/9.
 */

public class NewsDetailMo extends BaseMo implements Parcelable {
    public String id;
    public String title;
    public String summary;
    public String summaryAuto;
    public String url;
    public String mobileUrl;
    public String siteName;
    public String language;
    public String authorName;
    public String publishDate;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.summary);
        dest.writeString(this.summaryAuto);
        dest.writeString(this.url);
        dest.writeString(this.mobileUrl);
        dest.writeString(this.siteName);
        dest.writeString(this.language);
        dest.writeString(this.authorName);
        dest.writeString(this.publishDate);
    }

    public NewsDetailMo() {
    }

    protected NewsDetailMo(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.summary = in.readString();
        this.summaryAuto = in.readString();
        this.url = in.readString();
        this.mobileUrl = in.readString();
        this.siteName = in.readString();
        this.language = in.readString();
        this.authorName = in.readString();
        this.publishDate = in.readString();
    }

    public static final Creator<NewsDetailMo> CREATOR = new Creator<NewsDetailMo>() {
        public NewsDetailMo createFromParcel(Parcel source) {
            return new NewsDetailMo(source);
        }

        public NewsDetailMo[] newArray(int size) {
            return new NewsDetailMo[size];
        }
    };
}
