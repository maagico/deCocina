$(document).ready(function() {	
				
	var idFe = $("#formaEnvio").val();
	var idFp = $("#formaPago").val();
	
	if(idFe != ""){
		
		formaEnvio(idFe);
		formaPago(idFp, true);
	}
	
});

var feSel = false;

function formaEnvio(id){
	
	feSel = true;
	
	$("#formaEnvio").val(id);
	
	$("#fe").removeClass("panel-danger");
	$("#fe").addClass("panel-default");
	
	$("#fe > div > div > div > a").each(function(index, element){
		
		//Borde a gris.
		$(element).css("border", "1px solid rgb(221, 221, 221)");
		
		var idFe = element.id.split("-")[1];
		
		if(id == idFe){
			
			$(element).addClass("active");
		}else{
			
			$(element).removeClass("active");
		}
	});
	
	$("#fp").removeClass("panel-danger");
	$("#fp").addClass("panel-default");
	
	$("#fp > div > div > div > a").each(function(index, element){
		
		//Borde a gris.
		$(element).css("border", "1px solid rgb(221, 221, 221)");
		
		var idFp = element.id.split("-")[1];
	
		if(id == idFp){
			
			$(element).show();	
		}else{
			
			$(element).hide();
			
		}
		
		$(element).removeClass("active");
	
	});
	
	$("#formaPago").val("");
	
	$("#fp").show();
}

function formaPago(id , onLoad){
	
	$("#formaPago").val(id);
	
	$("#fp").removeClass("panel-danger");
	$("#fp").addClass("panel-default");
	
	$("#observaciones").removeClass("panel-display-none");
	
	$("#fp > div > div > div > a").each(function(index, element){
		
		//Borde a gris.
		$(element).css("border", "1px solid rgb(221, 221, 221)");
		
		var idFp = element.id.split("-")[2];
		
		if(id == idFp){
			
			$(element).addClass("active");	
		}else{
			
			$(element).removeClass("active");	
			
		}
	
	});
	
	var parametros = {
   		"idFormaPago" : id
	};
	
	$.ajax({
        data: parametros,
        url: 'seleccionar-forma-pago-rp',
        type: 'post',
        async: true,
        beforeSend:function(){			        	
        },
        success: function (data) {
        	
        	var json = JSON.parse(data);
        	
        	var pasoAdicional = json.paso_adicional;
        	var nombreBoton = json.nombre_boton;
        	
        	var confimarPedido = "Confirmar y finalizar"; 
        	
        	if(pasoAdicional == "true"){
        		
        		confimarPedido = nombreBoton;
        	}

        	$("#paso-dos").html(confimarPedido);
        	
        	if(!onLoad){
        		
        		ga("set", { page: "/seleccionar-forma-pago-rp", title: "Realizar pedido - Seleccionar forma de pago" });
        		ga("send", "pageview");
        	}
        }
    });
}

function confirmarPedido(){
	
	var formaEnvio = $("#formaEnvio").val();
	
	if(formaEnvio == ""){
		
		$("#fe").removeClass("panel-default");
		$("#fe").addClass("panel-danger");
		
		$("#fe > div > div > div > a").each(function(index, element){
			
			$(element).css("border", "1px solid rgb(235, 204, 209)");
			
		});
		
	}
	
	var formaPago = $("#formaPago").val();
	
	if(formaPago == "" && feSel){
		
		$("#fp").removeClass("panel-default");
		$("#fp").addClass("panel-danger");
		
		$("#fp > div > div > div > a").each(function(index, element){
				
			$(element).css("border", "1px solid rgb(235, 204, 209)");
			
		});
		
	}
	
	if(formaEnvio != "" && formaPago != ""){
		
		$("#pedidoForm").submit();
	}
}