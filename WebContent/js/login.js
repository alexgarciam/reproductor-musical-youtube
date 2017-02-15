/**
 * codigo para el login
 */

$(function() {
    var button = $('#loginButton');
    var box = $('#loginBox');
    var form = $('#loginForm');
    button.removeAttr('href');
    button.mouseup(function(login) {
        box.toggle();
        button.toggleClass('active');
    });
    form.mouseup(function() { 
        return false;
    });
    $(this).mouseup(function(login) {
        if(!($(login.target).parent('#loginButton').length > 0)) {
            button.removeClass('active');
            box.hide();
        }
    });
    
    /*Codigo para crear cuenta*/
    $('#createAccount').click(function(){  
    	$('#div_tab_login_account').load('login2.html#tologin', function() {
			showLogin();
		});    	
	});
    
});


