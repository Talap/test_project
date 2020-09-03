# test_project
После вытягивания проекта подключить БД postgreSQL

скачать зависимости из pom.xml

скачать плагин lombok если его нет (в настройках itellij idea: preferences -> plugins -> и в поиске написать "Lombok")

в файле "main/resources/application.properties" : 
1. выбрть порт на котором будет будет запущен проект
2. в том же файле в полях spring.datasource указать логин и пароль от postgres
3. так же в поле "spring.datasource.url" указать адрес БД
4. ВАЖНО при первом запуске spring.jpa.hibernate.ddl-auto= указан "create" при последующих запусках поменять на "none".

в папке main/resources/ есть файл data.sql оттуда скопировать sql запрос и запустить его в Query console. 

ГОТОВО!

Можно тестить запросы через SWAGGER API, в моем случае по адресу "http://localhost:8080/swagger-ui.html#/"
