[ -d bin/ ] || mkdir bin/
javac src/*.java -d bin/
java -cp bin/ Viterbi 1000 3