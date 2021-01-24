$(function () {
    $('#btn_search').click(function (event) {
        event.preventDefault();
        var search = $('#search').val();
        var checkboxes = document.getElementsByName('stars');
        var tags = [];
        i = 0;
        for (var index = 0; index < checkboxes.length; index++) {
            if (checkboxes[index].checked) {
                tags[i] = checkboxes[index].value;
                i++;
            }
        }

        var select = document.getElementById("sort_by__select");
        var value = select.value;
        var priceFrom = $('#priceFrom').val();
        var priceTo = $('#priceTo').val();
        var tagsJson = JSON.stringify(tags);

        $.ajax({
            type: "POST", url: "/feed",
            data: {
                page: 1,
                search: search,
                tags: tagsJson,
                value: value,
                priceFrom: priceFrom,
                priceTo: priceTo,
                alterPages: true
            },
            dataType: 'json',
            success: function (result) {
                console.log(result);
                let tag = tagsJson.replaceAll("\"","").replace("[", "").replace("]", "");
                console.log(tag);
                var url = '/feed?search=' + search + '&tags=' + tag + '&value=' + value + '&priceFrom=' + priceFrom + '&priceTo=' + priceTo + '&page=1';
                history.pushState(null, null, url);
                history.replaceState(null, null, url);
                $("#child").detach();
                $("<div id=\"child\" class=\"card_holder\" style=\"width: 100%;\">").appendTo($("#parent"));
                var j;
                for (let i = 0; i < result.length - 1; i++) {
                    $('#child').append($(' <div class="card_item"> ' +
                        '<div class="card__image"> <img src="/static/img/icon-spaceman.jpg"> ' +
                        '</div> ' +
                        '<div class="card__info"> ' +
                        '<div class="card__text"> ' +
                        '<div class="text__heading"> ' +
                        '<div class="heading__review"><a href="/ticket?id=' + result[i]['id'] + '">Отзывы</a></div> ' +
                        '<div class="heading__name"><a href="/ticket?id=' + result[i]['id'] + '">' + result[i]['title'] + '</a></div> ' +
                        '<div>' +
                        +result[i]['stars'] + ' звезд(ы)' +
                        '</div>' +
                        '</div>\n' +
                        '<div class="text__cont">\n' +
                        '<div class="cont__date">' + result[i]['dateFrom'] + ' - ' + result[i]['dateTo'] + '</div> ' +
                        '<div class="cont__inf">' + result[i]['description'] + '</div> ' +
                        '</div>' +
                        '</div>' +
                        '<div class="card__prise">' +
                        '<div class="prise__count">' + result[i]['price'] + '₽</div> ' +
                        '<div class="prise__btn"><a href="/ticket?id=' + result[i]['id'] + '">Подробнее</a></div> ' +
                        '</div> ' +
                        '</div> ' +
                        '</div> '
                    ));
                    j = i;
                }

                $("#child_pagination").detach();
                $("<ul id=\"child_pagination\" class=\"pagination\">").appendTo($("#parent_pagination"));
                for (let i = 0; i < result[j + 1]; i++) {
                    let count = i+1;
                    $('#child_pagination').append($('<li class="page-item">' +
                        '<form class="test__form">' +
                        '<button class="page-link" value="' + count + '" type="button" onclick="aa(this)">'+ count +'</button>' +
                        '</form>' +
                        '</li>'
                    ));
                }
            }
        });
    });
});

    var aa=function (b) {
        var page = b.value;
        var url = window.location.href;
        history.pushState(null, null, url.substr(0, url.length - 1) + page);
        history.replaceState(null, null, url.substr(0, url.length - 1) + page);
        var ind = url.indexOf('?');
        url = url.substr(ind, url.length);
        var searchElem = url.split("&");
        if (searchElem.length > 1) {
            var search = searchElem[0].substr(searchElem[0].indexOf("=") + 1);
            var tagsJson = searchElem[1].substr(searchElem[1].indexOf("=") + 1).replaceAll("%22", "\"");
            var value = searchElem[2].substr(searchElem[2].indexOf("=") + 1);
            var priceFrom = searchElem[3].substr(searchElem[3].indexOf("=") + 1);
            var priceTo = searchElem[4].substr(searchElem[4].indexOf("=") + 1);
        }

        $.ajax({
            type: "POST", url: "/feed",
            data: {
                page: page,
                search: search,
                tags: tagsJson,
                value: value,
                priceFrom: priceFrom,
                priceTo: priceTo
            },
            dataType: 'json',
            success: function (result) {
                console.log(result);
                $("#child").detach();
                $("<div id=\"child\" class=\"card_holder\" style=\"width: 100%;\">").appendTo($("#parent"));
                for (var i = 0; i < result.length; i++) {
                    $('#child').append($(' <div class="card_item"> ' +
                        '<div class="card__image"> <img src="/static/img/icon-spaceman.jpg"> ' +
                        '</div> ' +
                        '<div class="card__info"> ' +
                        '<div class="card__text"> ' +
                        '<div class="text__heading"> ' +
                        '<div class="heading__review"><a href="/ticket?id=' + result[i]['id'] + '">Отзывы</a></div> ' +
                        '<div class="heading__name"><a href="/ticket?id=' + result[i]['id'] + '">' + result[i]['title'] + '</a></div> ' +
                        '<div>' +
                        +result[i]['stars'] + ' звезд(ы)' +
                        '</div>' +
                        '</div>\n' +
                        '<div class="text__cont">\n' +
                        '<div class="cont__date">' + result[i]['dateFrom'] + ' - ' + result[i]['dateTo'] + '</div> ' +
                        '<div class="cont__inf">' + result[i]['description'] + '</div> ' +
                        '</div>' +
                        '</div>' +
                        '<div class="card__prise">' +
                        '<div class="prise__count">' + result[i]['price'] + '₽</div> ' +
                        '<div class="prise__btn"><a href="/ticket?id=' + result[i]['id'] + '">Подробнее</a></div> ' +
                        '</div> ' +
                        '</div> ' +
                        '</div> '
                    ))
                }
            }
        })
    };

// $(function () {
    // $('.page-link').click(aa($(this)))
// });
