const errorMsgBox = document.getElementById("error-message");
const table = document.querySelector("table");

window.history.pushState(null, null, window.location.href);
window.onpopstate = function () {
    window.history.go(1);
};

function deleteConfirmation() {
    return confirm('Ви впевнені що хочете вилучити даного клієнта?');
}

function errorWasReadBtn(event) {
    errorMsgBox.style.display = 'none';
}

function onFilterButton(event) {
    const selectedOption = document.querySelector('input[name="radioFilter"]:checked').value;
    const gender = document.getElementById('client-gender').value;
    const vip = document.getElementById('client-vip').value;

    let filterData = [];

    const genderFilterData = {
        columnId: findColumnIndexByHeader("Стать"),
        value: gender
    };

    const vipFilterData = {
        columnId: findColumnIndexByHeader("VIP клієнт"),
        value: vip
    };

    switch (selectedOption) {
        case "gender":
            filterData.push(genderFilterData);
            break;
        case "vip":
            filterData.push(vipFilterData);
            break;
        case "both":
            filterData.push(genderFilterData);
            filterData.push(vipFilterData);
            break;
        default:
            break;
    }

    filterBy(filterData);
}

function filterBy(filterData) {
    table.querySelectorAll("tbody tr").forEach(row => {
        let shouldDisplay = true;

        filterData.forEach(filter => {
            const columnId = filter.columnId;
            const value = filter.value;
            const column = row.querySelectorAll("td")[columnId];
            const content = column.textContent;

            if (content !== value) {
                shouldDisplay = false;
            }
        });

        row.style.display = shouldDisplay ? '' : 'none';
    });
}


function findColumnIndexByHeader(headerName) {
    const headers = Array.from(table.querySelectorAll("thead th"));
    return headers.findIndex(header => header.textContent.trim() === headerName);
}

let ascSorting = true;
function onSortBySurname(event) {
    const columnId = findColumnIndexByHeader("Прізвище");

    const rows = Array.from(table.rows);
    rows.shift(); // Remove the header row

    rows.sort((a, b) => {
        const cellA = a.cells[columnId].textContent;
        const cellB = b.cells[columnId].textContent;
        return ascSorting ? cellA.localeCompare(cellB, "ua", { sensitivity: "base" }) :
            cellB.localeCompare(cellA, "ua", { sensitivity: "base" }) ;
    });

    rows.forEach(row => table.tBodies[0].appendChild(row));

    ascSorting = !ascSorting;
}

function onSearchBySurname(event) {
    const columnID = findColumnIndexByHeader("Прізвище");
    let pattern = new RegExp(document.getElementById("search-by-surname").value, 'i');

    table.querySelectorAll("tbody tr").forEach(row => {
        const surname = row.querySelectorAll("td")[columnID].textContent;
        row.style.display = pattern.test(surname) ? '' : 'none';
    });
}
