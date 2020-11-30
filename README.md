# Xapo test project
This is a project used to evaluate candidate's coding skills and Android knowledge.

## Description
The project's idea is to list trending projects from Github and then tap on one of them and show their details.

**Requirements**
- Write your application in Kotlin
- Ensure your application looks good on different screen sizes and densities
- Ensure your application supports Android API 23+
- Use good architecture and design patterns
- Use valuable external libraries that you are used to
- Use reactive programming

**Bonus** 
- Filtering and ordering functionalities
- Unit tests
- Shared element transition or other animations (but only if they are functional to the user experience)

If there is something not specified, please be free to decide on it. 
Let us know if you need something or you have any doubt about the project.

# Hand over

Whenever you are done, just create a release in github or contact HR!



# Implementation

## Prerequisites
* Android SDK v23 and above
* Compile SDK v30
* Latest Android Build Tools
* Gradle 6.5

## Installation

1. Download or clone this repository
2. Open project in Android Studio
3. Sync project with Gradle files
4. Run 'app' solution on your mobile phone or emulator

## Technlogies

* Android
* Jetpack
* MVVM
* Kotlin
* Live Data
* Kotlin coroutines
* RxKotlin
* Retrofit
* Room
* Picasso
* Mockito

## Architecture
App is developed as a single activity with multiple fragments and is written in Kotlin language. 

Architecture is based on Clean architecture concept.

It uses Android [Navigation Component](https://developer.android.com/guide/navigation) for navigating between screens.

UI states are held in View Models and propagated via Live data values.

Unit tests are based on Mockito and JUnit.

### Async work:
API calls are handled by Retrofit via RxKotlin.
DB calls are handled by Room database via RxKotlin.
Other async tasks are done via Kotlin coroutines.

Both technologies are used just for demonstration.
