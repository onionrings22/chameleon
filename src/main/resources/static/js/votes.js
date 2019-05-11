const init = function() {
    getVotes();
};

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