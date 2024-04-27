package com.github.slemke.backupemail;

import org.keycloak.Config;
import org.keycloak.authentication.RequiredActionFactory;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class BackupEmailRequiredActionFactory implements RequiredActionFactory {

    private static final BackupEmailRequiredAction requiredAction = new BackupEmailRequiredAction();

    @Override
    public String getDisplayText() {
        return "Backup Email";
    }

    @Override
    public RequiredActionProvider create(KeycloakSession session) {
        return requiredAction;
    }

    @Override
    public void init(Config.Scope config) { }

    @Override
    public void postInit(KeycloakSessionFactory factory) { }

    @Override
    public void close() { }

    @Override
    public String getId() {
        return BackupEmailRequiredAction.PROVIDER_ID;
    }
}
