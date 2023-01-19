<!DOCTYPE html>
<html>
<head>
    <h1>Сервис подбора фильмов</h1>
    <meta charset="UTF-8">
    <title></title>

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
                placeholder: "Выберите жанр",
                maximumSelectionLength: 6,
                weight: 500,
                language: "ru"
            });
        });
    </script>
    <script>
        $(document).ready(function() {
            $('.js-select2').select2({
                placeholder: "Выберите период", // tofix
                maximumSelectionLength: 6,
                weight: 500,
                language: "ru"
            });
        });
    </script>
    <script>
        $(document).ready(function() {
            $('.js-select2').select2({
                placeholder: "Выберите...",
                maximumSelectionLength: 6,
                weight: 500,
                language: "ru"
            });
        });
    </script>
    <script>
        $(document).ready(function() {
            $('.js-select2').select2({
                placeholder: "Выберите фильмы:",
                maximumSelectionLength: 6,
                weight: 500,
                language: "ru"
            });
        });
    </script>
</head>
<body>
<h2>Пройдите короткий опрос для составления списка рекомендаций</h2><hr>
<form action="/passSurvey" method="post">
    <p> Выберите свой любимый жанр кино:
    <div class="select_wrp">
        <label>
            <select name="genre" placeholder="Выберите жанр">
                <option value="Боевик">Боевик</option>
                <option value="Драма">Драма</option>
                <option value="Детектив">Детектив</option>
                <option value="Ужасы">Ужасы</option>
                <option value="Комедия">Комедия</option>
            </select>
        </label><br><br>
    </div>
    <p> Выберите период, фильмы которого вам нравятся больше:
    <div class="select_wrp">
        От: <label>
            <input type="text" name="dateFrom" placeholder="2000">
        </label><br><br>
        До: <label>
            <input type="text" name="dateFor" placeholder="2022">
        </label><br><br>
    </div>
    <p> Вам больше нравится отечественный кинематограф или западный?
    <div class="select_wrp">
        <label>
            <select name="country" placeholder="Выберите...">
                <option value="Отечественные">Отечественные</option>
                <option value="Зарубежные">Зарубежные</option>
            </select>
        </label><br><br>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Ответить"/>
</form>
</body>
</html>
