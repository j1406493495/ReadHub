package cn.com.woong.readhub.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.ArrayList;

import cn.com.woong.readhub.db.converter.NewsMoConverter;
import cn.com.woong.readhub.db.converter.TimeLineMoConverter;

/**
 * Created by wong on 2018/3/9.
 */

public class TopicDetailMo extends BaseMo implements Parcelable {
    public String id;
    public String createAt;
    public ArrayList<NewsMo> newsArray;
    public long order;
    public String publishDate;
    public String summary;
    public String title;
    public String updateAt;
    public TimeLineMo timeline;

    public ArrayList<NewsMo> getNewsArray() {
        return this.newsArray;
    }

    public void setNewsArray(ArrayList<NewsMo> newsArray) {
        this.newsArray = newsArray;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.createAt);
        dest.writeTypedList(newsArray);
        dest.writeLong(this.order);
        dest.writeString(this.publishDate);
        dest.writeString(this.summary);
        dest.writeString(this.title);
        dest.writeString(this.updateAt);
        dest.writeParcelable(this.timeline, 0);
    }

    public TopicDetailMo() {
    }

    protected TopicDetailMo(Parcel in) {
        this.id = in.readString();
        this.createAt = in.readString();
        this.newsArray = in.createTypedArrayList(NewsMo.CREATOR);
        this.order = in.readLong();
        this.publishDate = in.readString();
        this.summary = in.readString();
        this.title = in.readString();
        this.updateAt = in.readString();
        this.timeline = in.readParcelable(TimeLineMo.class.getClassLoader());
    }

    public static final Creator<TopicDetailMo> CREATOR = new Creator<TopicDetailMo>() {
        public TopicDetailMo createFromParcel(Parcel source) {
            return new TopicDetailMo(source);
        }

        public TopicDetailMo[] newArray(int size) {
            return new TopicDetailMo[size];
        }
    };
}
