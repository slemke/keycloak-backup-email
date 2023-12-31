package com.github.slemke;

import org.keycloak.authentication.RequiredActionContext;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.models.UserModel;

import javax.ws.rs.core.Response;

public class BackupEmailRequiredAction implements RequiredActionProvider {

    private final String ID = "backup-email-required-action";

    @Override
    public void evaluateTriggers(RequiredActionContext context) {
        UserModel user = context.getUser();
        final String backupEmail = user.getFirstAttribute("backupEmail");
        if (backupEmail == null || backupEmail.isBlank()) {
            user.addRequiredAction(ID);
        }
    }

    @Override
    public void requiredActionChallenge(RequiredActionContext context) {
        Response challenge = context.form().createForm("backup_email.ftl");
        context.challenge(challenge);
    }

    @Override
    public void processAction(RequiredActionContext context) {
        if (context.getHttpRequest().getDecodedFormParameters().containsKey("submit"))
    }

    @Override
    public void close() {

    }
}
