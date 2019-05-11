const init = function() {
    checkReady();
    getOnlineUsers();
    checkForAdmin();
};

const getOnlineUsers = function() {
    $.ajax({
        type: "GET",
        url: "/online/get"
    }).then(function(users) {
        let list = document.getElementById("online-list");
        let li;
        users.forEach(function(user) {
            li = document.createElement("li");
            li.innerText = user;
            list.appendChild(li);
        })
    })
};

const checkReady = function() {
    $.ajax({
        type: "GET",
        url: "/ready/get"
    }).then(function(ready){
        if (ready) {
            window.location.href = "/items"
        }
    })
};

const checkForAdmin = function() {
    $.ajax({
        type: "GET",
        url: "/username/get"
    }).then(function(user) {
        if (user == "russ") {
            let btn = document.getElementById("start-btn");
            btn.style.display = "block";
        }
    })
};

const readyUp = function() {
    $.ajax({
        type: "GET",
        url: "/admin/ready"
    }).then(function(){
        window.location.href = "/items"
    })
};