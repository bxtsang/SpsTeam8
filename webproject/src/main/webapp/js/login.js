/**
 * Goes to the Google login page.
 */
function redirectToGoogleLogin() {
    fetch('/login').then(response => response.text()).then(response => {
        window.location.href = response;
    });
}
