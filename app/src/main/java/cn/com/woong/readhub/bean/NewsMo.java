package cn.com.woong.readhub.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;


@Entity
public class NewsMo extends BaseMo implements Parcelable {
    @Id
    public long id;
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
        dest.writeLong(this.id);
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

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummaryAuto() {
        return this.summaryAuto;
    }

    public void setSummaryAuto(String summaryAuto) {
        this.summaryAuto = summaryAuto;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMobileUrl() {
        return this.mobileUrl;
    }

    public void setMobileUrl(String mobileUrl) {
        this.mobileUrl = mobileUrl;
    }

    public String getSiteName() {
        return this.siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublishDate() {
        return this.publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public NewsMo() {
    }

    protected NewsMo(Parcel in) {
        this.id = in.readLong();
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

    @Generated(hash = 349755279)
    public NewsMo(long id, String title, String summary, String summaryAuto,
            String url, String mobileUrl, String siteName, String language,
            String authorName, String publishDate) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.summaryAuto = summaryAuto;
        this.url = url;
        this.mobileUrl = mobileUrl;
        this.siteName = siteName;
        this.language = language;
        this.authorName = authorName;
        this.publishDate = publishDate;
    }

    public static final Parcelable.Creator<NewsMo> CREATOR = new Creator<NewsMo>() {
        @Override
        public NewsMo createFromParcel(Parcel source) {
            return new NewsMo(source);
        }

        @Override
        public NewsMo[] newArray(int size) {
            return new NewsMo[size];
        }
    };
}
