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