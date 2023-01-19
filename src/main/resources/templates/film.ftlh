<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>

    <link rel="stylesheet" href="https://snipp.ru/cdn/select2/4.0.13/dist/css/select2.min.css">
    <style type="text/css">
    </style>
    <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://snipp.ru/cdn/select2/4.0.13/dist/js/select2.min.js"></script>
    <script src="https://snipp.ru/cdn/select2/4.0.13/dist/js/i18n/ru.js"></script>

</head>
<body>
<h2>Сервис подбора фильмов</h2><hr>
<h3>Фильм</h3>


<p>Название: ${film.title}</p>
<a href="${film.image}"><img src="${film.image}" style="height: 200px; width: 150px;" alt="image"></a>
<p>Страна: ${film.country}</p>
<p>Жанр: <#foreach genre in film.genre>
            <td>${genre.name}</td>
        </#foreach>
<p>Дата выпуска: ${film.releaseDate}</p>
<p>Описание: <br> ${film.description}</p>
<form action="/home" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="На главную"/>
</form>
<br>
<#if !isWatched>
<form action="/addToWatched/${film.id}" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Уже посмотрел">
</form>
<#else>
    <p></p>
</#if>
</body>
</html>