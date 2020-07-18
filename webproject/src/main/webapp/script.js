/**
 * Goes to the Google login page.
 */
function redirectToGoogleLogin() {
    fetch('/login').then(response => response.text()).then(response => {
        window.location.href = response;
    });
}

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
        document.getElementById('logout-button').insertAdjacentHTML('beforeend', textToAppend);
    });
}
