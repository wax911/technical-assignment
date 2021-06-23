# technical-assignment
Mobile Developer Technical Assignment 2021


## Project overview

The project is broken down into the following high level modules/compoents which facilitate common or similar functionality, this is done for the simple reason to allow easier colaboration and matainability which would be otherwise challenging if the `app` module houses all the layers

![](./docs/high_level_structure.png)

> Features will be included in the main application module `app` as a [runtimeOnly](https://docs.gradle.org/current/userguide/java_library_plugin.html#sec:java_library_configurations_graph) module to enforce low coupling between the application and it's feature modules, the benefit of doing this is the fact that we can deactivate certain features or enable them without breaking the build or affecting anything in the project. All the navigation will be handled by `navigation` which will define contracts for destinations though dependency injection


### States Flow

![](./docs/app_states.png)
