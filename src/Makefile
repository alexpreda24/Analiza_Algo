JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  main.java


build: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class out/*.out
	
run:
	java main