<#import "template.ftl" as layout>
<@layout.registrationLayout displayMessage=false; section>
    <#if section = "header">
        ${msg("backup_email")}
    <#elseif section = "form">
        <p id="kc-backup-email-text">
            ${msg("backup_email_message")}
        </p>
        <form class="${properties.kcFormClass!}" action="${url.loginAction}" method="POST">
            <div class="${properties.kcFormGroupClass!}">
                <div class="${properties.kcLabelWrapperClass!}">
                    <label for="email" class="${properties.kcLabelClass!}">
                        ${msg("email")}
                    </label>
                </div>
                <div class="${properties.kcInputWrapperClass!}">
                    <input 
                        id="email" 
                        type="text" 
                        name="email" 
                        value="${backupEmail!}"
                        placeholder="${msg("email")}"
                        class="${properties.kcInputClass!}"
                        autofocus 
                        aria-invalid="<#if messagesPerField.existsError('email')>true</#if>"
                    />
                    <#if messagesPerField.existsError('email')>
                        <span id="input-error" class="${properties.kcInputErrorMessageClass!}" aria-live="polite">
                            ${kcSanitize(messagesPerField.getFirstError('email'))?no_esc}
                        </span>
                    </#if>
                </div>
            </div>

            <div class="${properties.kcFormGroupClass!}">
                <div id="kc-form-buttons" class="${properties.kcFormButtonsClass!}">
                    <button 
                        class="${properties.kcButtonClass!} ${properties.kcButtonPrimaryClass!} ${properties.kcButtonBlockClass!} ${properties.kcButtonLargeClass!}" 
                        type="submit" 
                        name="accept"
                    >
                        ${msg("doSubmit")}
                    </button>
                </div>
            </div>
        </form>
    </#if>
</@layout.registrationLayout>