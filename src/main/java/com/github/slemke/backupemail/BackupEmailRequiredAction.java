package com.github.slemke.backupemail;

import org.keycloak.authentication.RequiredActionContext;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.models.UserModel;
import org.keycloak.models.utils.FormMessage;
import org.keycloak.services.validation.Validation;

import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;

public class BackupEmailRequiredAction implements RequiredActionProvider {

    public static final String PROVIDER_ID = "backup-email-required-action";

    private static final String TEMPLATE = "backup_email.ftl";

    private static final String USER_ATTRIBUTE_KEY = "backupEmail";

    private static final String FORM_PARAMETER_KEY = "email";

    private static final String INVALID_EMAIL_MESSAGE_KEY = "invalidEmailMessage";

    private static final String MISSING_EMAIL_MESSAGE_KEY = "missingEmailMessage";

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
        Response response = context.form().createForm(TEMPLATE);
        context.challenge(response);
    }

    @Override
    public void processAction(RequiredActionContext context) {
        MultivaluedMap<String, String> decodedFormParameters = context.getHttpRequest().getDecodedFormParameters();
        String email = decodedFormParameters.getFirst(FORM_PARAMETER_KEY);
        if (Validation.isBlank(email)) {
            challengeWithError(context, email, MISSING_EMAIL_MESSAGE_KEY);
            return;
        }
        if (!Validation.isEmailValid(email)) {
            challengeWithError(context, email, INVALID_EMAIL_MESSAGE_KEY);
            return;
        }
        context.getUser().setSingleAttribute(USER_ATTRIBUTE_KEY, email);
        context.success();
    }

    private void challengeWithError(RequiredActionContext context, String email, String messageKey) {
        Response response = context.form()
            .addError(new FormMessage(FORM_PARAMETER_KEY, messageKey))
            .setAttribute(USER_ATTRIBUTE_KEY, email)
            .createForm(TEMPLATE);
        context.challenge(response);
    }

    @Override
    public void close() { }
}
