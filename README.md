<h1>Loading-Animation-Composable</h1>

<h3>Dependency</h3>

```groovy

// Add the jitpack.io repository
repositories {
    maven { url 'https://jitpack.io' }
}

// Add the dependency
dependencies {
    implementation 'com.github.willor777:loading-animation-composable:<Current-Version>'
}
```

Current Version...
[![](https://jitpack.io/v/willor777/loading-animation-composable.svg)](https://jitpack.io/#willor777/loading-animation-composable)


<h3>Usage</h3>
1. Download a Lottie Json Animation from https://lottiefiles.com/
2. Create a 'raw' folder in your project's resources directory and place the Lottie Json there.
3. Loading the Json is done using the 'resources' field of an Activity's Context...

```kotlin

// This function is part of the library. Just give it a reference to the
// Context.resources along with the R.raw.fileName
val lottieJson: String = loadLottieFile(resources, R.raw.yourLottieJson)

```

4. Set up the animation. Here is an example of using the Composable.
Just give it the lottieJson you loaded, then set it's condition check (Note the condition
check should be false initially. The animation will stop looping whenever it evaluates to true.).
You can also provide a: Modifier, Speed-of-animation, Max-Loop-Time in MS, and callbacks for
when the condition evaluates to true, or for when the maxTime is hit.

```kotlin

var counter = 0

LoadingAnimation(
    modifier = Modifier.fillMaxSize(),
    lottieJson = lottieJsonFromRawRes,
    maxLoopTime = 20000,
    onMaxTime = {
        // This will be called if maxLoopTime is hit
    },
    onConditionTrue = {
        // This will be called if the condition evaluates true before maxLoopTime
    },
    condition = {
        // The condition is checked every 500ms
        counter += 1
        
        // When this evaluates to true, the animation will stop
        counter > 10
    }
)
```