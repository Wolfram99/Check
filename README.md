1) Убедится что вы находитесь на уровне директории корня пректа 
2) Убедится что установлен jdk, при помощи комнды java -version в командной строке
3) Если всё хорошо -> то необходимо скомпилировать app комндой (это необходимо для подключения кода из других классов в main)
	javac -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java
4) Запустить скомпилированное приложение командой
	java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 3-1 2-5 5-1 discountCard=1111 balanceDebitCard=100
5) Так как передаваемый параметр discountCard не обязательный -> то при его отсутствии в файл result.csv будт записано "NOT FOUND;0%" 
