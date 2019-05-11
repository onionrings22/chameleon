const getVotes = function() {
    $.ajax({
        type: "GET",
        url: "/votes/get"
    }).then(function(votes) {
        let obj = {};
        votes.forEach(function(vote) {
            if (obj.hasOwnProperty(vote)) {
                obj[vote]++;
            } else {
                obj[vote] = 1;
            }
        });
        let table = document.getElementById("vote-table");
        let td;
        let tr;
        Object.keys(obj).forEach(function(user) {
            tr = document.createElement("tr");
            td = document.createElement("td");
            td.innerText = user;
            tr.appendChild(td);
            td = document.createElement("td");
            td.innerText = obj[user];
            tr.appendChild(td);
            table.appendChild(tr);
        })
    })
};

const getUser = function() {
    $.ajax({
        type: "GET",
        url: "/username/get"
    }).then(function(user) {
        username = user;
        if (user == "russ") {
            let btn = document.getElementById("allow-reveal-btn");
            btn.style.display = "block";
        }
    })
};

const checkReveal = function() {
    $.ajax({
        type: "GET",
        url: "/reveal/get"
    }).then(function(reveal) {
        if (reveal) {
            window.location.href = "/reveal";
        } else {
            getVotes();
            getUser();
        }
    })
};

const setReveal = function() {
    $.ajax({
        type: "GET",
        url: "/admin/reveal"
    }).then(function(user) {
        window.location.href = "/reveal";
    })
};