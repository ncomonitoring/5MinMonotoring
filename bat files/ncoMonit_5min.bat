D:

::cd D:\Murali\NCO-Monitoring
cd D:\Git-NcoMonitoring\5MinMonotoring

mvn clean test -Dsurefire.suiteXmlFiles=AllRegionsSmokeTest.xml

taskkill /F /IM chrome.exe /T 
taskkill /F /IM cmd.exe /T

