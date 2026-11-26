VERITASFINANCE - UPDATED AUTHENTICATION UI
===========================================

This project is a Maven WAR application for Java 21, Jakarta Servlet 6 and Tomcat 10.1.

WHAT WAS UPDATED
- Redesigned registration and login pages with a responsive, finance-themed layout.
- Added accessible labels, mobile layouts, password show/hide, client-side validation,
  clear registration/login feedback, and matching branding.
- Registration validates input on the server and checks for an existing email.
- Passwords continue to be stored using BCrypt (never plain text).
- Login creates a fresh session after successful authentication.
- MySQL JDBC setup automatically enables allowPublicKeyRetrieval if not already present.
- Real database credentials are not included in this ZIP.

SET UP MYSQL
1. Open MySQL Shell / Workbench.
2. Run database/veritasfinance_users.sql.
   If you already have a users table, do not drop it. Check that it has these columns:
   user_id, full_name, email, phone, password, language, voice_enabled.
   The email column should be unique.
3. Copy src/main/resources/config.properties.example to
   src/main/resources/config.properties.
4. Edit config.properties and enter your own MySQL username and password.
   Do not upload config.properties to GitHub.

BUILD ON WINDOWS
Open CMD in the project folder (the folder containing pom.xml) and run:

    mvn clean package

The output WAR will be:
    target\VeritasFinance.war

DEPLOY TO TOMCAT 10.1
1. Stop Tomcat.
2. Copy target\VeritasFinance.war into Tomcat's webapps folder.
3. Remove the old expanded webapps\VeritasFinance folder if it exists.
4. Start Tomcat.
5. Open:
   http://localhost:8082/VeritasFinance/

If your Tomcat uses a different port, replace 8082 with your configured port.

GITHUB SAFETY
- Keep src/main/resources/config.properties out of GitHub (it is in .gitignore).
- Commit config.properties.example, not config.properties.
- Never commit database passwords, API keys, or private credentials.

IMPORTANT
The project ZIP does not contain your local MySQL password. You must configure it locally.
This build has been source-reviewed, but it could not be Maven-compiled in this environment
because Maven is not installed here. Run mvn clean package on your Windows machine before
deploying. If Maven reports a compile error, share the complete error output.
