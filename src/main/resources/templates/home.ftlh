<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Сервис подбора фильмов</title>
</head>
<body>
<h1>Сервис подбора фильмов</h1><hr>

<nav class="menu">
    <ul>
        <li><a href="home">Главная</a></li>
        <li><a href="films">Все фильмы</a></li>
        <li><a href="profile">Профиль</a></li>
    </ul>
</nav><br><br>

<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Выйти"/>
</form><br>

</body>
</html>