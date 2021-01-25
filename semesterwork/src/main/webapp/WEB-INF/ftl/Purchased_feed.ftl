<#import "Base.ftl" as base>

<@base.main>

    <h4 class="header">Мои путевки</h4>

    <#if list??>
        <div class="card_holder" id="parent">
            <div id="child" style="width: 100%; margin: 10px 0;">
                <#list list as list>
                    <div class="card_item">
                        <div class="card__image">
                            <img src="../../static/img/icon-spaceman.jpg">
                        </div>
                        <div class="card__info">
                            <div class="card__text">
                                <div class="text__heading">
                                    <div class="heading__review"><a href="/ticket?id=${list.id}">Отзывы</a></div>
                                    <div class="heading__name"><a href="/ticket?id=${list.id}">${list.title}</a></div>
                                    <div style="font-size: 20px">
                                        ${list.stars} звезд(ы)
                                    </div>
                                </div>
                                <div class="text__cont">
                                    <div class="cont__date">${list.dateFrom} - ${list.dateTo}</div>
                                    <div class="cont__inf">${list.description}</div>
                                </div>
                            </div>
                            <div class="card__prise">
                                <div class="prise__count">${list.price}₽</div>
                                <div class="prise__btn"><a href="/ticket?id=${list.id}">Подробнее</a></div>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
        <div class="card_holder" id="parent">
            <div class="btn_back">
                <form action="/feed" method="get">
                    <button class="prise__btn btn_back">Назад</button>
                </form>
            </div>

        </div>
    <#else>
        <div class="card_holder" id="parent">

            <div class="content">
                <div>Вы не купили ни одной путевки. Хотите попробовать приобрести наши услуги?</div>
                <form action="/feed?page=1" method="get">
                    <button class="btn btn-secondary btn_try">Попробовать</button>
                </form>
            </div>

            <div class="btn_back">
                <form action="/feed" method="get">
                    <button class="prise__btn btn_back">Назад</button>
                </form>
            </div>

        </div>
    </#if>

    <div class="bottom">
        <div class="bottom__div">
        </div>
    </div>

    <link rel="stylesheet" href="../../static/css/purchased_feed.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</@base.main>