<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../static/css/login.css">
    <title>Login</title>
</head>
<body>
<div class="wrapper">
    <div class="page">
        <div class="page__image">
            <img src="../../static/img/space-shuttle.jpg">
        </div>
        <div class="page__login">
            <div class="login_container">
                <div class="login__welcome">
                    <div class="login__welcome__text">Welcome</div>
                </div>

                <div class="login__form">
<#--                    <#if errMessage??><span style="color: red">${errMessage}</span></#if>-->
                    <div id="error_text" class="error_text">The username or password you entered is incorrect</div>
                    <form id="form" action="/login" method="post">

                        <!--Username input-->
                        <div class="form__input">
                            <div class="input__icon">
                                <div class="white_icon">
                                    <img src="../../static/img/user.png">
                                </div>
                                <div class="yellow_icon">
                                    <img src="../../static/img/user-active.png">
                                </div>
                            </div>
                            <div class="input__div">
                                <div class="input__text">Login</div>
                                <input name="login" id="login" class="input" type="text">
                            </div>
                        </div>

                        <!--Password input-->
                        <div class="form__input">
                            <div class="input__icon">
                                <div class="white_icon">
                                    <img src="../../static/img/password.png">
                                </div>
                                <div class="yellow_icon">
                                    <img src="../../static/img/password-active.png">
                                </div>
                            </div>
                            <div class="input__div">
                                <div class="input__text">Password</div>
                                <input name="password" class="input" type="password">
                            </div>
                        </div>

                        <div class="form__bottom">
                            <div class="form__bottom__sing_up">
                                <a href="/registration">Sing up</a>
                            </div>
                            <div class="form__bottom__checkbox">
                                <input class="checkbox" name="check" type="checkbox">
                                <div class="remember_me__text">Don't forget me</div>
                            </div>
                        </div>
                        <div class="form__btn">
                            <input type="submit" class="button" value="Login">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../../static/js/login.js"></script>
</body>
</html>