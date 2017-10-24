(function () {
    'use strict';

    var phoneBook = {
        "contacts": []
    };

    $(document).ready(init);
    function init() {
        $
            .ajax({
                async: true,
                type: "GET",
                url: "http://www.mocky.io/v2/581335f71000004204abaf83",
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                    phoneBook.contacts = sortByFamilyName(data.contacts);
                    initTable(phoneBook.contacts);
                }
            });

        $("#sortByTown").click(function () {
            initTable(sortByTown(phoneBook.contacts));
            $("#sortByTown").css({"display": "none"});
            $("#sortByName").css({"display": "block"});
        });
        $("#sortByName").click(function () {
            initTable(sortByFamilyName(phoneBook.contacts));
            $("#sortByName").css({"display": "none"});
            $("#sortByTown").css({"display": "block"});
        });

        $("#searchName").on('input', function () {
            initTable(searchByName(phoneBook.contacts, $("#searchName").val().trim()));
        })
        $("#searchPhone").on('input', function () {
            initTable(searchByPhone(phoneBook.contacts, $("#searchPhone").val().trim()));
        })
    }

    function searchByName(contacts, searchItem) {
        var result = [];
        contacts.forEach(function (element) {
            if (element.name.includes(searchItem)) {
                result.push(element);
            }
        });
        return result;
    }

    function searchByPhone(contacts, searchItem) {
        if (searchItem === "") {
            return contacts;
        }
        var result = [];
        searchItem = searchItem.charAt(0) === "0" ? "+44" + searchItem.substring(1) : searchItem;
        contacts.forEach(function (element) {
            if (element.phone_number.includes(searchItem)) {
                result.push(element);
            }
        });
        return result;
    }

    function initTable(contacts) {
        $("table").remove();
        var table = document.createElement("table");
        var tRowHeader = document.createElement("tr");
        var headName = document.createElement("th");
        headName.innerHTML = "Name";
        var headPhoneNumber = document.createElement("th");
        headName.innerHTML = "Phone number";
        var headAddress = document.createElement("th");
        headName.innerHTML = "Address";
        tRowHeader.appendChild(headName);
        tRowHeader.appendChild(headPhoneNumber);
        tRowHeader.appendChild(headAddress);
        table.appendChild(tRowHeader);
        contacts.forEach(function (element) {
            var tRow = document.createElement("tr");
            var name = document.createElement("td");
            name.innerHTML = element.name;
            var phoneNumber = document.createElement("td");
            phoneNumber.innerHTML = element.phone_number;
            var address = document.createElement("td");
            address.innerHTML = element.address;
            tRow.appendChild(name);
            tRow.appendChild(phoneNumber);
            tRow.appendChild(address);
            table.appendChild(tRow);
        });
        $("#phoneBook").append(table);
    }

    function sortByTown(contacts) {
        return contacts.sort(function (element1, element2) {
            var town1 = getTown(element1.address);
            var town2 = getTown(element2.address)
            if (town1 < town2) {
                return -1;
            }
            if (town1 > town2) {
                return 1;
            }
            return 0;
        });
    }

    function sortByFamilyName(contacts) {
        return contacts.sort(function (element1, element2) {
            var lastName1 = getLastName(element1.name.toLowerCase());
            var lastName2 = getLastName(element2.name.toLowerCase());
            if (lastName1 < lastName2) {
                return -1;
            }
            if (lastName1 > lastName2) {
                return 1;
            }
            return 0;
        });
    }

    function getLastName(name) {
        return name.split(" ")[1];
    }

    function getTown(address) {
        var addressArray = address.split(",");
        return addressArray[addressArray.length - 2].trim();

    }

}());
