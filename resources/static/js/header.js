$(document).ready(function () {
    //Бургер
    $('.header__burger').click(function (event) {
        $('.header__burger, .header__links').toggleClass('active-menu');
    });
});