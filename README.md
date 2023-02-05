# auction-service
auction service.
1. Установить докер
2. Положить файлы Dockerfile, docker-compose.yml и jar - файл в одну директорию.
3. Открыть командную строку.
4. В командной строке перейти к данной директории
5. Прописать команду docker-compose up.
6. Подождать пока проекты поднимуться. 
7. Проект поднимается по адресу localhost:8082/auction .
8 Доступные эндпоинты можно посмотреть на странице http://localhost:8082/auction/swagger-ui/index.html.
9. Авторизация находится на странице http://127.0.0.1:8082/auction/login (localhost:8082/auction/login?username=admin@admin.com&password=admin)
10. По умолчанию доступен пользователь:
username: admin@admin.com
password: admin
