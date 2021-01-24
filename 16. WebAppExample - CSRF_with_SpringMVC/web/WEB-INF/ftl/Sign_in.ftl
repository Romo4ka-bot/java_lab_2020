<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form method="post" action="/sign_in">
    <input type="text" name="login" placeholder="login">
    <input type="password" name="password" placeholder="password">
    <input type="hidden" name="_csrf_token" value="${_csrf_token}">
    <button type="submit">Sign in</button>
</form>
</body>
</html>