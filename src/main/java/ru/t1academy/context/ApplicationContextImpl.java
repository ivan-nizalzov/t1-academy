package ru.t1academy.context;

import ru.t1academy.context.annotation.factory.Autowired;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContextImpl implements ApplicationContext {
    private final Map<String, Object> beanMap;

    public ApplicationContextImpl() {
        beanMap = new ConcurrentHashMap<>();
    }

    @Override
    public void registerBean(String beanName, Object beanInstance) {
        beanMap.put(beanName, beanInstance);
    }

    @Override
    public Object getBean(String beanName) {
        return beanMap.get(beanName);
    }

    @Override
    public void autowireBeans() {
        for (Object bean : beanMap.values()) {
            autowireBean(bean);
        }
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        for (Object bean : beanMap.values()) {
            if (beanClass.isAssignableFrom(bean.getClass())) {
                return beanClass.cast(bean);
            }
        }
        return null;
    }

    private void autowireBean(Object bean) {
        /*Class<?> clazz = bean.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Object dependency = getBean(field.getType());
                field.setAccessible(true);
                try {
                    field.set(bean, dependency);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }*/

        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Object dependency = beanMap.get(field.getName());
                try {
                    field.setAccessible(true);
                    field.set(bean, dependency);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

   /* private Object wrapWithLogging(Object object) {
        return Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                new LoggingInvocationHandler(object)
        );
    }*/

}
