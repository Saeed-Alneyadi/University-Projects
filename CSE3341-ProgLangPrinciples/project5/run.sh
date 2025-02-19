#!/bin/bash
rm *.class
javac *.java

if [ "$1" = "all" ]; then
    for j in {0..14}
    do
        echo "$j.CODE:"
        echo "--------------------"
        java Main Correct/$j.code Correct/$j.data
        echo ""
    done
else
    java Main Correct/$1.code Correct/$1.data
fi
