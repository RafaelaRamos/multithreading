/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.edu.br.buffer;

/**
 *
 * @author Cliente
 */
public class BufferExemploSimples implements Buffer {

    private int buffer = -1;

    public void set(int valor) {
        System.out.printf("Produtor grava: ", valor);
        buffer = valor;
    }

    public int get() {
        System.out.printf("Consumidor lê:", buffer);
        return buffer;
    }
}
