package cn.com.woong.readhub.eventbus;

/**
 * Created by wong on 2018/5/9.
 */

public class Event {
    public static class ReadLaterTopicRemoveEvent {
        public int position;
    }

    public static class ReadLaterNewsRemoveEvent {
        public int position;
    }
}
