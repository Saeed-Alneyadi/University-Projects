rm -f *.class
javac Main.java
for i in {1..9}
do
    echo -------------------
    java Main Error/0$i.code
    echo -------------------
done