package ru.t1academy.context;

public interface ApplicationContext {
    Object getBean(String beanName);
    void registerBean(String beanName, Object beanInstance);
    void autowireBeans();
}
