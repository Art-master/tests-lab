package com.tests.lab.mbeans;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class SimpleMBeanAndNotificationTest {

    public interface ApplicationInfoMBean {

        String getMyName();

        void setSomeValue(int value1);

        int getSomeValue();

        void writeToConsole(String message);

        String concat(String str1, String str2);
    }

    public static class ApplicationInfo extends NotificationBroadcasterSupport implements ApplicationInfoMBean {

        private int someValue;

        private long sequenceNumber = 1;

        @Override
        public String getMyName() {
            return "JustMBeanName" + someValue;
        }

        @Override
        public void setSomeValue(int value1) {
            int old = someValue;
            Notification n = new AttributeChangeNotification(this, sequenceNumber++, System.currentTimeMillis(),
                    "Value changed", "CacheSize", "int",
                    old, this.someValue);

            sendNotification(n);
            this.someValue = value1;
        }

        @Override
        public int getSomeValue() {
            return this.someValue;
        }

        @Override
        public void writeToConsole(String message) {
            System.out.println(message);
        }

        @Override
        public String concat(String str1, String str2) {
            return str1 + str2;
        }

        @Override
        public MBeanNotificationInfo[] getNotificationInfo() {
            String[] types = new String[]{AttributeChangeNotification.ATTRIBUTE_CHANGE};

            String name = AttributeChangeNotification.class.getName();
            String description = "An attribute of this MBean has changed";
            MBeanNotificationInfo info = new MBeanNotificationInfo(types, name, description);
            return new MBeanNotificationInfo[]{info};
        }
    }

    @SneakyThrows
    @Test
    void simpleBeanTest() {
        ObjectName objectName = new ObjectName("com.tests.lab.mbeans:type=basic,name=console_bean");
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ApplicationInfo info = new ApplicationInfo();
        info.addNotificationListener((e, r) -> System.out.println(e.getMessage()), null, null);

        server.registerMBean(info, objectName);

        Integer data = 0;
        while (true) {
            var attribute = (Integer) server.getAttribute(objectName, "SomeValue");
            if (!attribute.equals(data)) {
                data = attribute;
                System.out.println(data);
            }
        }
    }
}
