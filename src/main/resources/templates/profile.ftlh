<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Личный кабинет</title>

</head>
<body>
<h2>Сервис подбора фильмов</h2><hr>
<h2>Имя пользователя: ${user.username}</h2>

<a href="/recommendations">Список рекомендаций<br></a>
<br>
<a href="/watchedFilms">Список просмотренных фильмов<br></a>
<br>
<form action="/home" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="На главную"/>
</form>
</div>
</body>
</html>