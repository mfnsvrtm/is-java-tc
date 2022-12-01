1. Мне кажется, с требованием "Запросы должны быть написаны в одном SQL файле и
   размечены комментариями. В качестве комментария необходимо указать текст
   задания" нужно что-то сделать. Потому что копировать текст задания и
   форматировать его в комментарий - достаточно неприятный и не особенно
   полезный процесс. Предлагаю либо требовать в качестве комментраия номер
   задания (например, 3.1), либо предоставить "студентам" готовый шаблон,
   в котором уже есть все необходимые комментарии (как в моём втором коммите).
2. Если в качестве базы данных будет MySQL, нужно в тексте задания исправить
   последнее предложение в разделе "Концептуальная модель".
3. В задании 1.3 сказано использовать функцию `NVL`. Если я правильно понимаю,
   её нет в MySQL.
4. В задании 5.1 нужно использовать столбец `Discount`. Предоставленный скрипт
   заполняет его нулями. При создании таблицы `Order Details` столбец объявлен
   `Discount REAL(8,0)`. Второй аргумент 0, поэтому сохраняется только целая
   часть числа, а заполняется столбец значениями в интервале (0; 1).
5. В задании 6.4 требуется использовать `CUBE`. Если я правильно понимаю,
   его нет в MySQL.
6. Сложно догадаться, какое решение ожидается в 6.5. Сказано, что нельзя
   использовать `JOIN`. Чем можно (следует) пользоваться?
7. В названии региона Western из таблицы `Region` опечатка - Westerns.