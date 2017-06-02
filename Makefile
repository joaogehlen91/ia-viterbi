BIN_DIR=bin/
SRC_DIR=src/
CP = javac
NAME= Viterbi
FLAGS= -cp
EX = java

all:
	$(CP) $(SRC_DIR)*.java -d $(BIN_DIR)

run:
	$(EX) $(FLAGS) $(BIN_DIR) $(NAME)
