package cn.com.woong.readhub.db.converter;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;

import cn.com.woong.readhub.bean.TimeLineMo;
import cn.com.woong.readhub.bean.TopicTimeLineMo;

/**
 * @author wong
 * Created by wong on 2018/5/5.
 */

public class TimeLineMoConverter implements PropertyConverter<TimeLineMo, String> {
    @Override
    public TimeLineMo convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        return null;
    }

    @Override
    public String convertToDatabaseValue(TimeLineMo entityProperty) {
        if (entityProperty == null) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();

        int count = 0;
        if (entityProperty.topics != null && entityProperty.topics.size() > 0) {
            count = entityProperty.topics.size();
            stringBuilder.append(count);
            stringBuilder.append("##");

            for (TopicTimeLineMo topicTimeLineMo : entityProperty.topics) {
                stringBuilder.append(topicTimeLineMo.id);
                stringBuilder.append("&&");
                stringBuilder.append(topicTimeLineMo.createdAt);
                stringBuilder.append("&&");
                stringBuilder.append(topicTimeLineMo.title);
                stringBuilder.append("&&");
            }
        } else {
            stringBuilder.append(count);
        }
        stringBuilder.append("##");

        return stringBuilder.toString();
    }
}
