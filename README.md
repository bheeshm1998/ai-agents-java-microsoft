# 🧠📖 Multi-Agent AI Story Illustrator

## 🚀 Overview

This project is an AI-powered multi-agent system that transforms short stories into **illustrated, downloadable Markdown or PDF files**. By combining the strengths of large language models (LLMs), image generation (DALL·E 3), and a modular agent architecture, the system allows users to input a story, specify the number of illustrations, choose an image theme/style, and receive a visually rich storytelling output — fully automated.

## ✨ Features

- 🔍 **Multi-Agent Workflow**  
  A pipeline of agents extracts story metadata, characters, events, and physical features.

- 🎨 **AI Image Generation**  
  Prompts are crafted for each scene using OpenAI’s DALL·E 3 to generate consistent and theme-aligned illustrations.

- 📝 **Markdown & PDF Output**  
  The final result is an illustrated version of the story with images embedded in the correct places — downloadable as Markdown or PDF.

- 🌐 **Web Interface**  
  Built with Angular for seamless user input and file downloads.

## 🛠️ Tech Stack

| Layer             | Technology                         |
|------------------|-------------------------------------|
| Backend API      | Java + Spring Boot                  |
| Frontend         | Angular                             |
| LLMs             | OpenAI GPT-4.1 + DALL·E 3            |
| Multi-Agent Logic| LangChain (Java via LangChain4j)    |
| Deployment       | Azure Web App (Dockerized)          |

## 🧩 Agent Architecture

1. **Metadata Agent**  
   Extracts story title, author, and actual story content from raw input.

2. **Character Agent**  
   Detects the unique characters of the story.

3. **Event/Scene Agent**  
   Identifies distinct scenes or events from the story based on the number of scenes (or images) requested by the user. Also outputs the characters involved in those scenes (by taking the charactersList as input from the Character Agent)

4. **CharacterPhysicalFeatures Agent**  
   Generates detailed physical and stylistic descriptions of characters based on the story and context to maintain consistency of the images across the story

5. **SceneCreation Agent**  
   Constructs detailed prompts for scene-based illustrations based on the scene description and the characters features.

6. **Image Generator Agent**  
   Calls OpenAI’s DALL·E 3 to generate images for each scene.

7. **Composer Agent**  
   Assembles Markdown with embedded images and prepares final output.

## 🖼️ Example Workflow

1. User enters a story and selects:
   - Number of illustrations
   - Image style (e.g., watercolor, anime, pencil sketch)

2. Backend triggers the agent chain:
   - Title: *The Lost Balloon*
   - Characters: Anya, Clown, Magician, Elephant, etc.
   - Scenes: 5 major moments identified
   - Prompts: AI-generated image prompts per scene

3. LLM composes the story in Markdown:
   - With images inserted at key points

4. Final file generated:
   - User downloads `.md` or `.pdf`

## 🧪 Demo

> **Input:** Short story text  
> **Output:**  
> - Markdown file with text and images  
> - Downloadable PDF  
> - Consistent visual narrative

## 📦 Installation

### 1. Clone the repository

```bash
git clone https://github.com/bheeshm1998/ai-agents-java-microsoft
```

### 2. Run the frontend
```bash
cd ui-2
npm install 
ng serve --open
```

### 3. Set the OpenAi Api Key
The environment variable name is `OPENAI_API_KEY`
### 4. Run the backend
```bash
cd azure-ai-java
mvn clean install
mvn spring-boot:run
```


