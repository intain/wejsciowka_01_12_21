package com.janbartnicki;

import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("unchecked")
public class SimpleWzimBoundedQueue<E> implements WzimBoundedQueue<E> {
    private final Object[] array;
    private int start, count;
    private final int capacity;
    private final ReentrantLock lock = new ReentrantLock();

    public SimpleWzimBoundedQueue(final int maxCapacity) {
        array = new Object[maxCapacity];
        start = 0;
        count = 0;
        capacity = maxCapacity;
    }

    private void forceAdd(E e)
    {
        lock.lock();
        try {
            array[start + count++] = e;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean add(E e) throws  IllegalStateException {
        if(count == capacity) {
            throw new IllegalStateException();
        }

        forceAdd(e);
        return true;
    }

    @Override
    public boolean offer(E e) {
        if(count == capacity) {
            return false;
        }

        forceAdd(e);
        return true;
    }

    @Override
    public E remove() {
        if(count == 0) {
            throw new NoSuchElementException();
        }

        E element = (E)array[start];
        start = (start + 1) % capacity;
        count--;
        return element;
    }

    @Override
    public E poll() {
        if(count == 0) {
            return null;
        }

        E element = (E)array[start];
        start = (start + 1) % capacity;
        count--;
        return element;
    }

    @Override
    public E element() {
        if(count == 0) {
            throw new NoSuchElementException();
        }

        return (E)array[start];
    }

    @Override
    public E peek() {
        if(count == 0) return null;
        return (E)array[start];
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public int capacity() {
        return capacity;
    }
}
