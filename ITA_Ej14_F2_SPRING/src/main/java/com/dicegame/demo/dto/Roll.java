package com.dicegame.demo.dto;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

//En esta clase mapeamos la entidad ROLL de BBDD para que luego puedan ser utilizadas en java.


//@Entity  // anotacion para decir que es una entidad de bbdd
//@Table(name="roll")//en este caso la clase y la tabla se llaman igual, pero esto serviria, por si se llaman diferente

@Document(collection = "roll")


public class Roll {
	 
		//Atributos de entidad roll
		@Id
		private String _id;
		@Column(name = "dice_one")//no hace falta si se llama igual
		private Integer dice_one;
		@Column(name = "dice_two")//no hace falta si se llama igual
		private Integer dice_two;
		@Column(name = "total_roll")//no hace falta si se llama igual
		private Integer total_roll;
		@Column(name = "result")//no hace falta si se llama igual
		private String result;
		@Column(name = "user")//no hace falta si se llama igual
		private String user;
		
		// Join con shop, al ser muchos a uno, nos devuelve solo una shop
		//@ManyToOne
	    //@JoinColumn(name="user_id")
	    //private User user;
		
		
	    //CONSTRUCTORES VACIO Y COMPLETO		
		public Roll() {
		
		}

		public Roll(String _id, Integer dice_one, Integer dice_two, Integer total_roll, String result, String user) {
			this._id = _id;
			this.dice_one = dice_one;
			this.dice_two = dice_two;
			this.total_roll = total_roll;		
			this.result = result;
			this.user = user;
		}
		
		//GETTERS
		public String getId() {
			return _id;
		}

		public Integer getDiceOne() {
			return dice_one;
		}
		
		public Integer getDiceTwo() {
			return dice_two;
		}
		
		public Integer getTotalRoll() {
			return total_roll;
		}
		
		public String getResult() {
			return result;
		}
		
		public String getUser() {
			return user;
		}
			
		
		
		
		// SETTERS
		public void setId(String _id) {
			this._id = _id;
		}

		public void setDiceOne(Integer dice_one) {
			this.dice_one = dice_one;
		}
		
		public void setDiceTwo(Integer dice_two) {
			this.dice_two = dice_two;
		}
		
		public void setTotalRoll(Integer total_roll) {
			this.total_roll = total_roll;
		}
		
		public void setResult(String result) {
			this.result = result;
		}

		public void setUser(String user) {
			this.user = user;
		}
		
		

		//Metodo impresion de datos por consola
		@Override
		public String toString() {
			return "Roll [id=" + _id + ", dice one=" + dice_one + ", dice two=" + dice_two + ", total roll=" + total_roll
					+ ", result=" + result + ", user_id=" + user + "]";
		}

		// método que devuelve un numero aleatorio ente 1 y 6 (tirada de dado)
		public static Integer rollDice() {
		
			return (int) ((Math.random() * 6) + 1);
			
		}
		
		// método que genera una tirada completa, dado1, dado2, suma y resultado
		public void playGame() {
			
			dice_one = Roll.rollDice(); 
			dice_two = Roll.rollDice();
			total_roll = dice_one + dice_two;
			
			if(this.total_roll == 7) {
				this.result = "WIN";
			}else {
				this.result = "LOSE";
			}
		}

}
