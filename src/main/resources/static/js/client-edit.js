const editClientForm = document.getElementById("edit-client-form");

editClientForm.addEventListener("submit", (event) => {
    if(!isNameAndSurnameValid()) {
        alert("Ім'я та/або прізвище введено невірно!");
        event.preventDefault();
    }
});

function isNameAndSurnameValid(){
    const lastNameStr = document.getElementById("client-lastname").value.trim();
    const firstNameStr = document.getElementById("client-name").value.trim();

    const englishWordPattern = /^[A-Z][a-z]+$/;
    const ukrainianWordPattern = /[А-ЯЄІЇ][а-яієї]+$/;

    return (englishWordPattern.test(lastNameStr) && englishWordPattern.test(firstNameStr))
        || (ukrainianWordPattern.test(lastNameStr) && ukrainianWordPattern.test(firstNameStr));
}

