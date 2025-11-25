package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DB.DB;
import DB.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

	private Connection conn; //Injeção de Dependência
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*, "
					+ "Department.Name as DepName "
					+ "FROM seller join department  on seller.DepartmentId = department.Id "
					+	"WHERE seller.id = ?");
			st.setInt(1, id);//Recebe o id para busca
			rs = st.executeQuery(); //Para executar o comando
			if(rs.next()) {
				Department dep = instantiateDepartment(rs);
				
				Seller seller = instantiateSeller(rs, dep);
				return seller;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	//Não necessita tratar e sim propagar
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setDepartment(dep);
		
		return seller;		
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));//Pegando o id do departamento
		dep.setName(rs.getString("DepName"));//Pegando o Nome do departamento		return null;
		return dep;
	}
	

	@Override
	public List<Seller> findAll() {
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("SELECT seller.*, "
					+ "Department.Name as DepName "
					+ "FROM seller join department  on seller.DepartmentId = department.Id "
					+ "order by Name; "
					);
			
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<Integer, Department>();
			
			while(rs.next()) {
				
				//Controlando a não repetição de depatamento
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if( dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller seller = instantiateSeller(rs, dep);
				list.add(seller);
			}
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Seller> findBydepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT seller.*, "
					+ "Department.Name as DepName "
					+ "FROM seller join department  on seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "order by Name");
			st.setInt(1, department.getId());
			
			rs = st.executeQuery(); //O resultSet recebe o resultado do comando
			
			//O resultado pode ter 0 ou mais valores
			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<Integer, Department>();
			
			while(rs.next()) {//Ler enquanto tiver um proximo
				
				//Testar se o departamento já existe
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {//Se não existir
					dep = instantiateDepartment(rs);//Instanciar o obj
					map.put(rs.getInt("DepartmentId"), dep);//Adicionar ao Map
				}
				
				Seller seller = instantiateSeller(rs, dep);
				list.add(seller);
				
			}
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

}
