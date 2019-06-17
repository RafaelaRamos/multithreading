/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.edu.br.buffer;

import java.util.Random;

/**
 *
 * @author Cliente
 */
public class Consumidor implements Runnable {

    private static Random rd = new Random();
    private Buffer buff;

    public Consumidor(Buffer compartilhado) {
        buff = compartilhado;
    }

    public void run() {
        int soma = 0;
        for (int cont = 1; cont <= 10; cont++) {
            try {
                Thread.sleep(rd.nextInt(3000));
                soma += buff.get();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        System.out.printf("Finalizado soma :", soma);
    }
}
