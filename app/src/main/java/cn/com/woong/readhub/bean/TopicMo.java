package cn.com.woong.readhub.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import cn.com.woong.readhub.base.BaseMo;

/**
 * Created by wong on 2018/3/9.
 */

public class TopicMo extends BaseMo implements Parcelable {
    public String id;
    public String createAt;
    public ArrayList<NewsMo> newsArray;
    public long order;
    public String publishDate;
    public String summary;
    public String title;
    public String updateAt;
    public String timeline;

    public TopicMo() {
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
        dest.writeString(this.timeline);
    }

    protected TopicMo(Parcel in) {
        this.id = in.readString();
        this.createAt = in.readString();
        this.newsArray = in.createTypedArrayList(NewsMo.CREATOR);
        this.order = in.readLong();
        this.publishDate = in.readString();
        this.summary = in.readString();
        this.title = in.readString();
        this.updateAt = in.readString();
        this.timeline = in.readString();
    }

    public static final Creator<TopicMo> CREATOR = new Creator<TopicMo>() {
        public TopicMo createFromParcel(Parcel source) {
            return new TopicMo(source);
        }

        public TopicMo[] newArray(int size) {
            return new TopicMo[size];
        }
    };
}
