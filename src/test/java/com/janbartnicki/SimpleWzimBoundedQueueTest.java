package com.janbartnicki;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleWzimBoundedQueueTest {
    final static int MAX_CAPACITY = 3;
    SimpleWzimBoundedQueue<Integer> queue;
    @BeforeEach
    void setup() {
        queue = new SimpleWzimBoundedQueue<>(MAX_CAPACITY);
    }

    @Test
    void testAdd() {
    }

    @Test
    void testOffer() {
    }

    @Test
    void testRemove() {
    }

    @Test
    void testPoll() {
    }

    @Test
    void testElement() {
    }

    @Test
    void testPeek() {
    }

    @Test
    void testSize() {
        assertEquals(queue.size(), 0, "Queue should be empty at the start");
        queue.add(3);
        assertEquals(queue.size(), 1, "After adding a single element the size should be 1");
        queue.add(5);
        queue.add(7);
        assertEquals(queue.size(), 3, "After adding two more elements the size should be 3");
        queue.offer(11);
        assertEquals(queue.size(), 3, "Queue should be at capacity and the new element shouldn't be added");
        queue.remove();
        assertEquals(queue.size(), 2, "After removing the head element the size should be back to 2");

    }

    @Test
    void testCapacity() {
        assertEquals(queue.capacity(), MAX_CAPACITY, "Capacity should be equal to the initially set value");
    }
}