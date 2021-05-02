# ¡IMPORTANT¡
If you want this project to work, insert your Public and Private keys in the local.properties file
```properties
#Marvel API KEYS
marvel.key.public = <insert>
marvel.key.private = <insert>
```
# Dependencies

-   [Jetpack](https://developer.android.com/jetpack):
    -   [Android KTX](https://developer.android.com/kotlin/ktx.html) - Kotlin to Jetpack Apis
    -   [AndroidX](https://developer.android.com/jetpack/androidx) - AndroidX library
    -   [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Helps you track the lifecycle state of your Android based components
    -   [Navigation](https://developer.android.com/guide/navigation/) - AndroidX Navigation library, very powerful.
    -   [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - The ViewModel class allows data and state to survive configuration changes such as screen rotations.
-   [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - Managing background threads in a simple way
-   [Hilt](https://dagger.dev/hilt/) - Android Dependency Injector
-   [Ktor](https://ktor.io/docs/welcome.html) -Pure KotlinHTTP client.
-   [Glide](https://bumptech.github.io/glide/) -Library for image loading and caching.

# Architecture

## Module by feature
The modules created here are dependent on the features we want to implement and the features
are created by domain logic, in this case the Marvel characters.
There is a core module holding the network dependencies and extensions the whole application is
going to use

## Clean Architecture
The feature modules are divided internally in clean architecture. Inside the modules exists a 
domain package for logic, data package for data retrieving and presentation package for everything
UI related

## Dependency injection
Another must-have architecture implementation. In this case I chose Hilt because I wanted to test
it after the demise of DaggerAndroid.
It's much easier now to inject AndroidPlatform Dependencies that it was before, but i'd still go
with Koin because it works with pure Kotlin and it's the one I use in my KMM projects.

## MVx
For the view I use a combination of MVVM and MVI. The ViewModel it's the one to hold the data
and the state of the view, so the view only listens to the ViewState changes and pass the data
to the ViewModel

## Components
The components allow us to have a much more modularized application, so we can reuse the components
anywhere in the application or the module.
These components listen the state provided by the Fragment (which is provided by the ViewModel)
and change their visibility and data accordingly.
I'm planning to move to Compose once I have more time to play with it and it exits Beta, it's very
similar to Flutter widgets and I loved it when it came out.

## Single Activity Application
Since the navigation jetpack library came out, I'm working only with single Activity Applications.
It makes things much much easier since you don't have to decide if this or that goes into an
Activity or a Fragment. It works like a charm with the components architecture since the only
thing reusable are the components with its views.

## BuildSrc
It helps the project by having all the dependencies in one place and allowing us to write gradle
extensions which is so nice to have. Also moving from .gradle to .gradle.kts is a must for all the 
help Kotlin provides in builds

# Libraries

## Ktor
Ktor is my to-go library when working with KMM projects, it's very simple and it works in pure
Kotlin. It has a lot of options to work with multiple platforms and I love the modularization it
provides to play with the requests.

## Glide
There is not much difference with Picasso at this level, but Glide is more lightweight and it has
an extensive documentation.

## Coroutines
In this project I use the Flow api from coroutines. It's super similar to the RX pattern but, simpler
and easier. Right now RX is on decline and people are moving to MVI architectures notifying states
instead of raw data to the UI (which is preferable) and Kotlin Flow allow us to do this smooth

## Navigation
Navigation with Fragments its much easier this way. No need to create a Navigator since Android
provides this for us. It also is visible and helps a lot when creating flux diagrams

# Methodologies

## git-flow
Git flow allows us to track the changes made in our project, and to assign branches to their
respective tickets. It helps everyone involved in the project, from the developers to the product
managers.

# TODOS

## UI improvements
The UI in this project is practically a scaffold. I tend to use my time showcasing my abilities in
the logic and domain department instead of focusing on the UI, but it's very much improvable.

## Testing
This project clearly lacks Unit Testing for its repository and unit cases, also it needs Network 
tests for Ktor (which Ktor provides out of the box with mocking), and UI Tests with Espresso.

## Dynamic features
I've seen and played with dynamic features, but I didn't had the necessity to use it it my projects
so I don't have a lot of experience with it. I really didn't know how it worked with Hilt so I 
decided not to take risks and try this in this project. I will probably will improve this repo in a
future or make a fork with dynamic features just to see how it plays out.

## Navigation
Right now the project has only Navigation within a feature, but if we wanted to have feature to
feature Navigation, the way to go is dynamic features and the dynamic features navigation library.

## Cleartext traffic
This is a no-no for security. But I really don't know how many endpoints the marvel API needs to be
allowed for images and all its dependencies, so I decided to go with allowing everything


