# **Fun With Flags App**

*This is a demonstration **Android** app to get information from the [RestCountries API](https://restcountries.eu/#rest-countries) and display details of each nation of the  world in a beautiful and easy way.*

## Wiremocks

`Splash`, `List` and `Details` view.

<p align="center">
<img src="images/splash.gif" width="31%" style="margin: 2px; border: 1px solid gray;">
<img src="images/list.png" width="32%" style="margin: 2px; border: 1px solid gray;">
<img src="images/details.png" width="32%" style="margin: 2px; border: 1px solid gray;">
</p>

## Architecture

The app makes use of the **Architecture Components**  of the [Android Jetpack](https://developer.android.com/jetpack/) libraries. The design is based in **MVVM Pattern** and **reactive programming**.

With this architecture strongly guarantees [SoC](https://developer.android.com/jetpack/guide#separation-of-concerns) and using [DI principle](https://en.wikipedia.org/wiki/Dependency_inversion_principle) the integration of different parts is easier and clean.

To persist data the [Room](https://developer.android.com/jetpack/androidx/releases/room) library is implemented, giving the app cohesion and stability.

With **Data Binding** and **View Binding** the codebase follows the [SRP](https://en.wikipedia.org/wiki/Single_responsibility_principle) as the views are only responsible of visualization while the data transformations and business logic runs in the **View Models**.

For the UI a simple approach was conceived using **Material Design** based components and basic animations and transitions.

## Tasks

- [X] Config resources and gradle files.
- [X] Setting fragments and viewmodel.
- [X] Prepare Bindings and Navigation.
- [X] Setup API client with logger.
- [X] Handle data from API.
- [X] Visualize details.
- [X] Improve performance.
- [X] Internationalization.
- [X] Handle errors.

## Extras

- [/] RTL Languages.
- [X] Filters.
- [ ] Caching.
- [X] Animations.
- [X] Splash.
- [X] Google Map.
- [ ] UI tests.
- [ ] Share link.

* UnitTests implemented on demand for each task.