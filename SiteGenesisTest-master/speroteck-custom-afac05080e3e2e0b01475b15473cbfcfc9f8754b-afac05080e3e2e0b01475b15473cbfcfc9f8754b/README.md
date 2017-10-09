Application Support Test Framework (automation testing for SFCC)
====================================================

Features:
--------
Coming soon...


Install
-------

1. Java SDK
2. Install WebDriver and add it to env. variable PATH
  * ex.: Chromedriver from link: https://sites.google.com/a/chromium.org/chromedriver/downloads
3. Maven from https://maven.apache.org/install.html
4. Execute command from Project folder:  "mvn clean install"

Run tests
---------
Execute command from Project folder:  "mvn clean test" 

#### **Available Command Line parameters:**
* -Dbrowser specifies the browser that will be lunched to execute tests.  
    Values:  chrome(default) | firefox | ie | htmlunit
    Note: -DenableJS should be added to enable JS for HTML Unit Driver
* -DbaseUrl  specifies target site for testing ex.: *-DbaseUrl=http://mysite.com* 
* -DsslEnabled  specifies usage of "https://" for current site
yes  (if omitted or any other value is specified - "no" will be applied) 
* -DlogLevel  specifies the level of logging and the amount of messages that will appear in console
 Values:  info(default) | error | warn (Case Insensetive) 
* -DsauceLabsSession    Runs test in SauceLabs remote browser.
Syntax is the following: "OS\*version*browser" 
Please note: all whitespaces are collapsed ex: OS X => OSX  
see details on: [wiki.saucelabs.com](https://wiki.saucelabs.com/display/DOCS/Test+Configuration+Options) also [configurator to get desired version](https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/) 

FAQ:
---
* Add GRADLE_HOME/bin to your PATH environment variable.
* Note that it's not currently possible to set JVM options for Gradle on the command line.
* **Run from IDE:** JUnit runner, set "VM Options": -ea -DbaseUrl=http://lea-mage12.lcgosc.com/ -Dbrowser=CHROME Then right click on test class/method >> run or debug
* **SauceLabs: authentication** to run tests in SauceLabs service using REST API: 
    * add DSAUCE_USER_NAME=<user> and DSAUCE_API_KEY=<api_key> to command line with appropriate values
    *  or create a ~/.sauce-ondemand properties-file with "username" and "key" properties

Developer's Guide':
---
**UI elements Naming Convention (Constants/Variables):**
* link: LINK_%DISPLAYED_NAME% ex: **LINK_PRIVACY_POLICY**
* button(all which looks like buttons and is CTA): BUTTON_%BUTTONS'S LABEL'% ex: **BUTTON_LOGIN**, **BUTTON_SEARCH**
* input field: FIELD_%FORM-NAME%_%DISPLAYED-NAME% ex: **FIELD_SEARCH**, **FIELD_LOGIN_FORM_FIRST_NAME**
* dropdown/select: DROPDOWN_%FORM-NAME%_%DISPLAYED_NAME% ex: **DROPDOWN_LOCALES**, **DROPDOWN_BILLING_ADDRESS_STATE**