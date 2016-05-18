//$(document).ready(function() {
//    $("#DeleteButton").click(createUser)
//});

//$(document).on("Up","#forma", function(event) {
//    var $form = $(this);
//    $.post("/savefiles",
//        $form.serialize(),
//        function (data){
//            alert(data);
//        }
//    )
//    event.preventDefault();
//});

function Upload() {
    var $form = $(this);
    var File1 = document.getElementById("File1");
    var tip= File1.value.toString();


    var tipstr1 = tip;
    var from1=tipstr1.search('fakepath');
    var to1= tipstr1.length;
    var tipstr2=tipstr1.substring(from1,to1);


    var strstr1 = tipstr2;
    var from2=9;
    var to2= strstr1.length;
    var strstr2=strstr1.substring(from2,to2);//DO NOT OPEN!!!
    $.post("/savefiles",
        $form.serialize()
        ,
        function (data) {
            //alert(data + "1");
            //var str = data;
            //var from=str.search('resources');
            //var to= str.length;
            //var str2=str.substring(from,to);
            //alert(str2 + "2");
            $("#NEWimage_container")

                .append('<img style="margin: 10px" width="100px" height="100px" src="\\resources\\images\\'+strstr2+'"/>')
            SelectImg()
        }
    )
}
function DeletePost(id) {
    //var firstName = $("#firstName").val();
    //var lastName = $("#lastName").val();
    //var email = $("#email").val();
    //var firstName = document.getElemtentById("fistname").value;
    $.post("/deletePost", {
            id: id
        },
        function (data) {
            //var json = JSON.parse(data);

            window.location.reload();
            //  alert(data);
            //  $("#acontent").html(data);
        }
    )
}

//   // $.post("/savefiles"
//        //,
//        //{

//        //dataType: "html"
//    //    function (response)
//    //{
//    //    document.getElementById("tests").innerHTML = "true";
//    //}
//    //error: function (response) {
//    //    document.getElementById("tests").innerHTML = "false";
//    //}
//)
//}
function EditPost(id) {

    $.get("Update?id="+id)
}
