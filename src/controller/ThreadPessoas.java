package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadPessoas extends Thread{
	
	private int id;
	private Semaphore semaforo;
	
	public ThreadPessoas(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}

	public void run() {
		andando();
		try {
			semaforo.acquire();
			cruzarPorta();
			portaCruzada();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private void andando() {
		int distCorredor = 200;
		int distPercorrida = 0;
		int deslocamento;
		while (distPercorrida < distCorredor) {
			deslocamento = (int)(Math.random()*5) + 2;
			distPercorrida += deslocamento;
			System.out.println("A Pessoa " + id + " andou " + distPercorrida);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("#Pessoa " + id + " chegou na porta.");
	}
	
	private void cruzarPorta() {
		System.out.println("A Pessoa " + id + " cruzando a porta.");
		int tempo = (int)(Math.random()*2) +1;
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void portaCruzada() {
		System.out.println("A Pessoa " + id + " cruzou a porta.");
	}
}
