# Alunos: Elias Fank; João Gehlen; Ricardo Zanuzzo

ifeq (run,$(firstword $(MAKECMDGOALS)))
  RUN_ARGS := $(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))
  $(eval $(RUN_ARGS):;@:)
endif

BIN_DIR=bin/
SRC_DIR=src/
CP = javac
NAME= Viterbi
FLAGS= -cp
EX = java

all:
	$(CP) $(SRC_DIR)*.java -d $(BIN_DIR)

run:
	$(EX) $(FLAGS) $(BIN_DIR) $(NAME) $(RUN_ARGS)
