$(document).ready(function() {
				
	$('.articulo-cesta').each(function(i, obj) {
		
		var id = obj.id;
		
		$("#" + id).TouchSpin({
	           min: 1,
	           max: 99,
	           step: 1,
	           verticalbuttons: true				           
	    });
		
		$("#" + id).on('touchspin.on.stopupspin', function (){
			
			actualizarCantidad(id);
			
		});
		
		$("#" + id).on('touchspin.on.stopdownspin', function (){
			
			actualizarCantidad(id);
			
		});
		
	});
	
	function actualizarCantidad(id){
		
		var cantidad = $("#" + id).val();
		
		var idArticulo = id.split("-")[1];
		
		var parametros = {
                "id" : idArticulo,
                "cantidad" : cantidad
		};
		
		$.ajax({
			
	        data: parametros,
	        url: 'actualizar-cantidad',
	        type: 'post',
	        async: true,
	        beforeSend:function(){
	        	
	        	$("#precio-total-" + idArticulo).html("");
	        	
	        	$("#img-espera-" + idArticulo).css("visibility", 'visible');
	        	
	        	$("#gasto-forma-envio").html("");
	        	
	        	$("#img-espera-gasto-forma-envio").css("visibility", 'visible');
	        	
	        	$("#img-espera-subtotal").show();
	        	$("#img-espera-total").show();
	        	
	        	$("#subtotal-precio").hide();
	        	$("#total-precio").hide();
	        },
	        success: function (data) {
	        
	        	var json = JSON.parse(data);
				
	        	var articulos = json.articulos;
	        	
	        	for(var i = 0;i < articulos.length; i++){
	        		
	        		var articulo = articulos[i];
	        		
	        		if(articulo.id == idArticulo){
	        			
	        			var precioTotal = articulo.precioTotal;
	        			
	        			$("#img-espera-" + idArticulo).css("visibility", 'hidden');
	        			
	        			$("#precio-total-" + idArticulo).html(precioTotal);
	        	
	        		}
	        	}
	        	
	        	var gastoFormaEnvio = json.gastoFormaEnvio;
	        	$("#img-espera-gasto-forma-envio").css("visibility", 'hidden');
	        	$("#gasto-forma-envio").html(gastoFormaEnvio);
	        	$("#gasto-forma-envio").show();
	        	
	        	$("#img-espera-subtotal").hide();
	        	$("#subtotal-precio").show();
	        	
	        	var subTotal = json.subTotal;
	        	$("#subtotal-precio").html(subTotal);
	        	
	        	$("#img-espera-total").hide();
	        	$("#total-precio").show();
	        	
	        	var total = json.total;
	        	$("#total-precio").html(total);
	     		
	        //ga("set", { page: "/actualizar-cantidad", title: "Cesta - Actualizar cantidad" });
	        	//ga("send", "pageview");
	        }
		
        });
	}
	
	$("#zonas").change(function() {
			
		var idZona = $("#zonas").val();
		
		$("#gasto-forma-envio").html("");
    	$("#forma-pago").hide();
    	
    	$("#total").hide();
    	$("#total-precio").html("");
		
		if(idZona == ""){
			
			$("#forma-envio").hide();
			
			$("#msg-gastos").html("Para calcular los gastos de envío:");
			
		}else{
			
			$("#msg-gastos").html("Gastos para:");
		}
		
		var parametros = {
        	"idZona" : idZona
		};
		
		$.ajax({
			
	        data: parametros,
	        url: 'recuperar-formas-envio',
	        type: 'post',
	        async: true,
	        beforeSend:function(){
	        	
	        },
	        success: function (data) {
	        
	        	var json = JSON.parse(data);
				
	        	var formasEnvio = json.formasEnvio;
	        	
	        	var formasEnvioSelect = document.getElementById("formasEnvio");
	        	
	        	for (var i = formasEnvioSelect.length - 1; i >= 0; i--) {
	        		formasEnvioSelect.remove(i);
	        	}
	        	
	        	formasEnvioSelect.options[0] = new Option("Selecciona la forma de envío", "");
	        	
	        	for(var i = 0;i < formasEnvio.length; i++){
	        		
	        		var id = formasEnvio[i].id;
	        		var nombre = formasEnvio[i].nombre;
	        		
	        		formasEnvioSelect.options[i + 1] = new Option(nombre, id);
	        	}
	        	
	        	$("#forma-envio").show();
	        	
	        //ga("set", { page: "/recuperar-formas-envio", title: "Cesta - Recuperar formas de envío" });
	        	//ga("send", "pageview");
	        }
		
        });
		
	});
	
	$("#formasEnvio").change(function() {
		
		var idFormaEnvio = $("#formasEnvio").val();
		
		var parametros = {
        	"idFormaEnvio" : idFormaEnvio
		};
		
		if(idFormaEnvio == ""){
			$("#formasPago").hide();
		}
		
		$("#gasto-forma-envio").html("");
		$("#gasto-forma-pago").html("");
		$("#total-precio").html("");
		
		$.ajax({
			
	        data: parametros,
	        url: 'recuperar-gasto-forma-envio',
	        type: 'post',
	        async: true,
	        beforeSend:function(){
	        	
	        	$("#img-espera-gasto-forma-envio").css("visibility", 'visible');
	        	
	        },
	        success: function (data) {
	        
	        	var json = JSON.parse(data);
				
	        	$("#img-espera-gasto-forma-envio").hide();
	        	
	        	var gasto = json.gasto;
	        	
	        	$("#gasto-forma-envio").html(gasto);
	        	$("#gasto-forma-envio").show();
	        	
	        	//ga("set", { page: "/recuperar-gasto-forma-envio", title: "Cesta - Recuperar gasto de la formas de envío" });
	        	//ga("send", "pageview");
	        	
				cargarFormasPago();
					
	        }
		
        });
		
	});
	
	
	function cargarFormasPago(){
		
		var idFormaEnvio = $("#formasEnvio").val();
		
		var parametros = {
        	"idFormaEnvio" : idFormaEnvio
		};
		
		if(idFormaEnvio != ""){
			$("#formasPago").show();
		}
		
		$.ajax({
			
	        data: parametros,
	        url: 'recuperar-formas-pago',
	        type: 'post',
	        async: true,
	        beforeSend:function(){
	        	
	        },
	        success: function (data) {
	        
	        	var json = JSON.parse(data);
				
	        	var formasPago = json.formasPago;
	        	
	        	var formasPagoSelect = document.getElementById("formasPago");
	        	
	        	for (var i = formasPagoSelect.length - 1; i >= 0; i--) {
	        		formasPagoSelect.remove(i);
	        	}
	        	
	        	formasPagoSelect.options[0] = new Option("Selecciona la forma de pago", "");
	        	
	        	for(var i = 0;i < formasPago.length; i++){
	        		
	        		var id = formasPago[i].id;
	        		var nombre = formasPago[i].nombre;
	        		
	        		formasPagoSelect.options[i + 1] = new Option(nombre, id);
	        	}
	        	
	        	$("#forma-pago").show();
	        	
	        	//ga("set", { page: "/recuperar-formas-pago", title: "Cesta - Recuperar las formas de pago" });
	        	//ga("send", "pageview");
	        }
		
        });
	}
	
	$("#formasPago").change(function() {
		
		var idFormaPago = $("#formasPago").val();
		
		var parametros = {
        	"idFormaPago" : idFormaPago
		};
		
		$.ajax({
			
	        data: parametros,
	        url: 'recuperar-gasto-forma-pago',
	        type: 'post',
	        async: true,
	        beforeSend:function(){
	        	
	        	if(idFormaPago != ""){
	        		
					$("#img-espera-gasto-forma-pago").css("visibility", 'visible');
					$("#gasto-forma-pago").html("");
		        }
	        	
	        	$("#total").hide();
	        	
        		$("#img-espera-total").show();
        		$("#gasto-forma-pago").hide();
        		$("#total-precio").hide();
	        	
	        },
	        success: function (data) {
	        
	        	
	        	if(idFormaPago != ""){
	        		
		        	var json = JSON.parse(data);
					
		        	var gasto = json.gasto;
		        	
		        	$("#img-espera-gasto-forma-pago").css("visibility", 'hidden');
		        	
		        	$("#gasto-forma-pago").html(gasto);
		        	$("#gasto-forma-pago").show();
					
		        	$("#img-espera-total").hide();
		        	$("#total-precio").show();
		        	
		        	var total = json.total;
		        	$("#total-precio").html(total);
	        		
	        		$("#total").show();
	        		
	        		//ga("set", { page: "/recuperar-gasto-forma-pago", title: "Cesta - Recuperar gasto de la forma de pago" });
		        	//ga("send", "pageview");
	        	}
	        }
		
        });
});
});

function eliminar(id){
	
	var parametros = {
    	"id" : id,
	};
	
	$.ajax({
		
        data: parametros,
        url: 'eliminar-articulo',
        type: 'post',
        async: true,
        beforeSend:function(){
        	
        	$("#precio-total-" + id).html("");
        	
        	$("#img-espera-" + id).css("visibility", 'visible');
        	
        	$("#gasto-forma-envio").html("");
        	
        	$("#img-espera-gasto-forma-envio").css("visibility", 'visible');
        	
        	$("#subtotal-precio").hide();
        	$("#total-precio").hide();
        	
        	$("#img-espera-subtotal").show();
        	$("#img-espera-total").show();
        	
        },
        success: function (data) {
        
        	var json = JSON.parse(data);
			
        	var idArticuloEliminar = json.eliminar;
        	
        	if(idArticuloEliminar != ""){
        		
        		$("#fila-titulo-" + idArticuloEliminar).hide();
        		$("#fila-" + idArticuloEliminar).hide();
	        	$("#fila-eliminar-" + idArticuloEliminar).hide();
        	}
			
        	var gastoFormaEnvio = json.gastoFormaEnvio;
        	$("#img-espera-gasto-forma-envio").css("visibility", 'hidden');
        	$("#gasto-forma-envio").html(gastoFormaEnvio);
        	$("#gasto-forma-envio").show();
        	
        	$("#img-espera-subtotal").hide();
        	
        	var subTotal = json.subTotal;
        	$("#subtotal-precio").show();
        	$("#subtotal-precio").html(subTotal);
        	
        	$("#img-espera-total").hide();
        	
        	var total = json.total;
        	$("#total-precio").show();
        	$("#total-precio").html(total);
        	
        	var articulos = json.articulos;
			
        	if(articulos.length == 0){
        			
        		$("#btn-realizar-pedido").hide();
        		$("#subtotal").hide();
        		$("#gastos-envio").hide();
        		$("#total").hide();
        		$("#no-hay-articulos-cesta-sup").show();
        		$("#no-hay-articulos-cesta").show();
        		
        		$("#calculo-gastos-envio").hide();
        		
        		$("#zona").hide();
        		$("#forma-envio").hide();
        		$("#forma-pago").hide();
        		
        		$("#pides-hoy").hide();
        		$("#no-envio-a-provincia").hide();
        	}
        	
        	//ga("set", { page: "/eliminar-articulo", title: "Cesta - Eliminar artículo" });
        	//ga("send", "pageview");
        }
	
    });
}