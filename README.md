12.Система Быстрого Тестирования Студентов. Студент регистрируется
э-мейлом, к нему привязуеться его Успешность и на него будут приходить
сообщения о результате тестов. В системе существует каталог Тестов по
темам, авторизованный Студент может проходить тесты. В конце теста
должна на странице отобразится форма где показано ошибки студента. Все
данные об успеваемости и пройденных курсах отображаются на странице
Администратора как сводная таблица по всем Студентам.

Install and run

1.Create database from files: \src\main\resources\DBScripts

2.Run mvn clean install

3.Copy WAR file from StudentTesting\target directory to Tomcat webapps directory.

4.Rename to ROOT.war

5.Run tomcat:bin\catalina.bat start

6.Open http:\\localhost:8080