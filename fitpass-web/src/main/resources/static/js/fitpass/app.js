/*!
 * js
 */

const container = document.querySelector(".container");

// Theme
let theme = localStorage.getItem('data-theme');
const checkboxSignIn = document.getElementById("switch-sign-in");

const changeThemeToDark = () => {
    document.documentElement.setAttribute("data-theme", "dark");
    localStorage.setItem("data-theme", "dark");
    checkboxSignIn.checked = true;
}

const changeThemeToLight = () => {
    document.documentElement.setAttribute("data-theme", "light");
    localStorage.setItem("data-theme", 'light');
    checkboxSignIn.checked = false;
}

if (theme === null) {
    theme = 'dark';
}

if (theme === 'dark') {
    changeThemeToDark();
}

if (theme === 'light') {
    changeThemeToLight();
}

checkboxSignIn.addEventListener('change', () => {
    let theme = localStorage.getItem('data-theme');
    if (theme === 'dark') {
        changeThemeToLight();
    } else {
        changeThemeToDark();
    }
});
