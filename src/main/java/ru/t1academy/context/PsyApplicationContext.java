package ru.t1academy.context;

import ru.t1academy.context.annotation.factory.Autowired;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PsyApplicationContext implements ApplicationContext {
    private final Map<String, Object> beans; // Контейнер бинов

    public PsyApplicationContext() {
        beans = new ConcurrentHashMap<>();
    }

    @Override
    public void registerBean(String beanName, Object beanInstance) {
        beans.put(beanName, beanInstance);
    }

    @Override
    public Object getBean(String beanName) {
        return beans.get(beanName);
    }

    @Override
    public void autowireBeans() {
        for (Object bean : beans.values()) {
            autowireBean(bean);
        }
    }

    private <T> T getBean(Class<T> beanClass) {
        for (Object bean : beans.values()) {
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
                Object dependency = beans.get(field.getName());
                try {
                    field.setAccessible(true);
                    field.set(bean, dependency);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
