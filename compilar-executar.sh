[ -d bin/ ] || mkdir bin/
javac src/*.java -d bin/
java -cp bin/ Viterbi