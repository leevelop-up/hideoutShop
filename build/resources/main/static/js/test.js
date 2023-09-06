$(document).ready(function() {
    //로그인 상태 확인
    var accessToken = localStorage.getItem("accesstoken");
    if(accessToken){
        $(".navbar-nav").html(
            "<li class=\"nav-item active\">" +
            "<a class=\"nav-link\" href=\"\" onclick=\"logout()\">로그아웃</a>"+
            "</li>" +
            "<li class=\"nav-item\">" +
            "<a class=\"nav-link\" href=\"/setting\">제품등록</a>" +
            "</li>"+
            "<li class=\"nav-item\">" +
            "<a class=\"nav-link\" href=\"/updatesetting\">제품수정</a>" +
            "</li>"
        );
    }



    //등록된 제품 가져오기
    $.get("/product/board", function(data) {
        // 가져온 데이터를 리스트에 추가

        let listHtml = "";
        for (let i = 0; i < data.length; i++) {
            listHtml +="<div class=\"card\" style=\"width: 18rem;\">";
            listHtml +="<img class=\"card-img-top\" src="+"."+data[i].productPath+" alt=\"Card image cap\">";
            listHtml +="<div class=\"card-body\">";
            listHtml +="<p class=\"card-text\">"+data[i].productName+"</p>";
            listHtml +="</div>";
            listHtml +="</div>";

        }
        $("#body").append(listHtml);
    });
});

function logout(){
    localStorage.removeItem('accesstoken');
    location.replace("/home");
}
