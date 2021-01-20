$("#search").on("click", function () {

    // 화면으로부터 입력 받은 변수값의 처리
    var member = $("#button-addon2");
    var status = $(".dropdown-item");

    var memberVal = member.val();
    var statusVal = status.val();

    // AJAX통신 : GET
    $.ajax({
        type : "get",
        url : "/orders",
        headers : {
            "Content-type" : "application/json"
        },
        dataType : "text",
        data : JSON.stringify({
                    memberName : memberVal,
                    orderStatus : statusVal
                }),
        success : function (result) {
            console.log("result : " + result);
            if (result == "success") {
                alert("주문 내역 검색 완료!");
                getAllList(); // 댓글 목록 갱신
            }
        }
    });
});

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

// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })
})()