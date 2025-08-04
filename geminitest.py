from google import genai

client = genai.Client(api_key="AIzaSyDaunfDoBRIoY1r52WTRxpX9lN3Blfj1RU")

response = client.models.generate_content(
    model="gemini-2.5-flash-lite-preview-06-17", contents="Explain how AI works in a few words"
)
print(response)