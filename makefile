
compile:
	javac -sourcepath src -classpath bin src/*.java -d bin

jar:
	make compile
	make ojar

ojar:
	jar cvfm CalculMental.jar Manifest  -C bin . 

run:
	java -cp bin/ JeuCalculGUI

crun:
	make compile
	java -cp bin/ JeuCalculGUI

jrun:
	make jar
	java -jar CalculMental.jar

clean:
	rm bin/*.class
