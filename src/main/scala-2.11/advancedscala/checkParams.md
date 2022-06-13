***CheckParams***

1. It is  a dev functionality, not advisable to be used in production jobs.
2. We want to test the check with different thresholds, locales, regions, then running the check with  these different values requires modifying the PR, slim publish the PR and then running the job.
3. Not anymore, we can inject the params in the enigne and they will be available to the checks to consume.


**Use Cases**
1. Want to run check with different locales (excluded/included) then the ones in check metadata.
2. Run check with different regions(excluded/included) then the onces in metadata.
3. Run check with different tolerances (angle, distance, generic)
4. Excluded feature from being validated 

**Syntax**
1. add ```checkParams``` as ```input_source_params``` in the spectrum job. 
```
 {"5186498217798370552": {
                    "excludedFeatures": [
                        "432384126368920999",
                        "432384126382717161"
                    ],
                    "includedLocale": [
                        "NZ"
                    ],
                    "distanceTolerance": 13.01,
                    "angleTolerance": 180.01,
                    "myGenericKey":["value1","value2","value3"]
                }}
``` 

If we need to run the job with multiple checks, it will be quite cumbersome to create this Json, so we can just use the key ```all``` instead of the checkId
```
"all": {
                    "excludedLocale": [
                        "NZ"
                    ]
                }
```
This will exclude all ```NZ``` from all the checks.
If we have same key in checkId as well as ```all``` then the key in checkId will be given preference. If we have a key in ```all``` then it will be applied to all the checks running in the job.
```
{
  "5186498217798370552": {
    "excludedFeatures": [
      "432384126368920999",
      "432384126382717161"
    ],
    "includedLocale": [
      "NZ"
    ],
    "distanceTolerance": 13.01,
    "angleTolerance": 180.01,
    "myGenericKey": [
      "value1",
      "value2",
      "value3"
    ]
  },
  "all": {
    "excludedLocale": [
      "NZ"
    ]
  }
}
``` 
For keys in set {distanceTolerance, angleTolerance} we dont need to do anything on the checks side, Checks can directly consume this via methods like 
```
    getDistanceTolerance()
    getAngleTolerance()
```

For keys in set {includedLocale, excludedLocale, excludedFeatures, excludedRegion, includedRegion} , we dont need to do anything as the values in these fields will be matched in the superclass.

If we decide to use generic key then we need to specify the type of value in the check for eg 
```
 override def assert(): Unit = {
      val genericValue = getGenericKey[Double]("myGenericKey")
    }
```
and in the spectrum param this will be passed on as
```
{
  "myCheckId": {
    "myGenericKey":23.90
  }
}
```

