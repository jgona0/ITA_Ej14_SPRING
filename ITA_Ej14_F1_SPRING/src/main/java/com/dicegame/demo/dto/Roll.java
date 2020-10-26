package com.dicegame.demo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//En esta clase mapeamos la entidad ROLL de BBDD para que luego puedan ser utilizadas en java.


@Entity  // anotacion para decir que es una entidad de bbdd
@Table(name="roll")//en este caso la clase y la tabla se llaman igual, pero esto serviria, por si se llaman diferente

public class Roll {
	 
		//Atributos de entidad roll
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)//busca ultimo valor e incrementa desde id final de db
		private Long id;
		@Column(name = "dice_one")//no hace falta si se llama igual
		private Long dice_one;
		@Column(name = "dice_two")//no hace falta si se llama igual
		private Long dice_two;
		@Column(name = "total_roll")//no hace falta si se llama igual
		private Long total_roll;
		@Column(name = "result")//no hace falta si se llama igual
		private String result;
		
		
		// Join con shop, al ser muchos a uno, nos devuelve solo una shop
		@ManyToOne
	    @JoinColumn(name="user_id")
	    private User user;
		
		
	    //CONSTRUCTORES VACIO Y COMPLETO		
		public Roll() {
		
		}

		public Roll(Long id, Long dice_one, Long dice_two, Long total_roll, User user) {
			this.id = id;
			this.dice_one = dice_one;
			this.dice_two = dice_two;
			this.total_roll = total_roll;		
			this.user = user;
		}
		
		//GETTERS
		public Long getId() {
			return id;
		}

		public Long getDiceOne() {
			return dice_one;
		}
		
		public Long getDiceTwo() {
			return dice_two;
		}
		
		public Long getTotalRoll() {
			return total_roll;
		}
		
		public String getResult() {
			return result;
		}
		
		public User getUser() {
			return user;
		}
			
		
		
		
		// SETTERS
		public void setId(Long id) {
			this.id = id;
		}

		public void setDiceOne(Long dice_one) {
			this.dice_one = dice_one;
		}
		
		public void setDiceTwo(Long dice_two) {
			this.dice_two = dice_two;
		}
		
		public void setTotalRoll(Long total_roll) {
			this.total_roll = total_roll;
		}
		
		public void setResult(String result) {
			this.result = result;
		}

		public void setUser(User user) {
			this.user = user;
		}
		
		

		//Metodo impresion de datos por consola
		@Override
		public String toString() {
			return "Roll [id=" + id + ", dice one=" + dice_one + ", dice two=" + dice_two + ", total roll=" + total_roll
					+ ", result=" + result + ", user=" + user + "]";
		}

		// método que devuelve un numero aleatorio ente 1 y 6 (tirada de dado)
		public static Long rollDice() {
		
			return (long)((Math.random() * 6) + 1);
			
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
