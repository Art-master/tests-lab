package com.tests.lab.mbeans;

import org.junit.jupiter.api.Test;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationListener;

public class JMXClientTest {

    public static class ClientListener implements NotificationListener {

        public void handleNotification(Notification notification,
                                       Object handback) {
            System.out.println("\nReceived notification:");
            System.out.println("\tClassName: " + notification.getClass().getName());
            System.out.println("\tSource: " + notification.getSource());
            System.out.println("\tType: " + notification.getType());
            System.out.println("\tMessage: " + notification.getMessage());
            if (notification instanceof AttributeChangeNotification acn) {
                System.out.println("\tAttributeName: " + acn.getAttributeName());
                System.out.println("\tAttributeType: " + acn.getAttributeType());
                System.out.println("\tNewValue: " + acn.getNewValue());
                System.out.println("\tOldValue: " + acn.getOldValue());
            }
        }
    }

    @Test
    void test() {
        ClientListener listener = new ClientListener();
    }
}
