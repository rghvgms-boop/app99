# Photo Widget App

An Android application that provides a home screen photo widget that fetches and displays an image from a fixed URL, refreshing every hour.

## Features
- **Automatic Refresh**: The widget image refreshes every 60 minutes in the background.
- **Manual Refresh**: A refresh button on the widget allows for immediate updates.
- **Offline Support**: Uses Coil for efficient image loading and caching.
- **Multiple Sizes**: Supports resizing on the home screen.
- **Background Work**: Uses Android WorkManager for reliable background updates.

## Technical Details
- **Language**: Kotlin
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/)
- **Background Tasks**: [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager)
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

## How to Build and Install

### Prerequisites
- [Android Studio](https://developer.android.com/studio) (Hedgehog or newer recommended)
- Android SDK 34 installed

### Steps to Build
1. **Open the Project**: Launch Android Studio and select "Open" to open the `PhotoWidgetApp` folder.
2. **Sync Gradle**: Android Studio will automatically sync the Gradle files. Ensure you have an active internet connection.
3. **Build APK**:
   - Go to `Build` > `Build Bundle(s) / APK(s)` > `Build APK(s)`.
   - Once the build is complete, a notification will appear. Click "locate" to find the `app-debug.apk` file.

### Steps to Install
1. **Transfer to Device**: Copy the generated `app-debug.apk` to your Android device.
2. **Enable Unknown Sources**: If prompted, allow installation from unknown sources in your device settings.
3. **Install**: Open the APK file on your device and follow the installation prompts.

### How to Use the Widget
1. Go to your Android home screen.
2. Long-press on an empty space.
3. Select **Widgets**.
4. Find **Photo Widget** in the list.
5. Drag and drop the widget onto your home screen.
6. The widget will automatically fetch the image from the URL.
7. You can resize the widget to small, medium, or large sizes.
8. Tap the refresh icon in the bottom-right corner of the widget for a manual update.

## Project Structure
- `app/src/main/java/com/example/photowidget/widget/PhotoWidgetProvider.kt`: Handles widget lifecycle and scheduling updates.
- `app/src/main/java/com/example/photowidget/widget/PhotoWidgetWorker.kt`: Background worker that fetches the image and updates the widget.
- `app/src/main/res/layout/photo_widget_layout.xml`: The visual layout of the widget.
- `app/src/main/AndroidManifest.xml`: App configuration and permissions.
