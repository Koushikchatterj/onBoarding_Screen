# Jetpack Compose onBoarding Screens

https://github.com/user-attachments/assets/ffe48a3c-8d98-4b7b-8d3c-ac9d7081e4ac
## Features
- Splash Screen: 
A splash screen is displayed while the app is loading.

- Onboarding Screen: 
The first page (onboarding screen) is shown only once, on the first launch of the app.

- Navigation: 
The app uses Jetpack Compose navigation to move between the onboarding screen and the main content.

- ViewModel Integration: 
The app uses a ViewModel to handle state management and background loading.

## How It Works

- Splash Screen: 
The app shows a splash screen while loading.

- Onboarding Screen: 
If this is the first time the app is opened, the onboarding screen is shown. Once the user clicks the button, the app remembers that the user has seen the onboarding screen.

- Main Screen: 
On future app launches, the app will bypass the onboarding screen and go directly to the main content.
