VERITASFINANCE FRONTEND NAVIGATION FIX

This package adds a single shared top bar and drawer navigation. The uploaded analytics.html did not contain the old blue banner or the plain text navigation links visible in the screenshot; those elements were therefore coming from an older deployed webapp copy. A clean Tomcat redeploy is required.

1. Restore your LOCAL MySQL credentials in src/main/resources/config.properties. Do not commit that file with real credentials.
2. From this project folder run: mvn clean package
3. Stop Tomcat with Ctrl+C.
4. Delete the expanded old app folder and old WAR from Tomcat webapps:
   rmdir /s /q "C:\Users\dell\Downloads\apache-tomcat-10.1.57-windows-x64\apache-tomcat-10.1.57\webapps\VeritasFinance"
   del /q "C:\Users\dell\Downloads\apache-tomcat-10.1.57-windows-x64\apache-tomcat-10.1.57\webapps\VeritasFinance.war"
5. Copy target\VeritasFinance.war to Tomcat webapps\VeritasFinance.war.
6. Start Tomcat and hard-refresh the browser with Ctrl+F5.

The ZIP contains placeholder DB credentials. Maven/browser verification must be performed on the local machine with the user's JDK, Maven, MySQL and Tomcat setup.
