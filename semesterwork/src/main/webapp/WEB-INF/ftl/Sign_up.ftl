<!DOCTYPE html>
<html lang="en">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../static/css/login.css">
    <title>Registration</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <header class="header" style="display: flex; flex-direction: row; justify-content: flex-end; margin-right: 10px">
        <h4 style="margin-right: 5px"><a href="?lang=ru">РУ</a></h4>
        <h4><a href="?lang=en">EN</a></h4>
    </header>
    <div class="page">
        <div class="page__image">
            <img src="../../static/img/space-shuttle.jpg">
        </div>
        <div class="page__login">
            <div class="login_container">
                <div class="login__welcome">
                    <div class="login__welcome__text"><@spring.message 'sign_up.title'/></div>
                </div>
                <div class="login__form">
                    <div id="error_text" class="error_text"></div>
                    <@spring.bind "userForm"/>
                    <#if namesErrorMessage??>
                        <p class="error">${namesErrorMessage}</p>
                    </#if>
                    <form id="form" action="/signUp" method="post">

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
                                <div class="input__text"><@spring.message 'sign_up.form.first_name'/></div>
                                <@spring.formInput "userForm.firstName" "name='first_name' id='first_name' class='input' type='text'"/>
                                <@spring.showErrors "<br>" "error"/>
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
                                <div class="input__text"><@spring.message 'sign_up.form.second_name'/></div>
                                <@spring.formInput "userForm.secondName" "name='second_name' id='second_name' class='input' type='text'"/>
                                <@spring.showErrors "<br>" "error"/>
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
                                <div class="input__text"><@spring.message 'sign_up.form.email'/></div>
                                <@spring.formInput "userForm.login" "name='email' id='login' class='input' type='text'"/>
                                <@spring.showErrors "<br>" "error"/>
                            </div>
                        </div>
                        <div class="form__radio">
                            <div class="radio__gender"><@spring.message 'sign_up.form.gender'/>:</div>
                            <div class="radio__gender">
                                <input type="radio" name="gender" value="man" checked>
                                <div class="gender__text"><@spring.message 'sign_up.form.gender.man'/></div>
                            </div>
                            <div class="radio__gender">
                                <input type="radio" name="gender" value="woman">
                                <div class="gender__text"><@spring.message 'sign_up.form.gender.woman'/></div>
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
                                <div class="input__text"><@spring.message 'sign_up.form.password'/></div>
                                <@spring.formPasswordInput "userForm.password1" "name='password1' id='password1' class='input' type='password'"/>
                                <@spring.showErrors "<br>" "error"/>
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
                                <div class="input__text"><@spring.message 'sign_up.form.password'/></div>
                                <@spring.formPasswordInput "userForm.password2" "name='password2' id='password2' class='input' type='password'"/>
                                <@spring.showErrors "<br>" "error"/>
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
<script src="../../static/js/signup.js"></script>
</body>
</html>