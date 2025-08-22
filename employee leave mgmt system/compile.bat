@echo off
echo Compiling Java Employee Leave Management System...
javac -d bin src/*.java
if %errorlevel% == 0 (
    echo Compilation successful!
    echo Running the application...
    java -cp bin Main
) else (
    echo Compilation failed!
    pause
)