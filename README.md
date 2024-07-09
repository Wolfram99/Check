1) Убедится что вы находитесь на уровне директории корня пректа 
2) Убедится что установлен jdk, при помощи комнды java -version в командной строке
3) Если всё хорошо -> то необходимо скомпилировать app комндой (это необходимо для подключения кода из других классов в main)
    javac -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java
4) Запустить скомпилированное приложение командой
   java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-1 discountCard=1111 balanceDebitCard=100 pathToFile=./products.csv saveToFile=./task2_result.csv
5) Так как products.csv был перемещё в корень проекта то команда в пункте 4 будет являтся правильной 

6) java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-1 discountCard=1111 balanceDebitCard=100 pathToFile=./products.csv
Данной командой создаться файл result.csv в корне проекта с ошибкой "BAD REQUEST"

7) java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-1 discountCard=1111 balanceDebitCard=12.1 saveToFile=./error_result.csv
Данной командой создаться файл error_result.csv в корне проекта с ошибкой BAD REQUEST

8) Так как передаваемый параметр discountCard не обязательный -> то при его отсутствии в файл result.csv будт записано "NOT FOUND;0%" 
