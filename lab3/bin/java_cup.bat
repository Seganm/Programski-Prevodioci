@echo off
REM ********   PODESAVANJA - MENJA STUDENT *********
set JCUP_HOME="C:\Users\Milos Milenkovic\Desktop\lab3\java_cup_v10k"
set JAVA_HOME="C:\Program Files\Java\jdk-21"
set PARSER_CLASS_NAME="MPParserError"
set CUP_SPEC_NAME="MPParserError.cup"


REM ********   POZIV JAVA CUP APLIKACIJE  ***********
echo vrednost : %JCUP_HOME%
%JAVA_HOME%\bin\java -classpath %JCUP_HOME% java_cup.Main -parser %PARSER_CLASS_NAME% -symbols sym < %CUP_SPEC_NAME%

PAUSE
