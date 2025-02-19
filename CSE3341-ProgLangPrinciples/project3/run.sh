#!/bin/bash
rm *.class
javac *.java

if [ "$1" -eq 0 ]; then
    for j in {1..30}
    do
        java Main Correct/$j.code Correct/$j.data
    done
else
    java Main Correct/$1.code Correct/$1.data
fi
