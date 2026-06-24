# BFHL REST API — Chitkara Assignment (Java Spring Boot)

## Project Structure

```
bfhl-api/
├── src/
│   ├── main/java/com/bfhl/
│   │   ├── BfhlApplication.java            ← Spring Boot entry point
│   │   ├── controller/
│   │   │   └── BfhlController.java         ← POST /bfhl, GET /health
│   │   ├── service/
│   │   │   ├── BfhlService.java            ← Service interface
│   │   │   └── BfhlServiceImpl.java        ← Business logic
│   │   ├── dto/
│   │   │   ├── BfhlRequest.java            ← Request DTO
│   │   │   ├── BfhlResponse.java           ← Response DTO
│   │   │   └── ErrorResponse.java          ← Error DTO
│   │   └── exception/
│   │       └── GlobalExceptionHandler.java ← Exception handling
│   ├── main/resources/
│   │   └── application.properties          ← Config (name, dob, email, roll)
│   └── test/java/com/bfhl/
│       └── BfhlApplicationTests.java       ← JUnit 5 test cases
├── Dockerfile
└── pom.xml
```

---

## ✅ Step 1 — Update Your Personal Details

Open `src/main/resources/application.properties` and replace:

```properties
app.user.full-name=John Doe         ← Your full name
app.user.dob=17091999               ← Your DOB in ddmmyyyy format
app.user.email=john@xyz.com         ← Your college email
app.user.roll-number=ABCD123        ← Your roll number
```

---

## ✅ Step 2 — Prerequisites

Make sure you have installed:
- **Java 17+** → https://adoptium.net
- **Maven 3.6+** → https://maven.apache.org/download.cgi
- **Git** → https://git-scm.com

Verify:
```bash
java -version
mvn -version
git --version
```

---

## ✅ Step 3 — Run Locally

```bash
# Navigate into the project
cd bfhl-api

# Build and run
mvn spring-boot:run
```

The API will start at: `http://localhost:8080`

Test it:
```bash
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{"data": ["a", "1", "334", "4", "R", "$"]}'
```

---

## ✅ Step 4 — Run Tests

```bash
mvn test
```

---

## ✅ Step 5 — Push to GitHub

```bash
git init
git add .
git commit -m "Initial commit - BFHL Spring Boot API"

# Create a repo on github.com, then:
git remote add origin https://github.com/YOUR_USERNAME/bfhl-api.git
git branch -M main
git push -u origin main
```

---

## ✅ Step 6 — Host on Render (Free)

1. Go to https://render.com and sign up (use GitHub login)
2. Click **"New" → "Web Service"**
3. Connect your GitHub repo
4. Fill in:
   - **Name**: bfhl-api
   - **Runtime**: Docker  
   - **Region**: Any
   - **Branch**: main
5. Click **"Create Web Service"**
6. Wait ~3-5 minutes for build to complete
7. Your URL will be: `https://bfhl-api.onrender.com`

**API Endpoints after hosting:**
- POST: `https://bfhl-api.onrender.com/bfhl`
- GET:  `https://bfhl-api.onrender.com/health`

---

## ✅ Step 7 — Host on Railway (Alternative)

1. Go to https://railway.app and sign in with GitHub
2. Click **"New Project" → "Deploy from GitHub repo"**
3. Select your repo
4. Railway auto-detects Dockerfile and deploys
5. Go to **Settings → Networking** and click "Generate Domain"

---

## API Reference

### POST /bfhl

**Request:**
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

**Response (200 OK):**
```json
{
  "is_success": true,
  "user_id": "john_doe_17091999",
  "email": "john@xyz.com",
  "roll_number": "ABCD123",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

### GET /health
```json
{ "status": "UP", "message": "BFHL API is running" }
```
