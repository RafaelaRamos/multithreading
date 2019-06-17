/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.edu.br.buffer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Cliente
 */
public class Main {

    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(2);
        Buffer buff = new BufferExemploSimples();
        try {
            ex.execute(new Produtor(buff));
            ex.execute(new Consumidor(buff));
        } catch (Exception exception) {
        }
        ex.shutdown();
    }
}
