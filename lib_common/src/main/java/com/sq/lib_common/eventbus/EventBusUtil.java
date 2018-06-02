package com.sq.lib_common.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * @author javakam
 */
public class EventBusUtil {

    public static void register(Object subscriber) {
        //加上判断
        if (!EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber);
        }
    }

    public static void unregister(Object subscriber) {
        if (EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().unregister(subscriber);
        }
    }

    public static void sendEvent(Object event) {
        EventBus.getDefault().post(event);
    }

    public static void sendStickyEvent(Object event) {
        EventBus.getDefault().postSticky(event);
    }
}