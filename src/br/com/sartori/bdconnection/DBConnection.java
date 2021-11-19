package br.com.sartori.bdconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.sartori.model.Product;

public class DBConnection {
	
	Connection connection = null;
	public Statement openConnection() {
		
		Statement statement = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:/armazem.db");
			statement = connection.createStatement();
			
			this.dropAndCreateTable(statement);
		} catch (SQLException e) {
		  System.err.println(e.getMessage());
		} finally {
		  
		}
		return statement;
		
	}
	
	public void insertProduct(Statement statement, Product product) throws SQLException {
		statement.executeUpdate("INSERT INTO ARMAZEM VALUES("
									+ product.getCodigo() + ", "
									+ "'" + product.getNome() + "'," 
									+ product.getValor() + ", " 
									+ product.getQuantidade() + ", "
									+ "'" + product.getDataVencimento() + "'"
								+ " )");
	}
	
	public void dropAndCreateTable(Statement statement) throws SQLException {
		statement.executeUpdate("DROP TABLE IF EXISTS ARMAZEM;");
		
		statement.executeUpdate("CREATE TABLE ARMAZEM (CODIGO INTEGER, NOME TEXT, "
	         + " VALOR REAL,QUANTIDADE INTEGER, DATAVALIDADE TEXT);");
	}
	
	public ResultSet getAllInformations(Statement statement) throws SQLException {
		ResultSet result = statement.executeQuery("SELECT * FROM ARMAZEM;");
		
		return result;

	}
	
	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	

}
