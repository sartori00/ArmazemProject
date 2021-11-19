package br.com.sartori.service;

import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.sartori.bdconnection.DBConnection;
import br.com.sartori.model.Product;

public class ProductService {
	
	public Product createProduct(int codigo, String nome, BigDecimal valor, int quantidade, String dataVencimento) {
		Product product = new Product();
		product.setCodigo(codigo);
		product.setNome(nome);
		product.setValor(valor);
		product.setQuantidade(quantidade);
		product.setDataVencimento(dataVencimento);
		
		return product;
	}
	
	public List<Product> fillListProduct() throws ParseException{
		
		List<Product> productList = new ArrayList<>();
		productList.add(this.createProduct(300, "Lazanha", new BigDecimal(12.40), 189, "27/01/2022"));
		productList.add(this.createProduct(301, "Arroz", new BigDecimal(15.50), 852, "12/04/2022"));
		productList.add(this.createProduct(302, "Feijão", new BigDecimal(8), 52, "28/07/2022"));
		
		return productList;	
	}
	
	public Product createNewProduct() throws HeadlessException, ParseException {
		int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite um código numérico para o produto"));
		
		String nome = JOptionPane.showInputDialog("Digite o nome do Produto");
		
		BigDecimal valor = new BigDecimal(JOptionPane.showInputDialog("Digite o Valor do Produto\nEx.: 12.00"));
		
		int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de produto em estoque"));
		
		String dataVencimento = JOptionPane.showInputDialog("Digite a data de Vencimento do Produto\nEx.:18/11/2021");
		
		Product product = this.createProduct(codigo, nome, valor, quantidade, dataVencimento);

		JOptionPane.showMessageDialog(null, "Produto a ser criado:\n" + product.toString(), "Armazém", JOptionPane.PLAIN_MESSAGE);
		
		return product;
	}
	
	public void addProductInDb(List<Product> productList) throws SQLException {
		DBConnection connection = new DBConnection();
		Statement statement = connection.openConnection();
		
		productList.forEach((productItem) -> {
			try {
				connection.insertProduct(statement, productItem);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		
		int seeProducts = JOptionPane.showConfirmDialog(
				null, 
				"Produto inserido com sucesso no Banco de Dados.\nDeseja ver os produtos cadastrados?", 
				"Sucesso!", 
				JOptionPane.YES_NO_OPTION);
		
		if(seeProducts == 0) {
			this.seeProductsOnDb(connection, statement);
		}
		
		connection.closeConnection();
	}
	
	public void seeProductsOnDb(DBConnection connection, Statement statement) throws SQLException {
		ResultSet result = connection.getAllInformations(statement);
		List<Product> productList = new ArrayList<>();
		while(result.next()) {

			Product product = this.createProduct(
					result.getInt("CODIGO"), 
					result.getString("NOME"), 
					result.getBigDecimal("VALOR"), 
					result.getInt("QUANTIDADE"), 
					result.getString("DATAVALIDADE") );

			productList.add(product);
		}
		StringBuilder productsMsg = new StringBuilder();
		productList.forEach(productItem -> productsMsg.append(productItem.toStringRetornedOfDb()));
		
		JOptionPane.showMessageDialog(null, productsMsg, "Produtos Cadastrados no banco", JOptionPane.PLAIN_MESSAGE);
	}
}
