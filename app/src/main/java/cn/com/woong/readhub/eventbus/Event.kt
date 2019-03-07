package cn.com.woong.readhub.eventbus

class Event {
    class ReadLaterTopicRemoveEvent {
        var position: Int = 0
    }

    class ReadLaterNewsRemoveEvent {
        var position: Int = 0
    }
}