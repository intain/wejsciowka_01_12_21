package com.janbartnicki;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class SimpleWzimBoundedQueue<E> implements WzimBoundedQueue<E> {
    private final Object[] array;
    private int start, end;
    private final int capacity;

    public SimpleWzimBoundedQueue(final int maxCapacity) {
        array = new Object[maxCapacity];
        start = 0;
        end = 1;
        capacity = maxCapacity;
    }

    private void forceAdd(E e)
    {
        array[end] = e;
        end = (end + 1) % capacity;
    }

    @Override
    public boolean add(E e) throws  IllegalStateException {
        if(((end + 1) % capacity) == start) {
            throw new IllegalStateException();
        }

        forceAdd(e);
        return true;
    }

    @Override
    public boolean offer(E e) {
        if(((end + 1) % capacity) == start) {
            return false;
        }

        forceAdd(e);
        return true;
    }

    @Override
    public E remove() {
        if(start == end) {
            throw new NoSuchElementException();
        }

        E element = (E)array[start];
        start = (start + 1) % capacity;
        return element;
    }

    @Override
    public E poll() {
        if(start == end) {
            return null;
        }

        E element = (E)array[start];
        start = (start + 1) % capacity;
        return element;
    }

    @Override
    public E element() {
        if(start == end) {
            throw new NoSuchElementException();
        }

        return (E)array[start];
    }

    @Override
    public E peek() {
        if(start == end) return null;
        return (E)array[start];
    }

    @Override
    public int size() {
        if(end >= start) return end - start;
        return (capacity - start) + end;
    }

    @Override
    public int capacity() {
        return capacity;
    }
}
