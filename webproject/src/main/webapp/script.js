/**
 * Generates a URL for the login button.
 */
function generateUrlForLoginButton() {
    fetch('/login').then(response => response.text()).then(response => {
        const loginButton = document.getElementById('login-button');
        loginButton.href = response;
    });
}
