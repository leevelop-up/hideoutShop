$(document).ready(function() {
    //로그인 상태 확인
    var accessToken = localStorage.getItem("Authorization");
    if(accessToken){
        $(".navbar-nav").html(
            "<li class=\"nav-item active\">" +
            "<a class=\"nav-link\" href=\"\" onclick=\"logout()\">로그아웃</a>"+
            "</li>" +
            "<li class=\"nav-item\">" +
            "<a class=\"nav-link\" href=\"/setting\">제품등록</a>" +
            "</li>"+
            "<li class=\"nav-item\">" +
            "<a class=\"nav-link\" href=\"/mylist\">제품수정</a>" +
            "</li>"+
            "<li class=\"nav-item\">" +
            "<a class=\"nav-link\" href=\"/mypage\">마이페이지</a>" +
            "</li>"
        );
    }


    //등록된 제품 가져오기
    $.get("/product/board", function(data) {
        // 가져온 데이터를 리스트에 추가

        let listHtml = "";
        for (let i = 0; i < data.length; i++) {
            listHtml +="<div class=\"card\" style=\"width: 18rem;\" onclick=\"detail("+data[i].productNo+")\">";
            listHtml +="<img class=\"card-img-top\" src="+"."+data[i].productPath+" alt=\"Card image cap\">";
            listHtml +="<div class=\"card-body\">";
            listHtml +="<p class=\"card-text\">"+data[i].productName+"</p>";
            listHtml +="</div>";
            listHtml +="</div>";

        }
        $("#body").append(listHtml);
    });
    getUserMe()
});

function getUserMe(){
    var settings = {
        "url": "/api/header",
        "method": "GET",
        "timeout": 0,
        "headers": {
            "Authorization": localStorage.getItem("Authorization")
        },
    };

    $.ajax(settings).done(function (response) { //성공하면
        console.log(response);

    });
}
function logout(){
    localStorage.removeItem('Authorization');
    location.replace("/home");
}

function detail(no){
    location.replace("/detail");
}



$("#sendRequest").click(function() {
    $.ajax({
        url: "https://example.com/your_endpoint",
        method: "GET",  // 또는 "POST"
        headers: {
            "Custom-Header": "HeaderValue",
            "Another-Header": "AnotherValue",
            "text": "your_text_value"  // Request Header에 "text" 값 추가
        },
        success: function(data) {
            // 성공적으로 응답을 받았을 때 수행할 작업
            console.log(data);
        },
        error: function(error) {
            // 요청이 실패했을 때 수행할 작업
            console.error(error);
        }
    });
});