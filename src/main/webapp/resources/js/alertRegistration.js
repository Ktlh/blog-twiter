$(document).ready(function () {
    
    $('#modal_close, #overlay').click(function () {
        $('#modal_form')
            .animate({opacity: 0, top: '5%'}, 200,
                function () {
                    $(this).css('display', 'none');
                    $('#overlay').fadeOut(400);
                }
            );
        window.location.href = "http://localhost:8080/"
    });
});


function RD() {
    temp();
    function temp() {
        $('#modal_form')
            .animate({opacity: 0, top: '45%'}, 200,
                function () {
                    $(this).css('display', 'none');
                    $('#overlay').fadeOut(400);
                }
            );
    }

    window.location.href = "http://localhost:8080/"
}
