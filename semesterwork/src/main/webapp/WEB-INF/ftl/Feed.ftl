<#import "Base.ftl" as base>

<@base.main>
    <div class="search">
        <form>
            <div class="search__bar">
                <input id="search" name="search" type="text" class="search_term"
                       placeholder="What are you looking for?">
                <button type="button" id="btn_open" class="btn_open_more">
                    <i class="fa fa-caret-down"></i>
                </button>
                <button type="button" id="btn_search" class="btn_search">
                    <i class="fa fa-search"></i>
                </button>
            </div>
            <div id="search__div" class="search__div">
                <div class="search__config">
                    <div class="config__sort_by">
                        <div class="form-group ">
                            <label for="sort_by__select">Сортировать по:</label>
                            <select name="sorting" class="form-control" id="sort_by__select">
                                <option value="normal">Умолчанию</option>
                                <option value="priceIncrease">Цене по возрастанию</option>
                                <option value="priceDecrease">Цене по убыванию</option>
                            </select>
                            <input name="stars" type="checkbox" value="3"><span>3 звезды</span>
                            <input name="stars" type="checkbox" value="4"><span>4 звезды</span>
                            <input name="stars" type="checkbox" value="5"><span>5 звезд</span>
                        </div>
                    </div>
                    <div class="config__prise_interval">
                        <div class="prise_interval">
                            <div class="prise_interval__text">Цена от</div>
                            <div class="prise_interval__input">
                                <input id="priceFrom" name="priceFrom" type="text" class="form-control" placeholder="0">
                            </div>
                            <div class="prise_interval__text">до</div>
                            <div class="prise_interval__input">
                                <input id="priceTo" name="priceTo" type="text" class="form-control" placeholder="*">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>


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

    <nav id="parent_pagination">
        <ul id="child_pagination" class="pagination">
            <#assign page=1>
            <#list pages as pages>
                <li class="page-item">
                    <form class="test__form">
                        <button class="page-link" value="${page}" type="button" onclick="aa(this)">${page}</button>
                    </form>
                </li>
                <#assign page+=1>
            </#list>
        </ul>
    </nav>

    <div class="bottom">
        <div class="bottom__div">
        </div>
    </div>

    <link rel="stylesheet" href="../../static/css/feed.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="../../static/js/ajaxrequest.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="../../static/js/feed.js"></script>
</@base.main>