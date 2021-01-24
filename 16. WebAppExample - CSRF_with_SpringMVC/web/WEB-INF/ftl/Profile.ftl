<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>profile</title>
</head>
<body>
<form method="post" action="/profile">
    <input type="hidden" name="_csrf_token" value="${_csrf_token}">
    <button type="submit">delete USER</button>
</form>
<div style="color: red; size: 20px">${_csrf_token}</div>
</body>
</html>