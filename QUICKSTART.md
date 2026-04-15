## QUICK START GUIDE

Your Wordle game is now ready to run as a Spring Boot web application!

### Step 1: Install Maven (if not already installed)

**Windows:**

1. Download from: https://maven.apache.org/download.cgi
2. Extract to: `C:\Apache\maven`
3. Add to PATH: `C:\Apache\maven\bin`
4. Restart your terminal/PowerShell
5. Verify: `mvn --version`

**Alternative (if you have Chocolatey):**

```powershell
choco install maven
```

### Step 2: Run the Web Server

From the project directory:

```powershell
mvn spring-boot:run
```

Or use the helper script:

```powershell
.\run.bat
```

### Step 3: Open in Browser

Go to: **http://localhost:8080**

---

## What Was Done

✅ **Project Structure Reorganized**

- Moved all Java files to: `src/main/java/com/wordle/`
- Added proper package declarations: `package com.wordle;`
- Created Spring Boot project structure

✅ **New Files Created**

- `WordleApplication.java` - Spring Boot entry point
- `GameService.java` - Business logic layer
- `GameController.java` - REST API endpoints
- `GameState.java` - Game state model
- `src/main/resources/static/index.html` - Web frontend

✅ **Web Frontend Features**

- Dark theme with Wordle colors (#121213, #538D4E, #B59F3B)
- 3D tile flip animations (staggered by letter)
- Shake animation on invalid guesses
- Bounce animation on wins
- On-screen QWERTY keyboard that updates in real-time
- Toast notifications for errors
- Game over modal
- Fully responsive (mobile & desktop)
- Vanilla JavaScript (no frameworks)

✅ **REST API Endpoints**

- `GET /api/new-game` - Start a new game
- `POST /api/guess` - Submit a guess

✅ **Original Files Preserved**

- All original Java files remain in the project root
- Console version still works: `javac *.java && java WordleGame`

---

## Troubleshooting

### "mvn: The term 'mvn' is not recognized"

→ Maven is not installed or not in PATH. Follow Step 1 above.

### Port 8080 already in use

→ Change port in: `src/main/resources/application.properties`
Edit: `server.port=8080` to any other port (e.g., 9090)

### Words not loading

→ Ensure `words.txt` is in the project root directory.
Maven will include it automatically during the build.

### Build fails with "cannot find symbol"

→ Make sure you're in the correct directory:
`cd C:\Users\Pranav\OneDrive\Desktop\Java-Project`
Then run: `mvn clean compile`

---

## Project Structure

```
Java-Project/
├── src/
│   ├── main/
│   │   ├── java/com/wordle/
│   │   │   ├── WordleApplication.java ← Spring Boot app
│   │   │   ├── GameController.java    ← REST API
│   │   │   ├── GameService.java       ← Business logic
│   │   │   ├── GameState.java         ← Game model
│   │   │   ├── GameLogic.java
│   │   │   ├── GameBoard.java
│   │   │   ├── WordLoader.java
│   │   │   └── LetterResult.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/index.html      ← Web UI
│   │       └── words.txt
│   └── test-classes/
│
├── WordleGame.java        ← Console version (preserved)
├── Display.java           ← Console UI (preserved)
├── pom.xml               ← Maven config
├── build.bat             ← Windows build helper
├── run.bat               ← Windows run helper
└── README (1).md         ← Updated with instructions
```

---

## Next Steps

1. Install Maven if needed
2. Run `mvn spring-boot:run`
3. Open http://localhost:8080
4. Start playing!

Enjoy your Wordle game! 🎮
