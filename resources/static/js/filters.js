$(document).ready(function () {
    //Бургер
    $('.header__burger').click(function (event) {
        $('.header__burger, .header__links').toggleClass('active-menu')
    });
    //Фильтры
    updateListeners()
});

function updateListeners(){
    $('.filters__accordion-title').click(function (event) {
        $(this).toggleClass('active-filter')
        $(this).next().slideToggle(300)
        $(this).next().css('display', 'block')
        $(this).children().toggleClass('filters__accordion-button_clicked')
    });
}

function Filters(url){
    this.page = url.searchParams.get("page")
    this.author = url.searchParams.get("author")
    this.tier = url.searchParams.get("tier")
    this.emoji = url.searchParams.get("emoji")
    this.sortBy = url.searchParams.get("sortBy")
}

function setFilter(button) {
    let url = new URL(window.location)
    let param = button.getAttribute("name")
    let value = button.id.toString()
    if (value.includes('clear')) url.searchParams.delete(param)
    else {
        if (url.searchParams.has(param)) url.searchParams.set(param, value)
        else url.searchParams.append(param, value)
    }
    updateUrl(url.toString());
}

function setPage(pageNum){
    let url = new URL(window.location)
    if (url.searchParams.has("page")) url.searchParams.set("page", pageNum)
    else url.searchParams.append("page", pageNum)
    updateUrl(url.toString())
    updateContent(new Filters(url))
}

function applyFilters() {    
    let url = new URL(window.location)
    url.searchParams.delete('page')
    updateUrl(url.toString())
    updateContent(new Filters(url))
}

function clearFilters() {
    updateContent(new Filters())
    updateUrl(window.location.href.split('?')[0]);
}

function updateContent(filters) {
    $.ajax({
        type: 'POST',
        url: window.location.pathname,
        data: filters,
        data_type: 'json',
        async: true,
        success: function (resp) {
            applyContent(resp, $("#stickersContent"))
            updateListeners()
        }
    })
}

function applyContent(response, element) {
    element.html(response.trim())
}

function updateUrl(newUrl){
    window.history.pushState("", $(document).find("title").text(), newUrl);
}