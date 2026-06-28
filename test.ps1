$classpath = ./gradlew -q :masjidku-reporting:dependencies --configuration compileClasspath | Select-String "jasperreports-"
Write-Output $classpath
