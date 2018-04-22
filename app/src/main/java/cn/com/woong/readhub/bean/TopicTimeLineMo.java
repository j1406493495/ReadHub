package cn.com.woong.readhub.bean;

import android.os.Parcel;
import android.os.Parcelable;

import cn.com.woong.readhub.base.BaseMo;

/**
 * Created by wong on 2018/4/22.
 */

public class TopicTimeLineMo extends BaseMo implements Parcelable {
    public String id;
    public String title;
    public String createdAt;

    public TopicTimeLineMo() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.createdAt);
    }

    protected TopicTimeLineMo(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.createdAt = in.readString();
    }

    public static final Creator<TopicTimeLineMo> CREATOR = new Creator<TopicTimeLineMo>() {
        public TopicTimeLineMo createFromParcel(Parcel source) {
            return new TopicTimeLineMo(source);
        }

        public TopicTimeLineMo[] newArray(int size) {
            return new TopicTimeLineMo[size];
        }
    };
}
