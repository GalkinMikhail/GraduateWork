<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список рекомендаций</title>
</head>
<body>
<h1>Сервис подбора фильмов</h1><hr>
<h2>Список рекомендаций</h2>

<form action="/recommendations" method="get">
</form><br>
        <#foreach film in recomList>
            <a href="/film/${film.id}"><img src="${film.image}" style="height: 200px; width: 150px;" alt="image"></a>
        </#foreach>
<form action="/home" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="На главную"/>
</form>
<br>
<form action="/profile" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Назад"/>
</form>
</body>
</html>