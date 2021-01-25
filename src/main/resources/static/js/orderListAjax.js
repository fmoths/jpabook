var main = {
    init : function () {
        var _this = this;
        $('#search').on('click', function () {
            console.log("test");
            _this.save();
        });
    },
    save : function () {
        // 화면으로부터 입력 받은 변수값의 처리
        var member = $("#validationCustom01");
        var status = $("#validationCustom04");

        var memberVal = member.val();
        var statusVal = status.val();

        console.log(memberVal);
        console.log(statusVal);

        var data = {memberName: member.val(),orderStatus: status.val()};

        // AJAX통신 : POST
        $.ajax({
            type : "post",
            url : "/orders",
//            contentType: "application/json; charset=utf-8",
            dataType : "json",
            data : data,
            success : function (data) {
                alert(data.length);
//                printData(data);
            },
            error : function (e) {
               alert("fail");
//                printData(data);
           }
        });
    }
};

main.init();

//var source = $("#template").html();
//var templateMissions = Handlebars.compile(source);
//
//function printData(datas){
//    for (var i = 0; i < datas.length; i++) {
//        var data = datas[i];
//        var dataStamp = {
//            name: data.name,
//            description: data.description,
//            img: data.imageUrl
//        }
//        var template = template(dataStamp)
//        $('.append-here').append(template);
//    }
//}

//// 1000번째 게시글
//var articleNo = 1000;
//
//// 댓글 목록 호출
//getReplies();
//
//// 댓글 목록 출력 함수
//function getOrders() {
//
//    $.getJSON("/replies/all/" + articleNo, function (data) {
//
//        console.log(data);
//
//        var str = "";
//
//        $(data).each(function () {
//            str += "<li data-replyNo='" + this.replyNo + "' class='replyLi'>"
//                +   "<p class='replyText'>" + this.replyText + "</p>"
//                +   "<p class='replyWriter'>" + this.replyWriter + "</p>"
//                +   "<button type='button' class='btn btn-xs btn-success' data-toggle='modal' data-target='#modifyModal'>댓글 수정</button>"
//                + "</li>"
//                + "<hr/>";
//
//        });
//
//        $("#replies").html(str);
//
//    });
//
//}