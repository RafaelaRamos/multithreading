/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.edu.br.buffer;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author Cliente
 */
public class BufferBlocking implements Buffer {

    private ArrayBlockingQueue<Integer> buffer;

    public BufferBlocking() {
        buffer = new ArrayBlockingQueue<Integer>(3);
    }

    public void set(int value) {
        try {
            buffer.put(value);
            System.out.printf( "Produtor grava: ", value, "Buffers ocupados: ", buffer.size());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public int get() {
        int readValue = 0;
        try {
            readValue = buffer.take();
            System.out.printf("Consumidor lê: ", readValue, "Buffers ocupados: ", buffer.size());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return readValue;
    }
}
