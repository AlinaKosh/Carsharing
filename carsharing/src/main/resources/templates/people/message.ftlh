<#import "blocks/footerHeader.ftlh" as fh>
<@fh.footerHeader user, title>
    <html>
    <head>
        <meta charset="utf-8">
        <title>Регистрация пользователя</title>
        <link rel="stylesheet" href="/static/styles/Message.css">
    </head>
<body>
    ${message}
    <h1><a href = "/">На главную</a></h1>
    </body>
    </html>
</@fh.footerHeader>