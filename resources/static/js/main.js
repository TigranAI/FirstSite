$(document).ready(function () {
    $('.slider__wrapper').cycle({
        fx: 'fade',
        speed: 300,
        timeout: 3000,
        cleartypeNoBg: true,
        pager: '#pager',
        pause: 1,

        pagerAnchorBuilder: function (idx, slide) {
            return '<li><a href="#"></a></li>';
        }
    });

    //Спойлеры
    $('.instruction__item-title').click(function (event) {
        $(this).toggleClass('active').next().slideToggle(300);
        $(this).next().css('display', 'flex');
    });
});
