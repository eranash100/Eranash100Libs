# Eranash100Libs
This is my library


How to implement the library?

STEP 1: Go to your project gradle ("build.gradle(<ProjectName>)")
    
1.2: Add the line below to the "allprojects" section -> "repositories" section


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

STEP 2: Go to your app gradle ("build.gradle(app)")

2.2: Add the line below to "dependencies" section

    implementation 'com.github.eranash100:Eranash100Libs:1.0.0'
    
And the implementation done!
