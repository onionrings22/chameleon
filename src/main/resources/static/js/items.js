let itemNum;
let username;
let isChameleon = false;

const init = function() {
    checkVotingDone();
    getCategory();
    getUser();
    getVotingOptions();
    getChameleon();
    makeTable();
};

const getCategory = function() {
    $.ajax({
        type: "GET",
        url: "/category/get"
    }).then(function(cat) {
        itemNum = cat.num;
        let header = document.getElementById("category");
        header.innerText = cat.name;
    })
};

const getUser = function() {
    $.ajax({
        type: "GET",
        url: "/username/get"
    }).then(function(user) {
        username = user;
        if (user == "russ") {
            let btn = document.getElementById("close-vote-btn");
            btn.style.display = "block";
        }
    })
};

const getVotingOptions = function() {
    $.ajax({
        type: "GET",
        url: "/online/get"
    }).then(function(users) {
        let select = document.getElementById("select-vote");
        let op;
        users.forEach(function(user) {
            op = document.createElement("option");
            op.innerText = user;
            op.value = user;
            select.appendChild(op);
        })
    })
};

const getChameleon = function() {
    $.ajax({
        type: "GET",
        url: "/chameleon/get"
    }).then(function(chameleon) {
        if (chameleon == username) {
            isChameleon = true;
            let header = document.getElementById("chameleon");
            header.innerText = "You are the chameleon!";
            header.style.display = "block";
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
                if (index == itemNum && !isChameleon) {
                    td.classList.add("chosen");
                }
                tr.appendChild(td);
            }
            table.appendChild(tr);
        }
    })
};

const vote = function() {
    let select = document.getElementById("select-vote");
    let suspect = select.options[select.selectedIndex].value;
    $.ajax({
        type: "GET",
        url: "/vote?suspect=" + suspect
    }).then(function() {
        $("#vote-btn").children().prop('disabled',true);
    })
};

const checkVotingDone = function() {
    $.ajax({
        type: "GET",
        url: "/votingDone/get"
    }).then(function(votingDone) {
        if (votingDone) {
            window.location.href = "/votes"
        }
    })
};

const endVoting = function() {
    $.ajax({
        type: "GET",
        url: "/admin/endVoting"
    }).then(function() {
        window.location.href = "/votes"
    })
};