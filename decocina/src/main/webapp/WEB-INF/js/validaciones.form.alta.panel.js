$(document).ready(function(){
    	
	$("#clienteForm").validate({
		
		errorClass: "form-alta-campo-error",
	    validClass: "form-alta-campo-ok",
	    
        rules: {
            "cliente.nombre"   : "required",
            "cliente.apellidos": "required",
            "cliente.telefono" : {"required":true, "telefono":true},
            
            "cliente.direccion.calle"		: "required",
            "cliente.direccion.codigoPostal": {"required":true, "codigoPostal":true},
            "cliente.direccion.zona.id"		: "required",
            "cliente.direccion.poblacion"	: "required"
        },
        messages: {
        	"cliente.nombre"   : "El nombre es obligatorio",
            "cliente.apellidos": "Los apellidos son obligatorios",
            "cliente.telefono" : {"required": "El teléfono es obligatorio", "telefono":"debe introducir un número de teléfono correcto"},
            "cliente.direccion.calle"		: "La calle, número, piso y letra son obligatorios",
            "cliente.direccion.codigoPostal":{"required": "El código postal es obligatorio", "codigoPostal":"Debe introducir un número de código postal correcto"},
            "cliente.direccion.zona.id"		: "La provincia es obligatoria",
            "cliente.direccion.poblacion"	: "La población es obligatoria"
        },
        highlight: function(element) {
        	
            $(element).closest('.form-group').addClass('has-error')}
        ,
        unhighlight: function(element) {
        	
            $(element).closest('.form-group').removeClass('has-error');
            $(element).closest('.form-group').addClass('form-alta-campo-ok');
        },
        submitHandler: function(form) {
        	
        	form.submit();
        	
        }
    });
	
	$.validator.addMethod("telefono",function(value,element){
		
		return /^[0-9]{8,12}$/.test(value) 
    });
	
	$.validator.addMethod("password",function(value,element){
		
		return /^[a-zA-Z0-9]{6,15}$/.test(value) 
    });
	
	$.validator.addMethod("codigoPostal",function(value,element){
		
		return /(0[1-9]|5[0-2]|[0-4][0-9])[0-9]{3}$/.test(value) 
    });
});