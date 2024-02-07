# T1 Academy: Part one
----

## Web-service психологической поддержки

1. Заходить простой HttpServlet который будет обрабатывать GET и POST запрос;
2. POST запрос /help-service/v1/support который возвращает рандомную фразу приободрения. Например, «У тебя все получится»;
3. POST запрос /help-service/v1/support добавляет в список фраз новую;
4. Использовать text/plain как content type;
5. Собрать war архив;
6. Поднять TomCat с war архивом используя docker-compose;
7. Создать репозитория на GitHub и залить готовое решение;

----
### Стек:
1. Java 17;
2. Jakarta EE;
3. Maven;
4. Lombok;
5. Mockito;
6. Docker.

----
### Запуск приложения:
1. `mvn clean package`
2. `docker-compose up`