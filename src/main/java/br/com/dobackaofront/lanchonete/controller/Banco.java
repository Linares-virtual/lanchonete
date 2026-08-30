/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dobackaofront.lanchonete.controller;

import br.com.dobackaofront.lanchonete.model.Lanche;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Banco {    
    private String url;
    private String usuario;
    private String senha;   
    
    public Banco() {      
        url = "jdbc:mysql://localhost:3306/lanchonete";
        usuario = "root";
        senha = "root";        
    }
    
    public Connection conectar(){         
        try {            
            Connection conexao =  DriverManager.getConnection(url, usuario, senha);            
            System.out.println("Conexão com Banco de Dados estabelecida com sucesso !");            
            return conexao;            
        } catch (SQLException e) {            
            System.out.println("Não foi possível conectar ao Banco de Dados");
            return null;            
        }                
    } 
    
    
    
    public void salvar(Lanche lanche, Connection conexao) {        
        String sql = "INSERT INTO lanche(nome, preco) VALUES(?, ?)";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)){                
             stmt.setString(1, lanche.getNome());
             stmt.setDouble(2, lanche.getPreco());
                                
             int linhasAfetadas = stmt.executeUpdate();
                
                if (linhasAfetadas > 0) {                    
                    System.out.println("Lanche salvo com sucesso !");        
    } 
        }
                catch(SQLException e) {            
            System.out.println("Lanche não foi salvo no Banco de Dados !");
        
        }
        
    }
}
