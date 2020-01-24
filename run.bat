@echo off
set arg1=%1
set JAVA_HOME=C:\Software_MG\OpenJDK_11\

IF "%arg1%"=="build" (
@echo Build is running
mvn clean package
)
