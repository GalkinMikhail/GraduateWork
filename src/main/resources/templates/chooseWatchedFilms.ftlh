<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://snipp.ru/cdn/select2/4.0.13/dist/css/select2.min.css">
    <style type="text/css">
        .select_wrp {
            width: 1500px;
            margin: 0 auto;
        }
    </style>
    <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://snipp.ru/cdn/select2/4.0.13/dist/js/select2.min.js"></script>
    <script src="https://snipp.ru/cdn/select2/4.0.13/dist/js/i18n/ru.js"></script>
    <script>
        $(document).ready(function() {
            $('.js-select2').select2({
                placeholder: "Выберите фильмы:",
                maximumSelectionLength: 100,
                weight: 500,
                language: "ru"
            });
        });
    </script>
    <meta charset="UTF-8">
    <title>Список рекоммендаций</title>
</head>
<body>
<h1>Сервис подбора фильмов</h1><hr>
<h2>Выберите фильмы, которые уже смотрели</h2>

<form action="/choose" method="post">
    <div class="select_wrp">
        <label>
            <select class="js-select2" name="film" placeholder="Выберите фильмы:" multiple="multiple">
                    <#list recoms as film>
                        <option value="${film.id}">${film.title}</option>
                    </#list>
                </select>
        </label><br><br>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Выбрать"/>
</form>
<form>
    <p>Список просмотренных фильмов</p>
    <br>
    <#foreach film in watched>
        <td>${film.title}<br></td>
    </#foreach>
    <tr>
    </tr>
</form>
<form action="/recommendations" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Завершить опрос"/>
</form>
</body>
</html>
