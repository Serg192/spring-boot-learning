const table = document.querySelector("table");

function deleteConfirmation() {
    return confirm('Ви впевнені що хочете вилучити даного клієнта?');
}

function onFilterButton(){
    const selectedOption = document.querySelector('input[name="radioFilter"]:checked').value;

    switch (selectedOption) {
        case "address":
            filterBy(findColumnIndexByHeader("Адреса"));
            break;
        case "NP":
           filterBy(findColumnIndexByHeader("Номер відділення Нової пошти"));
           break;
        case "UkrPost":
            filterBy(findColumnIndexByHeader("Номер відділення Укрпошти"));
            break;
        default:
            break;
    }
}

function filterBy(colIndex) {
    table.querySelectorAll("tbody tr").forEach(row => {
        let shouldDisplay = true;
        const column = row.querySelectorAll("td")[colIndex];
        if(column.textContent.length === 0)
            shouldDisplay = false;
        row.style.display = shouldDisplay ? '' : 'none';
    });
}

function findColumnIndexByHeader(headerName) {
    const headers = Array.from(table.querySelectorAll("thead th"));
    return headers.findIndex(header => header.textContent.trim() === headerName);
}

function onSearch(event){
    const column1 = findColumnIndexByHeader("Код адреси");
    const column2 = findColumnIndexByHeader("Область");
    const column3 = findColumnIndexByHeader("Населений пункт");

    let pattern = new RegExp(document.getElementById("search-by-str").value, 'i');

    table.querySelectorAll("tbody tr").forEach(row => {
        const op1 = row.querySelectorAll("td")[column1].textContent;
        const op2 = row.querySelectorAll("td")[column2].textContent;
        const op3 = row.querySelectorAll("td")[column3].textContent;

        row.style.display = pattern.test(op1) || pattern.test(op2) || pattern.test(op3) ? '' : 'none';
    });
}