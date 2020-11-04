package com.dicegame.demo.dto;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

//En esta clase mapeamos la entidad USER de BBDD para que luego puedan ser utilizadas en java.

@Entity  // anotacion para decir que es una entidad de bbdd
@Table(name="user")//en este caso la clase y la tabla se llaman igual, pero esto serviria, por si se llaman diferente

public class User {
	 
		//Atributos de entidad user
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)//busca ultimo valor e incrementa desde id final de db
		private Long id;
		@Column(name = "name")//no hace falta si se llama igual
		private String name;
		@Column(name = "porc_success")//no hace falta si se llama igual
		private double porc_success;

		
		// Join con paint, al ser uno a muchos, nos devuelve una lista
		@OneToMany (mappedBy = "user") // con mapped by decimos que la tabla que "manda" será la de shop
	    //@JoinColumn(name="id")   --> Con esta instrucción no funcionaba bien el delete y el update
	    private List<Roll> roll;
	    
	    
	    //CONSTRUCTORES VACIO Y COMPLETO
		public User() {
		
		}

		public User(Long id, String name, double porc_success) {
			this.id = id;
			this.name = name;
			this.porc_success = porc_success;
		}

		
		
		//GETTERS 
		public Long getId() {
			return id;
		}	
		
		public String getName() {
			return name;
		}
		
		public double getPorcSuccess() {
			return porc_success;
		}
		
		
		//Retorna una lista de Paints, al ser de uno a muchos
		@JsonIgnore
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "Roll")
		public List<Roll> getRoll() {
			return roll;
		}
		

		
		//SETTERS
		public void setId(Long id) {
			this.id = id;
		}

		public void setName(String name) {
			this.name = name;
		}


		public void setRoll(List<Roll> roll) {
			this.roll = roll;
		}

		public void setPorcSucces(double porc_success) {
			
			this.porc_success = porc_success;
		}
		
		
		//Metodo impresion de datos por consola
		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + "]";
		}
	
}
