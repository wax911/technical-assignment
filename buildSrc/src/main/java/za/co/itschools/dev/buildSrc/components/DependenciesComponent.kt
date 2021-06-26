package za.co.itschools.dev.buildSrc.components

import za.co.itschools.dev.buildSrc.Libraries
import za.co.itschools.dev.buildSrc.module.Modules
import za.co.itschools.dev.buildSrc.extension.*
import za.co.itschools.dev.buildSrc.strategy.DependencyStrategy
import org.gradle.api.Project

private fun Project.applyFeatureModuleGroupDependencies() {
    println("Applying shared dependencies for feature module -> $path")

    dependencies.implementation(Libraries.AndroidX.Core.coreKtx)
    dependencies.implementation(Libraries.AndroidX.Work.runtimeKtx)
    dependencies.implementation(Libraries.AndroidX.Activity.activityKtx)
    dependencies.implementation(Libraries.AndroidX.Fragment.fragmentKtx)
    dependencies.implementation(Libraries.AndroidX.StartUp.startUpRuntime)
    dependencies.implementation(Libraries.AndroidX.Collection.collectionKtx)
    dependencies.implementation(Libraries.AndroidX.SwipeRefresh.swipeRefreshLayout)
    dependencies.implementation(Libraries.AndroidX.ConstraintLayout.constraintLayout)

    dependencies.implementation(Libraries.CashApp.Contour.contour)

    dependencies.implementation(Libraries.Google.Material.material)

    dependencies.implementation(project(Modules.App.Navigation.path()))
    dependencies.implementation(project(Modules.App.Core.path()))
    dependencies.implementation(project(Modules.App.Domain.path()))
    dependencies.implementation(project(Modules.App.Data.path()))
}

private fun Project.applyAppModuleDependencies() {
    Modules.Feature.values().forEach { module ->
        println("Adding runtimeOnly dependency ${module.path()} -> ${project.path}")
        dependencies.runtime(project(module.path()))
    }

    Modules.App.values().forEach { module ->
        if (module != Modules.App.Main) {
            println("Adding base module dependency ${module.path()} -> ${project.path}")
            dependencies.implementation(project(module.path()))
        }
    }

    dependencies.implementation(Libraries.Google.Material.material)

    dependencies.implementation(Libraries.AndroidX.Core.coreKtx)
    dependencies.implementation(Libraries.AndroidX.Work.runtimeKtx)
    dependencies.implementation(Libraries.AndroidX.Activity.activityKtx)
    dependencies.implementation(Libraries.AndroidX.Fragment.fragmentKtx)
    dependencies.implementation(Libraries.AndroidX.StartUp.startUpRuntime)
    dependencies.implementation(Libraries.AndroidX.ConstraintLayout.constraintLayout)
}

private fun Project.applyAppModuleGroupDependencies() {
    println("Applying base module dependencies for module -> $path")
    when (name) {
        Modules.App.Core.id -> {
            dependencies.implementation(project(Modules.App.Navigation.path()))
            dependencies.implementation(project(Modules.App.Domain.path()))
            dependencies.implementation(project(Modules.App.Data.path()))

            dependencies.implementation(Libraries.Google.Material.material)

            dependencies.implementation(Libraries.AndroidX.Core.coreKtx)
            dependencies.implementation(Libraries.AndroidX.Work.runtimeKtx)
            dependencies.implementation(Libraries.AndroidX.Activity.activityKtx)
            dependencies.implementation(Libraries.AndroidX.Fragment.fragmentKtx)
            dependencies.implementation(Libraries.AndroidX.StartUp.startUpRuntime)
            dependencies.implementation(Libraries.AndroidX.SwipeRefresh.swipeRefreshLayout)
            dependencies.implementation(Libraries.AndroidX.ConstraintLayout.constraintLayout)

            dependencies.implementation(Libraries.AndroidX.Collection.collectionKtx)
            dependencies.implementation(Libraries.AndroidX.Recycler.recyclerView)
        }
        Modules.App.Data.id -> {
            dependencies.implementation(project(Modules.App.Domain.path()))

            dependencies.implementation(Libraries.AndroidX.Room.runtime)
            dependencies.implementation(Libraries.AndroidX.Room.ktx)
            dependencies.kapt(Libraries.AndroidX.Room.compiler)

            dependencies.implementation(Libraries.Square.OkHttp.logging)
            dependencies.implementation(Libraries.Square.Retrofit.retrofit)
            dependencies.implementation(Libraries.Square.Retrofit.gsonConverter)
            dependencies.implementation(Libraries.retrofitSerializer)

            dependencies.debugImplementation(Libraries.Chuncker.debug)
            dependencies.releaseImplementation(Libraries.Chuncker.release)

            dependencies.androidTest(Libraries.AndroidX.Room.test)
            dependencies.androidTest(Libraries.Square.OkHttp.mockServer)
        }
        Modules.App.Navigation.id -> {
            dependencies.implementation(Libraries.AndroidX.Core.coreKtx)
            dependencies.implementation(Libraries.AndroidX.Activity.activityKtx)
            dependencies.implementation(Libraries.AndroidX.Collection.collectionKtx)
            dependencies.implementation(Libraries.AndroidX.Fragment.fragment)
        }
    }
}

internal fun Project.configureDependencies() {
    dependencies.implementation(
        fileTree("libs") {
            include("*.jar")
        }
    )
    DependencyStrategy(project).applyDependenciesOn(dependencies)

    if (isAppModule()) applyAppModuleDependencies()
    if (matchesAppModule()) applyAppModuleGroupDependencies()
    if (matchesFeatureModule()) applyFeatureModuleGroupDependencies()
}