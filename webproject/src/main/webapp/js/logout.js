/**
 * Logs out of the current Google account.
 */
function logout() {
    fetch('/logout').then(response => response.text()).then(response => {
        window.location.href = response;
    });
}

/**
 * Appends the user's username to the logout button.
 */
function appendUsernameToLogoutButton() {
    fetch('/username').then(response => response.text()).then(response => {
        const textToAppend = ' (' + response + ')';
        const logoutButtons = document.getElementsByClassName('logout-button');
        // Append to logout buttons for desktop and mobile sites
        for (let i = 0; i < logoutButtons.length; i++) {
            logoutButtons[i].innerHTML += textToAppend;
        }
    });
}
