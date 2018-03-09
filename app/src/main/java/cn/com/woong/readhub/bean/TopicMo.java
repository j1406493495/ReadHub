package cn.com.woong.readhub.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import cn.com.woong.readhub.base.BaseMo;

/**
 * Created by wong on 2018/3/9.
 */

public class TopicMo extends BaseMo implements Parcelable {
    public long id;
    public String createAt;
    public String eventData;
    public ArrayList<NewsMo> newsArray;
    public long order;
    public String publishDate;
    public String summary;
    public String title;
    public String updateAt;
    public String timeline;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.createAt);
        dest.writeString(this.eventData);
        dest.writeTypedList(newsArray);
        dest.writeLong(this.order);
        dest.writeString(this.publishDate);
        dest.writeString(this.summary);
        dest.writeString(this.title);
        dest.writeString(this.updateAt);
        dest.writeString(this.timeline);
    }

    public TopicMo() {
    }

    protected TopicMo(Parcel in) {
        this.id = in.readLong();
        this.createAt = in.readString();
        this.eventData = in.readString();
        this.newsArray = in.createTypedArrayList(NewsMo.CREATOR);
        this.order = in.readLong();
        this.publishDate = in.readString();
        this.summary = in.readString();
        this.title = in.readString();
        this.updateAt = in.readString();
        this.timeline = in.readString();
    }

    public static final Parcelable.Creator<TopicMo> CREATOR = new Parcelable.Creator<TopicMo>() {
        public TopicMo createFromParcel(Parcel source) {
            return new TopicMo(source);
        }

        public TopicMo[] newArray(int size) {
            return new TopicMo[size];
        }
    };
}
