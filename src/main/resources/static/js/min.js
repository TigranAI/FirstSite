function onClickShowToast(toastId) {
    $.ajax({
        type: 'POST',
        url: '/toast',
        name: '',
        data: { toastId: toastId },
        success: function(resp) {
            response(resp)
            let toast = document.getElementById(toastId)
            showToast(toast)
        }
    });
}

function response(toastBody) {
    document.getElementById("toastContainer").innerHTML += toastBody.trim();
}

function showToast(toastElement) {
    let toast = new bootstrap.Toast(toastElement)
    toast.show()
}

function setFilter(button){
    let name = button.getAttribute("name")
    if (button.checked) {
        if (button.id.toString().includes('clear')) $("input[name=" + name + "]").val('')
        else $("input[name=" + name + "]").val(button.id)
    }
    else $("input[name="+name+"]").val('')
}

function applyFilters(page = 1){
    let url = window.location.pathname
    let pageInput = $("input[name=page]")
    $('#page'+pageInput.val()).removeClass('active')
    pageInput.val(page)
    $('#page'+page).addClass('active')
    let newUrl = url + '?page=' + page + '&'
    let author = $("input[name=author]").val()
    let tier = $("input[name=tier]").val()
    let emoji = $("input[name=emoji]").val()
    let sortBy = $("input[name=sortBy]").val()
    if(author !== '') newUrl += 'author='+author+'&'
    if(tier !== '') newUrl += 'tier='+tier+'&'
    if(emoji !== '') newUrl += 'emoji='+emoji+'&'
    if(sortBy !== '') newUrl += 'sortBy='+sortBy+'&'
    newUrl = newUrl.substring(0, newUrl.length-1)
    $.ajax({
        type: 'POST',
        url: url,
        name: '',
        data: {
            page: page,
            author: author,
            tier: tier,
            emoji: emoji,
            sortBy: sortBy,
        },
        success: function(resp) {
            window.history.pushState("", $(document).find("title").text(), newUrl)
            applyContentTo(resp, $("#content"))
            $.ajax({
                type: 'POST',
                url: '/pages',
                name: '',
                data: {
                    pagesCount: $('#pagesCount').val(),
                    currentPage: page,
                },
                success: function(resp) {
                    applyContentTo(resp, $("#pagesBottom"))
                }
            })
        }
    })
}

function applyContentTo(response, element){
    element.html(response.trim())
}

var myModal = document.getElementById('license')
var myInput = document.getElementById('myInput')

myModal.addEventListener('shown.bs.modal', function () {
    myInput.focus()
})