package es.cgarcia.decocina.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.dao.IEstadoAdminDao;
import es.cgarcia.decocina.admin.dao.IPedidoEstadoHistorialAdminDao;
import es.cgarcia.decocina.admin.exception.DeCocinaAdminRuntimeException;
import es.cgarcia.decocina.admin.job.MailEstadoTimer;
import es.cgarcia.decocina.admin.service.IPedidoEstadoHistorialAdminService;
import es.cgarcia.decocina.web.model.Estado;
import es.cgarcia.decocina.web.model.Pedido;
import es.cgarcia.decocina.web.model.PedidoEstadoHistorial;

/**
 * Contiene los métodos necesarios para trabajar con un PedidoEstadoHistorial de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("pedidoEstadoHistorialAdminService")
public class PedidoEstadoHistorialAdminServiceImpl extends GenericServiceImpl<PedidoEstadoHistorial, IPedidoEstadoHistorialAdminDao> implements IPedidoEstadoHistorialAdminService{

	/**
	 * Dao para los Estados de la parte Admin.
	 */
	@Autowired
	private IEstadoAdminDao estadoAdminDao;
	
	/**
	 * Envía el mail de cambio de Estado.
	 */
	@Autowired
	private MailEstadoTimer mailEstadoTimer;
	
	/**
	 * Constructor.
	 * 
	 * @param pedidoEstadoHistorialAdminDao Dao para el PedidoEstadoHistorial.
	 */
	@Autowired
	public PedidoEstadoHistorialAdminServiceImpl(IPedidoEstadoHistorialAdminDao pedidoEstadoHistorialAdminDao){
		
		super(pedidoEstadoHistorialAdminDao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PedidoEstadoHistorial> findAllByIdPedido(Long id) {
		
		return dao.findAllByIdPedido(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(Pedido pedido) {
	
		try{
			
			dao.insert(pedido);
		
			Boolean notificarEstado = pedido.getNotificarEstado();
			
			if(notificarEstado){
				
				//Recuperamos el nombre del Estado de la base de datos a partir del id 
				//y se lo pasamos al EstadoAdmin del pedido para enviarlo por mail.
				Estado estadoAdmin = pedido.getEstadoAdmin();
				Long idEstadoAdmin = estadoAdmin.getId();
				
				Estado estadoBD = estadoAdminDao.findById(idEstadoAdmin);
				
				String nombreEstadoBD = estadoBD.getNombre();
				
				estadoAdmin.setNombre(nombreEstadoBD);
				
				mailEstadoTimer.enviaMail(pedido);
			}
			
		}catch(Exception e){
			
			throw new DeCocinaAdminRuntimeException("Error enviando email de cambio de estado.", e);
		}
	}
}