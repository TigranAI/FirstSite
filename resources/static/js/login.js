$(document).ready(function () {
    let eventUrl = "/login/event"
    let botName = $("input[name=botName]").val()
    let eventSource = new EventSource(eventUrl)
    eventSource.addEventListener("INIT", function (event) {
        let url = 'tg://resolve?domain=' + botName + '&start=login=' + event.data
        $('#BotLink').attr("href", url)
    })
    eventSource.addEventListener("confirm", function (event) {
        let form = document.createElement("form")
        form.method = 'POST'
        form.action = '/login'

        let input = document.createElement('input');
        input.type = 'text';
        input.name = 'key';
        input.value = event.data;
        form.appendChild(input);

        document.body.appendChild(form);
        form.submit();
    })
})