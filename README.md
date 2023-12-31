# Keycloak Backup Email SPI

This Keycloak SPI adds the ability for your users to add a backup email address via the account console.
Also, when configured it is possible to require users to add a backup email address via a custom required action.
The backup email address is stored as a user attribute so it can also read from the keycloak admin REST API.