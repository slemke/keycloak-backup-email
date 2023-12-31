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
            <input id="email" type="text" name="email" placeholder="email" />
            <button class="${properties.kcButtonClass!} ${properties.kcButtonDefaultClass!} ${properties.kcButtonLargeClass!}" type="submit" name="accept">
                ${msg("doSubmit")}
            </button>
        </form>
    </#if>
</@layout.registrationLayout>