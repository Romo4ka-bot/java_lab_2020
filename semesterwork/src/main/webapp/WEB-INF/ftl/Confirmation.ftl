<#import "Base.ftl" as base>

<@base.main>
    <div class="confirmation">
        <div class="confirmation__heading">Подтверждение заказа</div>
        <div class="confirmation__holder">
            <div class="holder__image">
                <img src="../../static/img/space-shuttle.jpg">
            </div>
            <div class="holder__text_holder">
                <div class="text_holder__text">
                    <div class="text__heading">${feed.title}</div>
                    <div class="text__day">Дата вылета: ${feed.dateFrom}</div>
                    <div class="text__day">Дата вылета: ${feed.dateTo}</div>
                    <div class="text__prise">${feed.price}₽</div>
                    <div class="text__btn"><a href="/confirmation_success?id=${feed.id}">Забронировать</a></div>
                </div>
            </div>
        </div>
    </div>
    <div class="bottom">
        <div class="bottom__div">
        </div>
    </div>

    <link rel="stylesheet" href="/static/css/confirmation.css">
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