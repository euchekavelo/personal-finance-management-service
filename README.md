# Сервис управления личными финансами
## Описание сервиса

Сервис управления личными финансами — это инструмент, который предоставляет возможность пользователям добавлять расходы и доходы, 
просматривать статистику по тратам, устанавливать бюджеты и категории расходов.

---


## Требования к программному обеспечению

Для корректной работы и тестирования сервиса необходимо наличие следующего установленного
и настроенного программного обеспечения:

* Docker;
* Gradle;
* OpenJDK 17+;
* Программа командной строки ***curl*** или ПО Postman для тестирования API;
* Среда разработки IntelliJ IDEA (Community или Ultimate);
* Система контроля версий Git;
* DBeaver или pgAdmin для просмотра сущностей в базе данных.

---


## Запуск необходимой инфраструктуры

1. Первоначально необходимо склонировать репозиторий сервиса с помощью команды:
```bash
git clone https://github.com/euchekavelo/personal-finance-management-service.git
```
2. Затем перейти в корневую директорию проекта и запустить docker-контейнер с БД PostgreSQL командой:
```bash
docker-compose up -d
```
3. Открыть и запустить склонированный проект, используя IntelliJ IDEA.
---


## Инструкция по использованию

Сервис представляет из себя бэкенд-приложения, функциональность которого заключается в использовании REST API, отвечающего за ключевые операции.
<br>Ниже представлены некоторые примеры таких операций с использованием программы командной строки ***curl***.
<br>Дополнительно автоматически сгенерированное описание спецификаций API можно посмотреть при запуске приложения по ссылке: http://localhost:8080/swagger-ui/index.html .

---
### Зарегистрировать пользователя и создать для него кошелек
Пример команды:
```bash
curl --location --request POST 'localhost:8080/users/registration' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "test@mail.ru",
    "password": "test"
}'
```
**Примечание**
<br>
Данный эндпоинт создает нового пользователя в системе, дополнительно привязывая к нему сформированный кошелек для
совершения последующих операций.
<br>
Для выполнения последующих команд необходимо обязательно запомнить/записать идентификатор сформированного кошелька 
пользователя, который будет предоставлен в ответе от сервера.

---


### Авторизовать пользователя в системе с помощью логина и пароля
Пример команды:
```bash
curl --location --request POST 'localhost:8080/users/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "test@mail.ru",
    "password": "test"
}'
```

**Примечание**
<br>
Данный эндпоинт имитирует процесс авторизации ранее зарегистрированного пользователя в системе с помощью логина и пароля.
<br>
В ответе сервер системы возвращает сгенерированный токен доступа, который можно использовать в течение ***30 минут***, и который будет использоваться для исполнения последующих функций 
от лица пользователя. Данный токен рекомендуется запомнить/записать.

---

### Создать категорию от лица авторизованного пользователя
Пример команды:
```bash
curl --location --request POST 'localhost:8080/categories' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QG1haWwucnUiLCJpYXQiOjE3MzY1NTIzMjAsImV4cCI6MTczNjU1NDEyMH0.LnvM2b-2SedS3kO4F8JA_hxJGby1ejCVrfSxs-GXTXc_FDxgGvLZbhvfapi1dtV6BNO_e4p3dCeRpQ5Rj3zRmQ' \
--data '{
    "title": "test_category"
}'
```

**Примечание**
<br>
Данный эндпоинт создает новую категорию от лица текущего авторизованного пользователя в системе.
<br>
В ответе сервер системы возвращает дополнительно идентификатор новой категории и ее название. Указанные данные необходимо
запомнить/записать для последующего использования.

---

### Установить/переустановить бюджет для указанной категории от лица авторизованного пользователя
Пример команды:
```bash
curl --location --request POST 'localhost:8080/budgets' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QG1haWwucnUiLCJpYXQiOjE3MzY1NTUyMjksImV4cCI6MTczNjU1NzAyOX0.hd-1Q_NDOyjohTEwI7zpP6OIq2DZBORMeFV-ga6HPmLPAuBtwzhphU-Yn_mHzY53Mxuz0Ad14ETfN50nMNOaHg' \
--data '{
    "amount": 10000,
    "categoryId": "074a4951-b416-4938-8f71-8afa0651e2da"
}'
```

**Примечание**
<br>
Данный эндпоинт устанавливает/переустанавливает размер бюджета для указанной категории от лица текущего 
авторизованного пользователя.

---

### Добавить операцию дохода или расхода от лица авторизованного пользователя
Пример команды:
```bash
curl --location --request POST 'localhost:8080/operations' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QG1haWwucnUiLCJpYXQiOjE3MzY1NTUyMjksImV4cCI6MTczNjU1NzAyOX0.hd-1Q_NDOyjohTEwI7zpP6OIq2DZBORMeFV-ga6HPmLPAuBtwzhphU-Yn_mHzY53Mxuz0Ad14ETfN50nMNOaHg' \
--data '{
    "walletId": "11f05c74-ad2d-4a55-9a73-918314307cf3",
    "categoryId": "074a4951-b416-4938-8f71-8afa0651e2da",
    "operationType": "INCOME",
    "amount": 2000
}'
```

**Примечание**
<br>
Данный эндпоинт создает операцию дохода или расхода от лица авторизованного пользователя для его указанного кошелька, 
а также указанной категории.

---

### Получить статистику об общей сумме доходов и расходов, а также данных по каждой категории от лица авторизованного пользователя
Пример команды:
```bash
curl --location --request GET 'localhost:8080/statistics/incomes-expenses-total' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QG1haWwucnUiLCJpYXQiOjE3MzY1NTUyMjksImV4cCI6MTczNjU1NzAyOX0.hd-1Q_NDOyjohTEwI7zpP6OIq2DZBORMeFV-ga6HPmLPAuBtwzhphU-Yn_mHzY53Mxuz0Ad14ETfN50nMNOaHg'
```

**Примечание**
<br>
Данный эндпоинт позволяет получить агрегированную информацию об общей сумме доходов и расходов, а также данных 
по каждой категории от лица авторизованного пользователя.

---

### Получить статистику об общей сумме доходов и расходов, а также данных по категориям в разрезе конкретных категорий от лица авторизованного пользователя
Пример команды:
```bash
curl --location --request GET 'localhost:8080/statistics/incomes-expenses-by-categories' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QG1haWwucnUiLCJpYXQiOjE3MzY1NTcyNzMsImV4cCI6MTczNjU1OTA3M30.CwrU-l2MuleAiVkTeRtWm1Do3F_dbiEFq0AXaEnHpguP3xDeEMiH7ebZlke4mFBr0BfkAE8dMHUiqUSAfgDjnw' \
--data '{
    "categories": [
        {
            "categoryTitle": "test_category"
        },
        {
            "categoryTitle": "test_category1"
        },
        {
            "categoryTitle": "test_category2"
        }
    ]
}'
```

**Примечание**
<br>
Данный эндпоинт позволяет получить агрегированную информацию об общей сумме доходов и расходов, а также данных
по категориям от лица текущего авторизованного пользователя в разрезе лишь указанных категорий, по которым требуется 
произвести подсчет статистики.

---

### Получить статистику о текущем состоянии бюджета для каждой категории с учетом оставшегося лимита от лица авторизованного пользователя
Пример команды:
```bash
curl --location --request GET 'localhost:8080/statistics/budget-status' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QG1haWwucnUiLCJpYXQiOjE3MzY1NTcyNzMsImV4cCI6MTczNjU1OTA3M30.CwrU-l2MuleAiVkTeRtWm1Do3F_dbiEFq0AXaEnHpguP3xDeEMiH7ebZlke4mFBr0BfkAE8dMHUiqUSAfgDjnw'
```

**Примечание**
<br>
Данный эндпоинт позволяет получить информационную сводку о состоянии установленного бюджета для каждой категории, а также оставшемся лимите от
лица текущего авторизованного пользователя.

---

### Обработка ошибок
Дополнительно в сервисе реализованы механизмы по обработке различных ситуационных ошибок:
* Ошибки валидации пользовательского ввода при отправке запросов;
* Ошибки отсутствия данных при отправке на сервер (не найден указанный кошелек для пользователя, 
не найдена указанная категория у пользователя и другое);
* Ошибки авторизации;
* Ошибки нарушения целостности данных (расходы пользователя превысили доходы при проведении операции, 
существование ранее зарегистрированного пользователя с указанной почтой при попытке регистрации и другое);

При возникновении данных ошибок после отправки запросов сервер системы возвращает ответ пользователю с 
соответствующим кодом ошибки.

---