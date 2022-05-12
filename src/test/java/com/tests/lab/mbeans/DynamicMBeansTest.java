package com.tests.lab.mbeans;

import org.junit.jupiter.api.Test;

import javax.management.*;
import java.lang.management.ManagementFactory;

//@Disabled
public class DynamicMBeansTest {

    public static class ApplicationInfo implements DynamicMBean {


        @Override
        public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
            return null;
        }

        @Override
        public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {

        }

        @Override
        public AttributeList getAttributes(String[] attributes) {
            return null;
        }

        @Override
        public AttributeList setAttributes(AttributeList attributes) {
            return null;
        }

        @Override
        public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
            return null;
        }

        @Override
        public MBeanInfo getMBeanInfo() {
            return null;
        }
    }

    @Test
    void simpleBeanTest() {
        try {
            ObjectName objectName = new ObjectName("com.tests.lab.mbeans:type=basic,name=console_bean");
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            server.registerMBean(new ApplicationInfo(), objectName);

            Integer data = 0;
            while (true) {
                var attribute = (Integer) server.getAttribute(objectName, "SomeValue");
                if (!attribute.equals(data)) {
                    data = attribute;
                    System.out.println(data);
                }
            }
        } catch (MalformedObjectNameException | InstanceAlreadyExistsException | NotCompliantMBeanException | MBeanException | AttributeNotFoundException | InstanceNotFoundException | ReflectionException e) {
            // handle exceptions
            System.out.println(e);
        }
    }
}
