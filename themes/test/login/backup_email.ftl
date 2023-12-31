<#import "template.ftl" as layout>
<@layout.registrationLayout displayMessage=false; section>
    <#if section = "header">
        ${msg("Backup Email")}
    <#elseif section = "form">
        <div id="kc-terms-text">
            Please provide a backup e-mail.
        </div>
        <form class="form-actions" action="${url.loginAction}" method="POST">
            <label for="email">E-Mail</label>
            <input
                class="${properties.kcButtonClass!} ${properties.kcButtonPrimaryClass!} ${properties.kcButtonLargeClass!}"
                name="email"
                id="kc-accept"
                type="submit"
                value="${msg("doAccept")}"
            />
            <button
                class="${properties.kcButtonClass!} ${properties.kcButtonDefaultClass!} ${properties.kcButtonLargeClass!}"
                type="submit"
            >
                ${msg("doDecline")}
            </button>
        </form>
    </#if>
</@layout.registrationLayout>