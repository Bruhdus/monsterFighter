To build and run MonsterFighter from the command line:
-----------------------------------------------------

1. Ensure you are in the root project directory. This directory contains this README, the src and doc directories.

2. Run the following command to compile the Java source code and place the resulting compiled classes into the
   bin directory:

     javac -d bin -cp src src/monsterfighter/GameEnvironment.java

3. To start MonsterFighter with a graphical user interface run:

     java -cp bin monsterfighter.GameEnvironment