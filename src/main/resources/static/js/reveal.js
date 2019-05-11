let username;

const init = function() {
    checkDone();
    getUser();
    getChameleon();
    makeTable();
};

const getUser = function() {
    $.ajax({
        type: "GET",
        url: "/username/get"
    }).then(function(user) {
        username = user;
        if (user == "russ") {
            let btn = document.getElementById("restart-btn");
            btn.style.display = "block";
        }
    })
};

const getChameleon = function() {
    $.ajax({
        type: "GET",
        url: "/chameleon/get"
    }).then(function(chameleon) {
        let header = document.getElementById("chameleon");
        if (chameleon == username) {
            header.innerText = "You are the chameleon!";
        } else {
            header.innerText = "The chameleon is " + chameleon + "!";
        }
    })
};

const makeTable = function() {
    $.ajax({
        type: "GET",
        url: "/items/get"
    }).then(function(items) {
        let table = document.getElementById("items");
        let tr;
        let td;
        let index;
        for(let i = 0; i < 5; i++) {
            tr = document.createElement("tr");
            for (let j = 0; j < 5; j++) {
                td = document.createElement("td");
                index = (i*5) + j;
                td.innerText = items[index];
                tr.appendChild(td);
            }
            table.appendChild(tr);
        }
    })
};

const checkDone = function() {
    $.ajax({
        type: "GET",
        url: "/done/get"
    }).then(function(done) {
        if (done) {
            window.location.href = "/";
        }
    })
};

const resetGame = function() {
    $.ajax({
        type: "GET",
        url: "/admin/resetGame"
    }).then(function() {
        window.location.href = "/";
    })
};

