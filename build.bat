@echo off
REM Setup and Build Script for Wordle Game Spring Boot Application

echo.
echo ====================================================
echo Wordle Game - Spring Boot Build Helper
echo ====================================================
echo.

REM Check if Maven is installed
where mvn >nul 2>&1
if errorlevel 1 (
    echo [ERROR] Maven is not installed or not in your PATH.
    echo.
    echo To install Maven on Windows:
    echo 1. Download from: https://maven.apache.org/download.cgi
    echo 2. Extract to a folder (e.g., C:\Apache\maven)
    echo 3. Add to PATH: C:\Apache\maven\bin
    echo 4. Verify with: mvn --version
    echo.
    echo Or install via Chocolatey: choco install maven
    echo Or install via SCOOP: scoop install maven
    echo.
    pause
    exit /b 1
)

echo [OK] Maven found
mvn --version

echo.
echo Compiling project...
mvn clean compile

if errorlevel 1 (
    echo [ERROR] Compilation failed.
    pause
    exit /b 1
)

echo.
echo [OK] Build successful!
echo.
echo To run the Spring Boot application:
echo   mvn spring-boot:run
echo.
echo Then open your browser to: http://localhost:8080
echo.
pause
