
default: classes

classes:
	@mkdir -p bin
	javac -g -classpath bin -sourcepath src src/*.java -d bin

jar:
	make classes
	make ojar

ojar:
	jar cvfm CalculMental.jar Manifest  -C bin . 

run:
	java -cp bin JeuCalculGUI

crun:
	make classes
	make run	

jrun:
	make jar
	java -jar CalculMental.jar

#Supprimer les fichiers class
clean:
	rm bin/*.class
