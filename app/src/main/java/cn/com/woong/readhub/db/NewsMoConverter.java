package cn.com.woong.readhub.db;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.com.woong.readhub.bean.NewsMo;

/**
 * @author wong
 * Created by wong on 2018/5/5.
 */

public class NewsMoConverter implements PropertyConverter<ArrayList<NewsMo>, String> {
    @Override
    public ArrayList<NewsMo> convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }

        ArrayList<NewsMo> newsMos = new ArrayList<>();
        newsMos.clear();

        List<String> newsMoList = Arrays.asList(databaseValue.split("##"));
        for (String newsMoString : newsMoList) {
            List<String> newsMoInfoList = Arrays.asList(newsMoString.split("&&"));

            if (newsMoInfoList.size() >= 9) {
                NewsMo newsMo = new NewsMo();
                newsMo.id = Long.parseLong(newsMoInfoList.get(0));
                newsMo.title = newsMoInfoList.get(1);
                newsMo.summary = newsMoInfoList.get(2);
                newsMo.summaryAuto = newsMoInfoList.get(3);
                newsMo.siteName = newsMoInfoList.get(4);
                newsMo.authorName = newsMoInfoList.get(5);
                newsMo.mobileUrl = newsMoInfoList.get(6);
                newsMo.publishDate = newsMoInfoList.get(7);
                newsMo.language = newsMoInfoList.get(8);

                newsMos.add(newsMo);
            }
        }

        return newsMos;
    }

    @Override
    public String convertToDatabaseValue(ArrayList<NewsMo> entityProperty) {
        if (entityProperty == null) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (NewsMo newsMo : entityProperty) {
            stringBuilder.append(newsMo.id);
            stringBuilder.append("&&");
            stringBuilder.append(newsMo.title);
            stringBuilder.append("&&");
            stringBuilder.append(newsMo.summary);
            stringBuilder.append("&&");
            stringBuilder.append(newsMo.summaryAuto);
            stringBuilder.append("&&");
            stringBuilder.append(newsMo.siteName);
            stringBuilder.append("&&");
            stringBuilder.append(newsMo.authorName);
            stringBuilder.append("&&");
            stringBuilder.append(newsMo.mobileUrl);
            stringBuilder.append("&&");
            stringBuilder.append(newsMo.publishDate);
            stringBuilder.append("&&");
            stringBuilder.append(newsMo.language);
            stringBuilder.append("##");
        }

        return stringBuilder.toString();
    }
}
