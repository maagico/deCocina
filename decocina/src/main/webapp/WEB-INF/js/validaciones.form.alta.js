$(document).ready(function(){
    	
	$("#clienteForm").validate({
		
		errorClass: "form-alta-campo-error",
	    validClass: "form-alta-campo-ok",
	    
        rules: {
            "cliente.nombre"   : "required",
            "cliente.apellidos": "required",
            "cliente.email"    : {"required":true, "email": true, "existe-email":true},
            "cliente.password" : {"required":true, "password":true},
            "cliente.telefono" : {"required":true, "telefono":true},
            
            "cliente.direccion.calle"		: "required",
            "cliente.direccion.codigoPostal": {"required":true, "codigoPostal":true},
            "cliente.direccion.zona.id"		: "required",
            "cliente.direccion.poblacion"	: "required",
            "politica":"required"
        },
        messages: {
        	"cliente.nombre"   : "El nombre es obligatorio",
            "cliente.apellidos": "Los apellidos son obligatorios",
            "cliente.email"    : {"required": "El email es obligatorio", "email":"Introduce un email válido", "existe-email":"El email introducido ya está dado de alta en deCocina"},
            "cliente.password" : {"required":" La contraseña es obligatoria", "password":"La contraseña debe tener entre 6 y 15 caracteres (se permiten letras y números)"},
            "cliente.telefono" : {"required": "El teléfono es obligatorio", "telefono":"Debes introducir un número de teléfono correcto"},
            
            "cliente.direccion.calle"		: "La calle, número, piso y letra son obligatorios",
            "cliente.direccion.codigoPostal":{"required": "El código postal es obligatorio", "codigoPostal":"Debes introducir un número de código postal correcto"},
            "cliente.direccion.zona.id"		: "La provincia es obligatoria",
            "cliente.direccion.poblacion"	: "La población es obligatoria",
            "politica": "Debes aceptar la política de privacidad"
        },
        highlight: function(element) {
        	
            $(element).closest('.form-group').addClass('has-error');
        }
        ,
        unhighlight: function(element) {
        	
            $(element).closest('.form-group').removeClass('has-error');
            $(element).closest('.form-group').addClass('form-alta-campo-ok');
        },
        errorPlacement: function(error, element) {
        	
        	if (element.attr("type") == "checkbox") {
        		
        		error.insertAfter($("#error_politica"));
            } else {
                
            	error.insertAfter(element);
            }
	    },
        submitHandler: function(form) {
        	
        	form.submit();
        	
        }
    });
	
	$.validator.addMethod("telefono",function(value,element){
		
		return /^[0-9]{8,12}$/.test(value);
    });
	
	$.validator.addMethod("password",function(value,element){
		
		return /^[a-zA-Z0-9]{6,15}$/.test(value);
    });
	
	$.validator.addMethod("codigoPostal",function(value,element){
		
		return /(0[1-9]|5[0-2]|[0-4][0-9])[0-9]{3}$/.test(value);
    });
	
	$.validator.addMethod("existe-email",function(value, element){
		
		var resultado = false;
		
		var parametros = {
                "email" : value,
		};
		
        $.ajax({
	        data: parametros,
	        url: 'existe-email',
	        type: 'post',
	        async: false,
	        success: function (data) {
	        	
	        	var json = JSON.parse(data);
	        	var existe = json.existe;
	        	
	        	resultado = existe == "false";
	        }
        });
        
		return resultado;
		
	});
	
	$('#validar-form').click(function(){
		
		$("#clienteForm").submit();
		return false;
	});
	
});

function abrirPopup()
{
	BootstrapDialog.alert({
		
		title: "<h4>Política de privacidad</h4>",
		message: $("<div></div>").load("politica-privacidad"),
        type: BootstrapDialog.TYPE_INFO,
        closable: true,
        draggable: false
        
    });
}
