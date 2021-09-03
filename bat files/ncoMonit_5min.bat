D:

cd D:\Murali\NCO-Monitoring

mvn clean test -Dsurefire.suiteXmlFiles=AllRegionsSmokeTest.xml

taskkill /F /IM chrome.exe /T 
taskkill /F /IM cmd.exe /T

