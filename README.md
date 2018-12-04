# Easy Weather
A demo app to test the Open Weather Map API
## Installation
1. Clone this repository to your local disk and open it in Android Studio (or any other IDE of your choice)
2. Get an API key from https://openweathermap.org/api for the current weather API.
3. Add the API key as a String resource `R.string.open_weather_api_key` (NOTE: Do not add it in your strings.xml if you plan to make any changes to this app and share it via git. Add it to a seperate String resource xml (`keys.xml`) and make sure it is in your `.gitignore`).
## Configuration and usage
Make sure you change the location under `String location` in `MainActivity.java`.
Run the app using Android Studio on your android device. Make sure your device is connected to the internet.
## API
The app implements the [Open Weather Map API](https://openweathermap.org/api) to check out its features and usage. The API is free to use upto a certain limit and requires an API key.
## Future Development
As of now, there are no future plans to develop this app. The main purpose was to test the Open Weather Map API which has been fulfilled. If there are any updates, they will be notified here.
