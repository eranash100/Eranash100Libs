# Eranash100Libs
This is my library


How to implement the library?

STEP 1:
Add the line below to your app gradle ("build.gradle(app)")

    implementation 'com.github.eranash100:Eranash100Libs:1.0.0'
    
STEP 2:
Add the line below to the "repositories" in "allprojects" in your project gradle ("build.gradle(<ProjectName>)")

    maven { url "https://jitpack.io" }

Its should look like this:

    allprojects 
    {
        repositories 
        {
            google()
            jcenter()
            maven { url "https://jitpack.io" }
        }
    }
