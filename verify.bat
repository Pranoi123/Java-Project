@echo off
REM Verification script to check if project is ready

echo.
echo ====================================================
echo     Wordle Spring Boot - Setup Verification
echo ====================================================
echo.

setlocal enabledelayedexpansion

set errors=0

echo [Checking] Java installation...
java -version >nul 2>&1
if errorlevel 1 (
    echo [ERROR] Java is not installed or not in PATH
    set /a errors+=1
) else (
    echo [OK] Java found
)

echo.
echo [Checking] Maven installation...
mvn --version >nul 2>&1
if errorlevel 1 (
    echo [WARNING] Maven is not installed or not in PATH
    echo            This is needed to build and run the project
    echo            Follow the Maven Installation Guide in README.md
    set /a errors+=1
) else (
    echo [OK] Maven found
)

echo.
echo [Checking] Project structure...
if exist "src\main\java\com\wordle\WordleApplication.java" (
    echo [OK] Spring Boot application file found
) else (
    echo [ERROR] Spring Boot application file not found
    set /a errors+=1
)

if exist "src\main\resources\static\index.html" (
    echo [OK] Web frontend found
) else (
    echo [ERROR] Web frontend not found
    set /a errors+=1
)

if exist "words.txt" (
    echo [OK] Words file found
) else (
    echo [ERROR] Words file not found
    set /a errors+=1
)

if exist "pom.xml" (
    echo [OK] Maven configuration found
) else (
    echo [ERROR] Maven configuration not found
    set /a errors+=1
)

echo.
echo ====================================================

if %errors% equ 0 (
    echo [SUCCESS] Setup verification passed!
    echo.
    echo To start the server, run:
    echo   mvn spring-boot:run
    echo.
    echo Then open http://localhost:8080 in your browser
) else (
    echo [FAILED] %errors% issue(s) found
    echo.
    echo Please check the README.md for setup instructions
)

echo.
pause
