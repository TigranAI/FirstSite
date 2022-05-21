$(document).ready(function () {
    let eventUrl = "/login/event"
    let botName = $("input[name=botName]").val()
    let eventSource = new EventSource(eventUrl)
    eventSource.addEventListener("INIT", function (event) {
        let url = 'tg://resolve?domain=' + botName + '&start=login=' + event.data
        $('#BotLink').attr("href", url)
    })
    eventSource.addEventListener("confirm", function (event) {
        eventSource.close()
        window.location.href = "/login"
    })
})