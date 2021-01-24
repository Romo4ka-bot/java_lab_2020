<div class="content">
    <nav class="navbar navbar-expand-lg navbar-dark">
        <a class="navbar-brand" href="#">SpaceDrive</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
                aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav mx-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/">Домашняя</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/feed?page=1">Путевки</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/news">Новости</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/info">Про нас</a>
                </li>
            </ul>
            <#if user??>
                <ul class="navbar-nav navbar-right">
                    <li class="nav-item active">
                        <a class="nav-link" href="/logout">Выйти</a>
                    </li>
                </ul>
                <#else>
                    <ul class="navbar-nav navbar-right">
                        <li class="nav-item active">
                            <a class="nav-link" href="/login">Войти</a>
                        </li>
                    </ul>
            </#if>
        </div>
    </nav>
</div>
