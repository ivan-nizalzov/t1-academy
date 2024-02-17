package ru.t1academy.context;

public interface ApplicationContext {
    <T> T getBean(Class<T> beanClass);

}
