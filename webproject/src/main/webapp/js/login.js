/**
 * Goes to the Google login page.
 */
function redirectToGoogleLogin() {
    fetch('/login').then(response => response.text()).then(response => {
        window.location.href = response;
    });
}
<<<<<<< HEAD:webproject/src/main/webapp/js/login.js
=======
/**
 * Logs out of the current Google account.
 */
function logout() {
    fetch('/logout').then(response => response.text()).then(response => {
        window.location.href = response;
    });
}
>>>>>>> 4ee36a89dc9d259a42bd0e9804e236e18f23440e:webproject/src/main/webapp/script.js
