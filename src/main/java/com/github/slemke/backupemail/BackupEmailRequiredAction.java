package com.github.slemke.backupemail;

import org.keycloak.authentication.RequiredActionContext;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.models.UserModel;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

public class BackupEmailRequiredAction implements RequiredActionProvider {

    public static final String PROVIDER_ID = "backup-email-required-action";

    private static final String TEMPLATE = "backup_email.ftl";

    private static final String USER_ATTRIBUTE_KEY = "backupEmail";

    private static final String FORM_PARAMETER_KEY = "email";

    @Override
    public void evaluateTriggers(RequiredActionContext context) {
        UserModel user = context.getUser();
        final String backupEmail = user.getFirstAttribute(USER_ATTRIBUTE_KEY);
        if (backupEmail == null || backupEmail.isBlank()) {
            user.addRequiredAction(PROVIDER_ID);
        }
    }

    @Override
    public void requiredActionChallenge(RequiredActionContext context) {
        Response challenge = context.form().createForm(TEMPLATE);
        context.challenge(challenge);
    }

    @Override
    public void processAction(RequiredActionContext context) {
        MultivaluedMap<String, String> decodedFormParameters = context.getHttpRequest().getDecodedFormParameters();
        if (!decodedFormParameters.containsKey(FORM_PARAMETER_KEY)) {
            context.failure();
            return;
        }
        String email = decodedFormParameters.getFirst(FORM_PARAMETER_KEY);
        context.getUser().setSingleAttribute(USER_ATTRIBUTE_KEY, email);
        context.success();
    }

    @Override
    public void close() { }
}
