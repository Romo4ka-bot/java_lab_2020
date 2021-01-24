<#import "Base.ftl" as base>

<@base.main>
    <h3 class="header">10 последних новостей</h3>
    <div class="news">
        <div class="news_holder">
            <#list list as list>
                <div class="post">
                    <div class="post__image">
                        <img src=/img?filename=${list.photo}>
                    </div>
                    <div class="post__text">
                        <div class="text__heading">${list.title}</div>
                        <div class="text__content">
                            <p>${list.content}</p>
                        </div>
                        <div class="text__day">
                            <div class="day">${list.date}</div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
    <div class="bottom">
        <div class="bottom__div">
        </div>
    </div>

    <link rel="stylesheet" href="/static/css/news.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="/static/js/feed.js"></script>
</@base.main>