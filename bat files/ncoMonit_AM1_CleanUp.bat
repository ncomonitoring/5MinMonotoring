D:

cd D:\Murali\NCO-Monitoring

mvn test -Dsurefire.suiteXmlFiles=NS_AM1_CleanUp.xml

taskkill /F /IM chrome.exe /T 
taskkill /F /IM cmd.exe /T

