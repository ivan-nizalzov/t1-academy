package ru.t1academy.bpp;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.t1academy.messageBroker.annotation.Subscriber;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

@Component
@RequiredArgsConstructor
public class SubscriberBeanPostProcessor implements BeanPostProcessor {
    private final Map<String, Object> beanMap = new HashMap<>();
    private final Long SLEEP_MILLIS = 10000L;
    private final ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Optional<Method> subscriberMethod = Arrays.stream(bean.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Subscriber.class))
                .findAny();

        if (subscriberMethod.isPresent()) {
            beanMap.put(beanName, bean);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Object target = beanMap.get(beanName);
        if (target != null) {

            Arrays.stream(target.getClass().getMethods())
                    .filter(method -> method.isAnnotationPresent(Subscriber.class))
                    .forEach(method -> {

                        final ArrayList<Object> params = new ArrayList<>();
                        for (Parameter parameter : method.getParameters()) {
                            params.add(applicationContext.getBean(parameter.getType()));
                        }
                        Runnable runnable = () -> {
                            while (true) {
                                try {
                                    Object invoke = method.invoke(target, params.toArray());
                                    if (invoke == null) {
                                        Thread.sleep(SLEEP_MILLIS);
                                    }
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        };
                        new Thread(runnable).start();

                    });

        }

        return bean;
    }

}
