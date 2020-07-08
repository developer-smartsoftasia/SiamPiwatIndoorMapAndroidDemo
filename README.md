# Demo : Siam Piwat Indoor Map Library for Android

## Setup
### Gradle
1. Open 'build.gradle' file of project
2. Add maven url of Indoor Map SDK as below
```
allprojects { 
  repositories { 
    google() 
    jcenter() 
    maven { url 'https://dl.bintray.com/ssa/com.siampiwat' } 
  } 
}

```
3. Open 'build.gradle' file of 'app' module
4. Add dependency of Indoor SDK as below
```
dependencies {
  ...
  implementation 'com.siampiwat:indoormapsdk:0.1.0'
}

```

### Kotlin
5. Create IndoorMap class for Indoor Map SDK initialization
```
class IndoorMap : Application() { 
  override fun onCreate() { 
    super.onCreate() 
    IndoorMapSDK.init(this) 
  } 
}

```

### Android Manifest
6. Add Internet permission
```
<uses-permission android:name="android.permission.INTERNET" />
```
7. Setup the android:name in <application> to refer to application subclass; IndoorMap (5) and metadata with below data table
```
<manifest package="com.smartsoftasia.library.indoorsdktest”
  xmlns:android="http://schemas.android.com/apk/res/android">
  ...
  <application
    android:name=".IndoorMap"
  ...
  >
  </application>
</manifest>
```
                             
## How to use
### (1) SDK Initialization
#### - initialize()
This function for selecting the department store type and importing the json string of stores

##### Required Parameters
| Name | Type | Description |
| ---- | ---- | ------ |
| swpDepartmentStoreType | String | The type of SWP department store |
| stores | String | The json string of stores |

##### Code
```
IndoorMapSDK.initialize(swpDepartmentStoreType, stores);
```

##### Example
```
var jsonString = /* json string of stores */
IndoorMapSDK.initialize(SWPDepartmentStoreType.SIAM_PARAGON, jsonString)
```

#### - getVenue()
This function for downloading the map of specified department store and mixing store data between stores from SDK and json string which come from initialize()

##### Required Parameters
| Name | Type | Description |
| ---- | ---- | ------ |
| - | - | - |

##### Code
```
IndoorMapSDK.getInstance().getVenue(callback);
```

##### Example
```
IndoorMapSDK.getInstance().getVenue(object : IndoorMapSDK.GetVenueCallback {
    override fun onSuccess() { 
        Log.i(TAG, "onSuccess") 
    } 
    override fun onFailed(e: Throwable) { 
        Log.i(TAG, "onFailed") 
    } 
})
```

#### - getStoreById()
This function for getting the store by specified id

##### Required Parameters
| Name | Type | Description |
| ---- | ---- | ------ |
| id | String | The id of specified store |

##### Code
```
IndoorMapSDK.getInstance().getStoreById(id)
```

##### Example
```
val stores = IndoorMapSDK.getInstance().getStoreById(“1234567890”)
```

#### - getStores()
This function for getting the stores of SDK

##### Required Parameters
| Name | Type | Description |
| ---- | ---- | ------ |
| - | - | - |

##### Code
```
IndoorMapSDK.getInstance().getStores()
```

##### Example
```
var stores = IndoorMapSDK.getInstance().getStores()
```

#### - setOriginByLocation()
This function for setting up the origin by location

##### Required Parameters
| Name | Type | Description |
| ---- | ---- | ------ |
| location | SPWAISLocation | The SPWAISLocation object |

##### Code
```
IndoorMapSDK.getInstance().setOriginByLocation(location)
```

##### Example
```
// Initialize the location
val location = SPWAISLocation(
    isIndoor = true, 
    latitude = 13.746600, 
    longitude = 100.534307, 
    buildId = "4409", 
    buildName = "Siam Paragon", 
    floorId = "8288", 
    floorNumber = "0" )
// Set the origin by location
IndoorMapSDK.getInstance().setOriginByLocation(location)
```

#### - setOriginByStore()
This function for setting up the origin by store

##### Required Parameters
| Name | Type | Description |
| ---- | ---- | ------ |
| originStore | SWPStore | The SWPStore object |

##### Code
```
IndoorMapSDK.getInstance().setOriginByStore(originStore)
```

##### Example
```
// Get the stores from Indoor Map SDK
val stores = IndoorMapSDK.getInstance().getStores()
// Set the origin by store
IndoorMapSDK.getInstance().setOriginByStore(stores[0])
```

#### - setDestinationByStore()
This function for setting up the destination by store

##### Required Parameters
| Name | Type | Description |
| ---- | ---- | ------ |
| destinationStore | SWPStore | The SWPStore object |

##### Code
```
IndoorMapSDK.getInstance().setDestinationByStore(destinationStore)
```

##### Example
```
// Get the stores from Indoor Map SDK
val stores = IndoorMapSDK.getInstance().getStores()
// Set the destination by store
IndoorMapSDK.getInstance().setDestinationByStore(stores[1])
```

#### - setUserLocation()
This function for setting up the user’s location on the indoor map and should be called in the class which inherits from IndoorMapActivity

##### Required Parameters
| Name | Type | Description |
| ---- | ---- | ------ |
| spwAISLocation | SPWAISLocation | The SPWAISLocation object |

##### Code
```
IndoorMapSDK.getInstance().setUserLocation(spwAISLocation)
```

##### Example
```
IndoorMapSDK.getInstance().setUserLocation(spwAISLocation)
```

#### - getClosestStore()
This function for getting the closest store by input location and should be called in the class which inherits from IndoorMapActivity

##### Required Parameters
| Name | Type | Description |
| ---- | ---- | ------ |
| spwAISLocation | SPWAISLocation | The SPWAISLocation object |

##### Code
```
IndoorMapSDK.getInstance().getClosestStore(spwAISLocation)
```

##### Example
```
var store = IndoorMapSDK.getInstance().getClosestStore(spwAISLocation)
```

### (2) View
#### - Indoor Map
To display the indoor map, create the activity class which inherits IndoorMapActivity. This activity must be call after the call the initialize() and getVenue() methods of SDK

#### Code
```
class MyIndoorMapActivity : IndoorMapActivity() { 
    override fun onIndoorMapError(exception: Exception) { 
        Log.i(TAG, "onIndoorMapError”) 
    } 
}
```
