package pildoras.es.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pildoras.es.controlador.entity.Cliente;

@Repository //con esto Spring reconoce que este es el repositorio
public class ClienteDAOclase implements ClienteDAO {

	@Override
	@Transactional //esta anotacion hace que evitemos usar el begin, commit y close del session
	public List<Cliente> getClientes() {
		// TODO Auto-generated method stub
		// Obtener la session
		Session miSession=sessionFactory.getCurrentSession();
		//Crear la consulta (Query)
		Query<Cliente> miQuery=miSession.createQuery("from Cliente", Cliente.class);	
		//ejecutar la query y devolver resultados
		List<Cliente> clientes=miQuery.getResultList();
		return clientes;
	}
	
	@Autowired //Da la inyeccion de dependencias cuando necesitemos usar SessionFactory
	private SessionFactory sessionFactory; //tiene que llamarse igual que lo especificado en el archivo XML


	@Override
	@Transactional
	public void insertarCliente(Cliente elCliente) {
		// TODO Auto-generated method stub
		// Obtener la session
		Session miSession=sessionFactory.getCurrentSession();
		// Insertar por el cliente
		miSession.save(elCliente);		
		//miSession.saveOrUpdate(elCliente);
	}


	@Override
	@Transactional
	public Cliente getCliente(int id) {
		// TODO Auto-generated method stub
		//Obtener la sesión
		Session miSession=sessionFactory.getCurrentSession();
		//Obtener la información del cliente seleccionado
		Cliente elCliente=miSession.get(Cliente.class, id);
		return elCliente;
	}


	@Override
	@Transactional
	public void eliminarCliente(int id) {
		// TODO Auto-generated method stub
		
		//Obtener la sesión
		
			Session miSession=sessionFactory.getCurrentSession();
			
		// Borrar el cliente de la BBDD utilizandocomo criterio su Id correspondiente
			
			Query consulta=miSession.createQuery("delete from Cliente where id=:IdDelCliente");
			
			consulta.setParameter("IdDelCliente", id);
			
			consulta.executeUpdate();
		
		
		
	}


	

}
