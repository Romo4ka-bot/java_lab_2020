<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../static/css/login.css">
    <title>Registration</title>
</head>
<body>
<div class="wrapper">
    <header class="header">

    </header>
    <div class="page">
        <div class="page__image">
            <img src="../../static/img/space-shuttle.jpg">
        </div>
        <div class="page__login">
            <div class="login_container">
                <div class="login__welcome">
                    <div class="login__welcome__text">Sign up</div>
                </div>

<#--                <#if errMessage??><span style="color: red">${errMessage}</span></#if>-->
                <div class="login__form">
                    <div id="error_text" class="error_text"></div>
                    <form id="form" action="/registration" method="post">

                        <div class="form__input">
                            <div class="input__icon">
                                <div class="white_icon">
                                    <img src="../../static/img/dot.png">
                                </div>
                                <div class="yellow_icon">
                                    <img src="../../static/img/dot-active.png">
                                </div>
                            </div>
                            <div class="input__div">
                                <div class="input__text">Name</div>
                                <input name="first_name" id="first_name" class="input" type="text">
                            </div>
                        </div>
                        <div class="form__input">
                            <div class="input__icon">
                                <div class="white_icon">
                                    <img src="../../static/img/dot.png">
                                </div>
                                <div class="yellow_icon">
                                    <img src="../../static/img/dot-active.png">
                                </div>
                            </div>
                            <div class="input__div">
                                <div class="input__text">Second name</div>
                                <input name="second_name" id="second_name" class="input" type="text">
                            </div>
                        </div>
                        <div class="form__input">
                            <div class="input__icon">
                                <div class="white_icon">
                                    <img src="../../static/img/dot.png">
                                </div>
                                <div class="yellow_icon">
                                    <img src="../../static/img/dot-active.png">
                                </div>
                            </div>
                            <div class="input__div">
                                <div class="input__text">Email</div>
                                <input name="email" id="login" class="input" type="text">
                            </div>
                        </div>
                        <div class="form__radio">
                            <div class="radio__gender">Gender:</div>
                            <div class="radio__gender">
                                <input type="radio" name="gender" value="man" checked>
                                <div class="gender__text">Man</div>
                            </div>
                            <div class="radio__gender">
                                <input type="radio" name="gender" value="woman">
                                <div class="gender__text">Woman</div>
                            </div>
                        </div>
                        <div class="form__input">
                            <div class="input__icon">
                                <div class="white_icon">
                                    <img src="../../static/img/dot.png">
                                </div>
                                <div class="yellow_icon">
                                    <img src="../../static/img/dot-active.png">
                                </div>
                            </div>
                            <div class="input__div">
                                <div class="input__text">Password</div>
                                <input name="password1" id="password1" class="input" type="password">
                            </div>
                        </div>
                        <div class="form__input">
                            <div class="input__icon">
                                <div class="white_icon">
                                    <img src="../../static/img/dot.png">
                                </div>
                                <div class="yellow_icon">
                                    <img src="../../static/img/dot-active.png">
                                </div>
                            </div>
                            <div class="input__div">
                                <div class="input__text">Password</div>
                                <input name="password2" id="password2" class="input" type="password">
                            </div>
                        </div>
                        <div class="form__btn">
                            <input type="submit" class="button" value="Submit">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../../static/js/registration.js"></script>
</body>
</html>