# 🟩 WORDLE — Java Edition

A fully-featured Wordle clone built in Java with both console and web versions.

---

## 📁 Project Structure

```
WordleGame/
├── WordleGame.java         ← Original console main entry point & game loop
├── GameLogic.java          ← Core Wordle evaluation rules
├── GameBoard.java          ← Tracks guesses and board state
├── WordLoader.java         ← Loads words.txt, validates guesses
├── Display.java            ← All colored terminal output (ANSI)
├── LetterResult.java       ← Enum: CORRECT / PRESENT / ABSENT
├── WordleApplication.java  ← Spring Boot main application
├── GameService.java        ← Service layer for web version
├── GameController.java     ← REST API controller
├── GameState.java          ← Game state model
├── words.txt               ← 100 valid 5-letter words
├── pom.xml                 ← Maven configuration
├── src/main/resources/static/index.html ← Web frontend
└── README.md               ← You're here
```

---

## 🚀 How to Run

### Web Version (Recommended)

#### Requirements

- Java 17 or higher
- Maven 3.6+ ([Install Maven](https://maven.apache.org/install.html))

**⚠️ Windows Users**: If Maven is not installed, follow the [Maven Installation Guide](#maven-installation-guide-for-windows) below.

#### Run the Web Server

**Option 1: Using helper scripts (Windows)**

```bash
# First time: compile and build
build.bat

# Run the server
run.bat
```

**Option 2: Using Maven directly**

```bash
mvn spring-boot:run
```

#### Play the Game

Open your browser and go to: **http://localhost:8080**

The web version features:

- Beautiful dark theme UI
- Animated tile flips
- On-screen keyboard
- Responsive design for mobile and desktop
- Smooth animations and transitions

### Console Version

#### Requirements

- Java 14 or higher (uses switch expressions)

#### Compile

```bash
javac *.java
```

#### Run

```bash
java WordleGame
```

---

## Maven Installation Guide for Windows

### Option A: Manual Installation (Recommended)

1. **Download Maven** from https://maven.apache.org/download.cgi
   - Download the binary zip file (e.g., `apache-maven-3.9.6-bin.zip`)

2. **Extract** to a folder (e.g., `C:\Apache\maven`)

3. **Add to System PATH**:
   - Open System Properties → Environment Variables
   - Edit the `Path` variable and add: `C:\Apache\maven\bin`
   - Click OK and close all windows
   - **Restart your terminal/PowerShell after changing PATH**

4. **Verify Installation**:
   ```bash
   mvn --version
   ```
   You should see Maven version info.

### Option B: Using Chocolatey (if you have it installed)

```bash
choco install maven
```

### Option C: Using SCOOP (if you have it installed)

```bash
scoop install maven
```

After installation, verify Maven is working by running:

```bash
mvn --version
```

Then run the web server:

```bash
mvn spring-boot:run
```

---

## 🎮 How to Play

- Guess the secret **5-letter word** in **6 tries**
- After each guess, tiles change color:

| Color     | Meaning                               |
| --------- | ------------------------------------- |
| 🟩 Green  | Letter is correct & in the right spot |
| 🟨 Yellow | Letter is in the word, wrong position |
| ⬛ Gray   | Letter is not in the word at all      |

- An on-screen **keyboard tracker** shows which letters you've used
- Type your guess and press Enter (console) or use the on-screen keyboard (web)
- Play again by pressing `Y` when prompted (console) or clicking "Play Again" (web)

---

## 🧠 Features

- ✅ Official Wordle duplicate-letter rules (correct handling of multi-letter edge cases)
- ✅ Colored board rendering with ANSI escape codes (console)
- ✅ Beautiful web UI with animations (web)
- ✅ ASCII art banner (console)
- ✅ Live keyboard tracker showing letter status
- ✅ 100-word dictionary (words.txt) — easy to expand
- ✅ Input validation (length, characters, word validity)
- ✅ Play again without restarting
- ✅ REST API for programmatic access

---

## 📡 REST API

The web version exposes a REST API:

### Start New Game

```http
GET /api/new-game
```

Response:

```json
{
  "gameId": "uuid-string",
  "board": [["EMPTY", "EMPTY", "EMPTY", "EMPTY", "EMPTY"], ...]
}
```

### Submit Guess

```http
POST /api/guess
Content-Type: application/json

{
  "gameId": "uuid-string",
  "guess": "STEAL"
}
```

Response:

```json
{
  "results": ["CORRECT", "PRESENT", "ABSENT", "ABSENT", "PRESENT"],
  "board": [["CORRECT", "PRESENT", "ABSENT", "ABSENT", "PRESENT"], ...],
  "keyboard": {"S": "CORRECT", "T": "PRESENT", ...},
  "won": false,
  "lost": false
}
```

---

## 📝 Extending the Word List

Simply open `words.txt` and add more 5-letter words (one per line, letters only). The game will automatically pick them up on next run.

---

## 📦 Dependencies

- **Web Version**: Spring Boot (web starter)
- **Console Version**: Pure Java standard library — no external dependencies
