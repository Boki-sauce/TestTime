window.onload = function () {
    var topbtn = document.getElementById("btn");
    var timer = null;
    var pagelookheight = document.documentElement.clientHeight;
console(pagelookheight);
    window.onscroll = function (){
        var backtop = document.body.scrollTop;
        if (backtop >= pagelookheight){
            top.style.display = "block";
        }else{
            topbtn.style.display = "none";
        }
    }

    topbtn.onclick = function(){

        timer = setInterval(function () {
            var backtop = document.body.scrollTop;
            var speedtop = backtop/5;
            document.body.scrollTop = backtop - speedtop;
            if (backtop == 0){
                clearInterval(timer);
          }
        }, 30);
    }
}

$("#gotop").hide();
$(window).scroll(function () {
    if ($(window).scrollTop() > 100) {
        $("#gotop").fadeIn()
    } else {
        $("#gotop").fadeOut()
    }
});
$("#gotop").click(function () {
    $('html,body').animate({
        'scrollTop': 0
    }, 500)
});