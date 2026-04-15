@echo off
REM Run Script for Wordle Game Spring Boot Application

echo.
echo ====================================================
echo Starting Wordle Game Server
echo ====================================================
echo.

where mvn >nul 2>&1
if errorlevel 1 (
    echo [ERROR] Maven is not installed or not in your PATH.
    echo Please run build.bat first for installation instructions.
    pause
    exit /b 1
)

echo Starting Spring Boot application on http://localhost:8080
echo Press Ctrl+C to stop.
echo.

mvn spring-boot:run

pause
