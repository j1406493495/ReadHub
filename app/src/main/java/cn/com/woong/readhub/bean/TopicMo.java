package cn.com.woong.readhub.bean;

import android.os.Parcel;
import android.os.Parcelable;
import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import java.util.ArrayList;
import cn.com.woong.readhub.db.NewsMoConverter;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class TopicMo extends BaseMo implements Parcelable {

    @Id
    public String id;
    public String createAt;
    @Convert(columnType = String.class, converter = NewsMoConverter.class)
    public ArrayList< NewsMo > newsArray;
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
        dest.writeString(this.id);
        dest.writeString(this.createAt);
        dest.writeTypedList(this.newsArray);
        dest.writeLong(this.order);
        dest.writeString(this.publishDate);
        dest.writeString(this.summary);
        dest.writeString(this.title);
        dest.writeString(this.updateAt);
        dest.writeString(this.timeline);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public ArrayList<NewsMo> getNewsArray() {
        return this.newsArray;
    }

    public void setNewsArray(ArrayList<NewsMo> newsArray) {
        this.newsArray = newsArray;
    }

    public long getOrder() {
        return this.order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public String getPublishDate() {
        return this.publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdateAt() {
        return this.updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getTimeline() {
        return this.timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    public TopicMo() {
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

    @Generated(hash = 780102330)
    public TopicMo(String id, String createAt, ArrayList<NewsMo> newsArray,
            long order, String publishDate, String summary, String title,
            String updateAt, String timeline) {
        this.id = id;
        this.createAt = createAt;
        this.newsArray = newsArray;
        this.order = order;
        this.publishDate = publishDate;
        this.summary = summary;
        this.title = title;
        this.updateAt = updateAt;
        this.timeline = timeline;
    }

    public static final Creator<TopicMo> CREATOR = new Creator<TopicMo>() {
        @Override
        public TopicMo createFromParcel(Parcel source) {
            return new TopicMo(source);
        }

        @Override
        public TopicMo[] newArray(int size) {
            return new TopicMo[size];
        }
    };
}