function Reset() {
    var token = $("#token").val();
    $.post("resetStat?_csrf=" + token);
    temp();
    function temp(){
        $('#modal_form')
            .animate({opacity: 0, top: '45%'}, 200,
                function(){
                    $(this).css('display', 'none');
                    $('#overlay').fadeOut(400);
                }
            );
    }
}
function loading() {
    var token = $("#token").val();
    $.post("stat?_csrf=" + token,
    function (dataSource) {
        // var json = JSON.parse(dataSource);
        // var list = json.statt;
        var all = dataSource.split('+');
        var count=all[0].split('-');
        var names=all[1].split(',');
        count.pop();
        names.pop();
        google.charts.load('current', {'packages': ['bar']});
        google.charts.setOnLoadCallback(drawStuff);
        function drawStuff() {
            var array = [];
            array.push(['Move', 'logins per mounth']);
            for (var i = 0; i < names.length; i++) {
                array.push([names[i], parseInt(count[i])])
            }

            var data = new google.visualization.arrayToDataTable(array);

            var options = {
                title: 'Chess opening moves',
                width: 900,
                legend: {position: 'none'},
                chart: {subtitle: 'stat by user logins'},
                axes: {
                    x: {
                        0: {side: 'top', label: 'Users attendance'} // Top x-axis.
                    }
                },
                bar: {groupWidth: "90%"}
            };

            var chart = new google.charts.Bar(document.getElementById('top_x_div'));
            chart.draw(data, options);
        }
    }
    )
}
$(document).ready(function() {
    $('#go').click( function(event){ 
        event.preventDefault(); 
        $('#overlay').fadeIn(400, 
            function(){ 
                $('#modal_form')
                    .css('display', 'block') 
                    .animate({opacity: 1, top: '50%'}, 200); 
            });
    });
    $('#modal_close, #overlay').click( function(){ 
        $('#modal_form')
            .animate({opacity: 0, top: '45%'}, 200,  
                function(){ // пoсле aнимaции
                    $(this).css('display', 'none'); 
                    $('#overlay').fadeOut(400); 
                }
            );
    });
});
