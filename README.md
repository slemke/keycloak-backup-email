# Keycloak Backup Email Required Action

This extension adds a required action to force the user to setup 
a backup email. The email is stored as a user attribute and can 
be mapped with a user attribute token mapper into the user info 
endpoint or tokens.

**Features**

* require user to setup backup email
* support for english and german localization
* storing backup email as user attribute
* force an backup email update by assigning the required action to a user via the admin console

## Screenshots

### Backup Email Form
![backup email form](./screenshots/form.png)

### Backup Email Error (inpit missing)
![backup email form missing input](./screenshots/form-missing-email.png)

### Backup Email Error (input invalid)
![backup email form invalid email](./screenshots/form-invalid-email.png)

### User Attribute in Admin Console
![backup email admin console](./screenshots/admin-console.png) 

## Installation

* Download the latest release from the release page
* Move the jar to `/opt/keycloak/providers`
* Make sure it is loaded correctly by checking the provider info in the admin-console
* Enable the required action `Backup Email` for your realm 