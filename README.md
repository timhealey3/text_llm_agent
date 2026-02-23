# Text LLM Agent

A Spring Boot application that acts as a Telegram bot powered by a local (LLM) via Ollama. It periodically polls for new Telegram messages, processes them using an LLM, and sends responses back to the user.

## Prerequisites

- **Java 21** or higher.
- **Ollama:** Installed and running on `http://localhost:11434`.
  - For this project I used: `ollama gemma3:4b` but any model will do
- **Telegram Bot Token:** Create a bot via [@BotFather](https://t.me/botfather) to get an API token.

## Configuration

### Telegram API Token
Currently, the API token is hardcoded in `src/main/kotlin/com/example/text_llm_agent/TelegramService.kt`.
Replace `"REPLACE TOKEN"` with your actual Telegram Bot Token:

## Getting Started
 
**Build the application:**
   ```bash
   ./gradlew build
   ```

3. **Run the application:**
   ```bash
   ./gradlew bootRun
   ```
   The application will initialize the SQLite database (`text.db`) on startup.

## Usage

The bot processes messages when the `/text-llm-agent` endpoint is triggered.

### Triggering the Agent
Send a GET request to the following endpoint:
```bash
curl http://localhost:8080/text-llm-agent
```

### Cronjob Setup
To make the bot responsive, set up a cronjob to trigger the endpoint periodically.

Example (run every 5 minutes):
```bash
*/5 * * * * curl -s http://localhost:8080/text-llm-agent > /dev/null
```
