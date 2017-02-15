<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>


        
        <link rel="stylesheet" type="text/css" href="css/login2/style3.css" />
		<link rel="stylesheet" type="text/css" href="css/login2/animate-custom.css" />
		
		<script>
		$(document).ready(function() {
			

			$("#registerForm").validate({
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
		</script>
  
                <div id="container_demo" >
                    <!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
                    <a class="hiddenanchor" id="toregister"></a>
                    <a class="hiddenanchor" id="tologin"></a>
                    
                    <div id="wrapper">
                        <div id="login" class="animate form">
                        
                        <c:if test="${not empty param.authfailed}">							
						    <span id="infomessage" class="errormessage" >						    	
						    	<span id="errorLogin" style="font-size: 12pt;color: red"><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></span> 
						    </span>
						    <script>
							    var error=$("#errorLogin").text();
							    //alert(error);
						    </script>
						</c:if>	
						
                        
                            <form  action="j_spring_security_check" autocomplete="on" method="post"> 
                                <h1><img src="images/login/logo.png" height="75" ></h1> 
                                <p> 
                                    <label for="username" class="uname" data-icon="u" > Usuario </label>
                                    <input id="username" name="j_username" required="required" type="text" placeholder="Tu usuario"/>
                                </p>
                                <p> 
                                    <label for="password" class="youpasswd" data-icon="p"> Password </label>
                                    <input id="password" name="j_password" required="required" type="password" placeholder="Tu password" /> 
                                </p>
                               
                                <p class="keeplogin"> 
									<input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping" /> 
									<label for="loginkeeping">Recu&eacute;rdame!</label>
								</p>
								
                                <p class="login button"> 
                                    <input type="submit" value="Acceder" /> 
								</p>
								
								<p>
									<span style="text-align: center;">                           
									No est&aacute;s inscrito? <a href="#toregister" class="to_register"> Inscr&iacute;bete</a>
									</span>		
								</p>
								
                                <p class="change_link">    
                                								
								</p>
                            </form>
                            
                        </div>

                        <div id="register" class="animate form">
                            <form id="registerForm"  action="registrarUsuario.do" autocomplete="on"> 
                                <h1> Registro </h1> 
                                <p> 
                                    <label for="userNameForm" class="uname" data-icon="u">Tu usuario</label>
                                    <input id="userNameForm" name="userNameForm" required="required" type="text" placeholder="mysuperusername690" />
                                </p>
                                <p> 
                                    <label for="userEmailForm" class="uname" data-icon="u">Tu email</label>
                                    <input id="userEmailForm" name="userEmailForm" required="required" type="email" placeholder="thismy@email.com" />
                                </p>
                                <p> 
                                    <label for="userPassword1Form" class="youpasswd" data-icon="p">Tu password </label>
                                    <input id="userPassword1Form" name="userPassword1Form" required="required" type="password" placeholder="ejemplo: X8df0EO"/>
                                </p>
                                <p> 
                                    <label for="userPassword2Form" class="youpasswd" data-icon="p">Repite tu password </label>
                                    <input id="userPassword2Form" name="userPassword2Form" required="required" type="password" placeholder="repite la password nuevamente"/>
                                </p>
                                <p class="signin button"> 
									<input type="submit" value="Sign up"/> 
								</p>
								<p>
									<a href="#tologin" class="to_register"> Ya estoy registrado </a>
								</p>
                                <p class="change_link">  									
									
								</p>
                            </form>
                        </div>
						
                    </div>
                </div>  
            