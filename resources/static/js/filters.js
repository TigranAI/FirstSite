$(document).ready(function () {
    //Бургер
    $('.header__burger').click(function (event) {
        $('.header__burger, .header__links').toggleClass('active-menu');
    });
});

$(document).ready(function () {
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

    updateFilters(page, author, tier, emoji, sortBy)
}

function clearFilters() {
    updateFilters(null, null, null, null, null)
    updateUrl(window.location.href.split('?')[0]);
}

function updateFilters(page, author, tier, emoji, sortBy) {
    let filters = { page: page, author: author, tier: tier, emoji: emoji, sortBy: sortBy }

    $.ajax({
        type: 'POST',
        url: window.location.pathname,
        name: '',
        data: filters,
        data_type: 'json',
        success: function (resp) {
            applyContentTo(resp, $("#stickersContent"))
            updateListeners()
        }
    })
}

function applyContentTo(response, element) {
    element.html(response.trim())
}

function updateUrl(newUrl){
    window.history.pushState("", $(document).find("title").text(), newUrl);
}