<#macro footerHeader user, title>
    <html>
    <head>
        <title>${title}</title>
        <link href="/static/styles/UserCabinetCss.css" rel="stylesheet">
    </head>
    <body>
    <div class="header">
        <header class="headerUserPage">
            <img src="/static/images/Logo.jpg" style="width:240px; height:70px">
            <#if user?? && user.avatar??>
                <img onclick = "account()" class = "avatar" src = "/image/${user.avatar.id}">
            <#else >
                <img class = "avatar" src="/static/images/userLogo.png">
            </#if>
        </header>
    </div>

    <#nested />
    <div class = "footer">

    </div>
    <script>
        function account(){
            document.location.replace("/people/account")
        }
    </script>
    </body>
    </html>
</#macro>