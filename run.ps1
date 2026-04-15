#!/usr/bin/env pwsh
# Wordle Game Spring Boot Runner

Write-Host "`n" -ForegroundColor Green
Write-Host "=====================================================" -ForegroundColor Green
Write-Host "    Wordle Game - Spring Boot Application" -ForegroundColor Green
Write-Host "=====================================================" -ForegroundColor Green
Write-Host "`n"

# Check if Maven is available
$mvnCheck = $null
try {
    $mvnCheck = mvn --version 2>$null
} catch {
    $mvnCheck = $null
}

if ($null -eq $mvnCheck) {
    Write-Host "[ERROR] Maven is not installed or not in PATH`n" -ForegroundColor Red
    Write-Host "To install Maven on Windows:" -ForegroundColor Yellow
    Write-Host "  1. Download: https://maven.apache.org/download.cgi" -ForegroundColor Cyan
    Write-Host "  2. Extract to: C:\Apache\maven" -ForegroundColor Cyan
    Write-Host "  3. Add to PATH: C:\Apache\maven\bin" -ForegroundColor Cyan
    Write-Host "  4. Restart PowerShell and run this script again" -ForegroundColor Cyan
    Write-Host "`nOr install via Chocolatey: choco install maven" -ForegroundColor Cyan
    Write-Host "Or install via SCOOP: scoop install maven`n" -ForegroundColor Cyan
    exit 1
}

Write-Host "[OK] Maven found:" -ForegroundColor Green
Write-Host $mvnCheck -ForegroundColor Cyan
Write-Host "`n"

Write-Host "Starting Wordle Game Server..." -ForegroundColor Green
Write-Host "Open your browser to: http://localhost:8080" -ForegroundColor Cyan
Write-Host "Press Ctrl+C to stop the server`n" -ForegroundColor Yellow

mvn spring-boot:run
