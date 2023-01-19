<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Фильмы</title>
</head>
<body>
<h2>Сервис подбора фильмов</h2><hr>
<h2>Список фильмов</h2>

<form action="/films" method="get">
</form><br>
        <#foreach film in films>
                <a href="/film/${film.id}"><img src="${film.image}" style="height: 200px; width: 150px;" alt="image"></a>
        </#foreach>
        <tr>
        </tr>

<form action="/home" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="На главную"/>
</form>
</body>
</html>