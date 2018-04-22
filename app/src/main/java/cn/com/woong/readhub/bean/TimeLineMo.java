package cn.com.woong.readhub.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import cn.com.woong.readhub.base.BaseMo;
import cn.com.woong.readhub.bean.TopicTimeLineMo;

/**
 * Created by wong on 2018/4/22.
 */

public class TimeLineMo extends BaseMo implements Parcelable {
    public ArrayList<TopicTimeLineMo> topics;
    public String message;
    public int errorCode;
    public ArrayList<String> keywords;

    public TimeLineMo() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(topics);
        dest.writeString(this.message);
        dest.writeInt(this.errorCode);
        dest.writeStringList(this.keywords);
    }

    protected TimeLineMo(Parcel in) {
        this.topics = in.createTypedArrayList(TopicTimeLineMo.CREATOR);
        this.message = in.readString();
        this.errorCode = in.readInt();
        this.keywords = in.createStringArrayList();
    }

    public static final Creator<TimeLineMo> CREATOR = new Creator<TimeLineMo>() {
        public TimeLineMo createFromParcel(Parcel source) {
            return new TimeLineMo(source);
        }

        public TimeLineMo[] newArray(int size) {
            return new TimeLineMo[size];
        }
    };
}
