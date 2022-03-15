$(document).ready(function () {
    //Бургер
    $('.header__burger').click(function (event) {
        $('.header__burger, .header__links').toggleClass('active-menu');
    });
    //Фильтры
    updateListeners();
});

function updateListeners(){
    $('.filters__accordion-title').click(function (event) {
        $(this).toggleClass('active-filter');
        $(this).next().slideToggle(300);
        $(this).next().css('display', 'block');
        $(this).children().toggleClass('filters__accordion-button_clicked');
    });
}

function Filters(page, author, tier, emoji, sortBy){
    this.page = page;
    this.author = author;
    this.tier = tier;
    this.emoji = emoji;
    this.sortBy = sortBy;
}

function setFilter(button) {
    let url = new URL(window.location)
    let param = button.getAttribute("name")
    let value = button.id.toString()
    if (value.includes('clear')) url.searchParams.delete(param);
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
    updateUrl(url.toString());
    applyFilters()
}

function applyFilters() {    
    let url = new URL(window.location)
    let page = url.searchParams.get('page')
    let author = url.searchParams.get('author')
    let tier = url.searchParams.get('tier')
    let emoji = url.searchParams.get('emoji')
    let sortBy = url.searchParams.get('sortBy')

    updateContent(page, author, tier, emoji, sortBy)
}

function clearFilters() {
    updateContent(null, null, null, null, null)
    updateUrl(window.location.href.split('?')[0]);
}

function updateContent(page, author, tier, emoji, sortBy) {
    let filters = new Filters(page, author, tier, emoji, sortBy)

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