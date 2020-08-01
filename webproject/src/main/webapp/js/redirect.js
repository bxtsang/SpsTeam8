$(document).ready(function() { 
    redirect();
 });

/**
 * Redirects to landing page if logged out.
 */
function redirect() {
    fetch('/redirect').then(response => response.text()).then(response => {
        if ($.trim(response)){   
            window.location.href = response;
        }
    });
}
