<#import "Base.ftl" as base>

<@base.main>
    <div class="about_us">
        <div class="about_us__holder">
            <div class="holder__element">
                <div class="element__img">
                    <img src="../../static/img/our_team.jpg">
                </div>
                <div class="element__text">
                    <p> Praesent posuere tempor augue vel bibendum. Quisque rutrum urna vitae ullamcorper ultricies.
                        Vivamus consectetur ligula at purus vulputate, sit amet aliquam massa tristique. Nullam nec
                        ante sed mauris suscipit sodales. Donec facilisis egestas placerat. Mauris non rhoncus ipsum,
                        eget dictum massa.</p>
                </div>
            </div>
            <div class="holder__element">
                <div class="element__text">
                    <p> Praesent posuere tempor augue vel bibendum. Quisque rutrum urna vitae ullamcorper
                        ultricies. Vivamus consectetur ligula at purus vulputate, sit amet aliquam massa tristique.
                        Nullam nec ante sed mauris suscipit sodales. Donec facilisis egestas placerat. Mauris non
                        rhoncus ipsum, eget dictum massa. Suspendisse sit amet fringilla turpis. Nunc tempor
                        venenatis nulla, at luctus lectus molestie vel. Integer libero lectus, tristique et
                        imperdiet in, lacinia vel tellus. </p>
                </div>
                <div class="element__img">
                    <img src="../../static/img/rocket_fly.jpg">
                </div>
            </div>
            <div class="holder__element">
                <div class="element__img">
                    <img src="../../static/img/palnet_photo.jpg">
                </div>
                <div class="element__text">
                    <p> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ante ante, posuere sed
                        tristique quis, laoreet in enim. Curabitur dapibus in lectus vitae pellentesque. Sed
                        tellus erat, commodo at volutpat id, porttitor sit amet lorem. Proin non justo rhoncus,
                        pretium ante at, egestas dolor. Cras hendrerit, sem at consequat ultrices, eros magna
                        euismod mi, auctor commodo sem lorem quis urna. Nullam non sem efficitur turpis
                        pellentesque imperdiet vel id massa. </p>
                </div>
            </div>
        </div>
    </div>
    <div class="holder__element">
        <div class="center__element">
            <div class="element__ask">
                <p>Остались вопросы или есть предложения по сайту?
                    <#if user??>
                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#staticBackdrop2">
                            Свяжитесь с нами
                        </button>
                        <#else>
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#staticBackdrop1">
                                Свяжитесь с нами
                            </button>
                    </#if>
                </p>
            </div>
        </div>
    </div>
    <!-- Modal1 -->
    <div class="modal fade" id="staticBackdrop1" data-backdrop="static" data-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Свяжитесь с нами</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="/support_message" method="post">
                    <div class="modal-body">
                        <div class="form-group row">
                            <div class="col-md-6">
                                <label for="first-name" class="col-form-label">Имя:</label>
                                <input name="name" type="text" class="form-control" id="first-name">
                            </div>
                            <div class="col-md-6">
                                <label for="last-name" class="col-form-label">Фамилия:</label>
                                <input name="surname" type="text" class="form-control" id="last-name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-form-label">Email:</label>
                            <input name="email" type="text" class="form-control" id="email">
                        </div>
                        <div class="form-group">
                            <label for="message-text" class="col-form-label">Message:</label>
                            <textarea name="content" class="form-control" id="message-text"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
                        <button type="submit" class="btn btn-primary">Отправить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Modal2 -->
    <div class="modal fade" id="staticBackdrop2" data-backdrop="static" data-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Свяжитесь с нами</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="/supportMessage" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="message-text" class="col-form-label">Message:</label>
                            <textarea name="content" class="form-control" id="message-text"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
                        <button type="submit" class="btn btn-primary">Отправить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="bottom">
        <div class="bottom__div">
        </div>
    </div>

    <link rel="stylesheet" href="../../static/css/about_us.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="../../static/js/feed.js"></script>
</@base.main>