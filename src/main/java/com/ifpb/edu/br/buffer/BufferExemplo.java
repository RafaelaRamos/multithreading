/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.edu.br.buffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Cliente
 */
public class BufferExemplo implements Buffer {

    private Lock lock = new ReentrantLock();
    private Condition PermissaoGravar = lock.newCondition();
    private Condition PermissaoLer = lock.newCondition();
    private int[] buff = {-1, -1, -1};
    private int buffersOcupados = 0;
    private int gravarIndice = 0;
    private int lerIndice = 0;

    public void set(int valor) {
        lock.lock();
        try {
            while (buffersOcupados == buff.length) {
                System.out.printf("Todos os buffers cheios.Produtor aguarda.\n");
                PermissaoGravar.await();
            }
            buff[gravarIndice] = valor;
            gravarIndice = (gravarIndice + 1) % buff.length;
            buffersOcupados++;
            System.out.println("Produtor grava: " + valor);
            PermissaoLer.signal();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int get() {
        int valorLido = 0;
        lock.lock();
        lock.lock();
        try {
            while (buffersOcupados == 0) {
                System.out.printf("Todos os buffers estão vazios. Consumidor aguarda.\n");
                PermissaoLer.await();
            }
            valorLido = buff[lerIndice];
            lerIndice = (lerIndice + 1) % buff.length;
            buffersOcupados--;
            System.out.println("Consumidor lê:"+ valorLido);
           
            PermissaoGravar.signal();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
        }
        return valorLido;
    }

   
}
