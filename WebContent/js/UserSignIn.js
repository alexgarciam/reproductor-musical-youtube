/**
 *  Fichero para controlar el registro del usuario en la base de datos
 */


$(document).ready(function() {
		

	$("#nuevaListaUser2").validate({
			rules : {
				userPassword1Form : "required",
				userPassword2Form : {
					equalTo : "#userPassword1Form"
				},
				userNameForm : {
					remote: "checkUserName.do"
				},
				userEmailForm : {
					remote: "checkUserEmail.do"
				}
			},
			
			 submitHandler: function(form) 
			 {
				 formObj = $( '#' + form.id );
			    //prevent the default submission of the form
			    //e.preventDefault();
			    //run an AJAX post request to your server-side script, $this.serialize() is the data from your form being added to the request
			    $.ajax({
			        type: 'POST',
			        url: 'registrarUsuario.do',
			        dataType: 'json',
			        data: formObj.serialize(),
			        success: function(responseData, responseStatus, responseXml){
			            console.log( 'responseData is: ' + responseData );
			            console.log( 'responseStatus is: ' + responseStatus );
			            console.log( 'responseXML is: ' + responseXml );
			            
			            if(responseData == 1){
			            	console.log( 'Login succesful: ' + responseData.sentStatus );
			            	
			            	$("#userNameForm").val("");
			            	$("#userEmailForm").val("");
			            	$("#userPassword1Form").val("");
			            	$("#userPassword2Form").val("");
			            	
			            	//cierra el formulario
			            	$.fancybox.close();
			            	$.fancybox.open("&nbsp;&nbsp;&nbsp;<h3>La cuenta ha sido creada ;) </h3> Para poder usarla debe activarla en el enlace que hemos enviado a su email.  &nbsp;&nbsp;&nbsp;" );
							//setTimeout("$.fancybox.close()", 5000);
			            }
			            	else {
			                alert('se ha producido un error en el registro');
			                console.log( 'sentStatus equals something other than 1: ' + responseData.sentStatus );
			            }
			        },
			        error: function(errorXml, errorStatus, errorError){
			            console.log( 'ErrorXml is: ' + errorXml );
			            console.log( 'errorStatus is: ' + errorStatus );
			            console.log( 'errorError is: ' + errorError );
			        },
			        complete: function(completeXml, completeStatus){
			            console.log( 'completeXML is: ' + completeXml );
			            console.log( 'completeStatus is: ' + completeStatus );
			        }
			    });
			 }	 			
		});
	});
	
	jQuery.extend(jQuery.validator.messages, {
	    required: "Este campo es obligatorio.",
	    remote: jQuery.format("{0} ya existe"),
	    email: "Introduce un email valido.",
	    url: "Please enter a valid URL.",
	    date: "Please enter a valid date.",
	    dateISO: "Please enter a valid date (ISO).",
	    number: "Please enter a valid number.",
	    digits: "Please enter only digits.",
	    creditcard: "Please enter a valid credit card number.",
	    equalTo: "Vuelve a escribir lo mismo.",
	    accept: "Please enter a value with a valid extension.",
	    maxlength: jQuery.validator.format("Please enter no more than {0} characters."),
	    minlength: jQuery.validator.format("Introduzca al menos {0} caracteres."),
	    rangelength: jQuery.validator.format("Please enter a value between {0} and {1} characters long."),
	    range: jQuery.validator.format("Please enter a value between {0} and {1}."),
	    max: jQuery.validator.format("Please enter a value less than or equal to {0}."),
	    min: jQuery.validator.format("Please enter a value greater than or equal to {0}.")
	});