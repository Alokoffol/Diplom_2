## Структура проекта

```
src/
├── main/
│   ├── java/org/example/Main.java
│   └── resources/simplelogger.properties
└── test/
    └── java/stellarburgers/
        ├── api/
        │   ├── client/
        │   │   ├── OrderClient.java
        │   │   └── UserClient.java
        │   ├── endpoints/
        │   │   └── Endpoints.java
        │   └── models/
        │       ├── ingredient/
        │       ├── LoginResponse.java
        │       ├── Order.java
        │       ├── OrderResponse.java
        │       └── User.java
        ├── steps/
        │   ├── OrderSteps.java
        │   └── UserSteps.java
        ├── tests/
        │   ├── CreateOrderTest.java
        │   ├── CreateUserTest.java
        │   └── LoginUserTest.java
        └── utils/
            └── DataGenerator.java
```


## Технологии

- Java 11
- JUnit 4
- REST Assured
- Jackson
- Allure Framework
- Maven


## Запуск всех тестов
mvn test


## Запуск с генерацией Allure отчета
mvn test allure:serve

# Создание пользователя:
- создать уникального пользователя;
- создать пользователя, который уже зарегистрирован;
- создать пользователя и не заполнить одно из обязательных полей.
# Логин пользователя:
- вход под существующим пользователем;
- вход с неверным логином и паролем.
# Создание заказа:
- с авторизацией;
- без авторизации;
- с ингредиентами;
- без ингредиентов;
- с неверным хешем ингредиентов.


# Настройки
Файл simplelogger.properties настраивает логирование для SLF4J.

API Endpoints

    BASE: https://stellarburgers.nomoreparties.site

    Регистрация: /api/auth/register

    Логин: /api/auth/login

    Заказы: /api/orders

    Ингредиенты: /api/ingredients