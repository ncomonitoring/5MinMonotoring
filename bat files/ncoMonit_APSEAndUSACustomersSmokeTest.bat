D:

::cd D:\Murali\NCO-Monitoring
cd D:\Git-NcoMonitoring\5MinMonotoring

mvn test -Dsurefire.suiteXmlFiles=NS_APSEAndUSACustomerSmokeTest.xml

taskkill /F /IM chrome.exe /T 
taskkill /F /IM cmd.exe /T

