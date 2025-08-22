#!/bin/bash
echo "Compiling Java Employee Leave Management System..."
mkdir -p bin
javac -d bin src/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "Running the application..."
    java -cp bin Main
else
    echo "Compilation failed!"
fi