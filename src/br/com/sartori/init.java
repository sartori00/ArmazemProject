package br.com.sartori;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.sartori.model.Product;
import br.com.sartori.service.ProductService;

public class init {
	
	public static void main(String[] args) throws HeadlessException, ParseException, SQLException {
		JOptionPane.showMessageDialog(null, "Bem vindo!\nClique em OK para cadastrar um novo produto", "Armazém", JOptionPane.PLAIN_MESSAGE);
		
		ProductService service = new ProductService();
		
		List<Product> productList = service.fillListProduct();
		productList.add(service.createNewProduct());
		
		service.addProductInDb(productList);
		
		JOptionPane.showMessageDialog(null, "Obrigado!!", "Armazém", JOptionPane.PLAIN_MESSAGE);
	}
}
