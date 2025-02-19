rm -f *.class
javac Main.java
for i in {1..26}
do
    java Main Correct/$i.code
done