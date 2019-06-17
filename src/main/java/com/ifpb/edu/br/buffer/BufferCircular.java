/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.edu.br.buffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cliente
 */
public class BufferCircular implements Buffer {

    private Lock lock = new ReentrantLock();
    private Condition permissaoGravar = lock.newCondition();
    private Condition poermissaoLeitura = lock.newCondition();
    private int buffer = -1;
    private boolean estado = false;

    public void set(int valor) {
        lock.lock();
        try {
            while (estado) {

                System.out.println("produtor aguarde buff cheio,");
                permissaoGravar.await();
            }
            buffer = valor;
            estado = true;
            System.out.println("Produtor grava: " + buffer);
            poermissaoLeitura.signal();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int get() {
        int valorLido = 0;
        lock.lock();
        while (!estado) {
           
            System.out.println("buffer vazio,aguarde");

            try {
                poermissaoLeitura.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(BufferCircular.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        estado = false;
        valorLido = buffer;
        System.out.println("Valor" + valorLido);

        permissaoGravar.signal();
        lock.unlock();
        return valorLido;
    }

}
