# Android Project Template
This repository serves as a starting point for Android development, providing a fully pre-configured template project. It includes essential libraries and tools to streamline your development process, ensuring you can focus on building features without spending time on setting up dependencies.

### Features
This project template includes the following dependencies and tools:

- **Jetpack Compose**: Modern toolkit for building native Android UI.
- **Dagger Hilt**: Dependency injection library that simplifies DI in Android.
- **Retrofit**: Type-safe HTTP client for making API calls.
- **Room**: SQLite database with direct access to the underlying platform database.
- **Kotlin Coroutines**: Lightweight concurrency support for managing background tasks.
- **LiveData & Flows**: Lifecycle-aware data holders and reactive streams for UI updates.
- **Glide**: Image loading and caching library for Android.

# Changing the Package Structure of an Existing Project

Changing the package structure of an existing project can be a bit involved, but here’s a step-by-step guide to help you through the process. The steps may vary slightly depending on your development environment and tools, but the general principles are similar.

## For Android Projects in Android Studio

### Refactor Package Names:
1. **Open Your Project**: Open your project in Android Studio.
2. **Navigate to the Package**: In the Project view, locate the package you want to rename.
3. **Right-Click and Refactor**: Right-click on the package name in the `src` directory. Select `Refactor -> Rename`.
4. **Rename the Package**: A dialog will appear where you can enter the new package name. Android Studio will update the package name in all relevant files. Confirm the changes.

### Update `AndroidManifest.xml`:
1. **Open the Manifest File**: Navigate to `src/main/AndroidManifest.xml`.
2. **Update the Package Name**: Make sure to update the `package` attribute in the `<manifest>` tag to match the new package name.

### Update Build Configuration:
1. **Open `build.gradle`**: Check the `build.gradle` file(s) for any references to the old package name, such as in `applicationId`.
2. **Update `applicationId`**: Make sure `applicationId` in the `defaultConfig` block matches the new package name.

### Update Imports:
1. **Search and Replace**: Use Android Studio's search and replace functionality (`Find in Path`) to update any remaining references to the old package name throughout your codebase.

### Clean and Rebuild:
1. **Clean the Project**: Go to `Build -> Clean Project`.
2. **Rebuild the Project**: Go to `Build -> Rebuild Project` to ensure that all changes are applied correctly.

