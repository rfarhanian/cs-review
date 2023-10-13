package com.mnaseri.cs.homework.ctci.ch08.p06;

import java.util.Stack;

public class HanoiTower {
    public static void main(String[] args) {
        Stack<Integer> start = new Stack<>();
        Stack<Integer> buffer = new Stack<>();
        Stack<Integer> target = new Stack<>();
        int n = 30;
        for (int i = 1; i <= n; i++) {

            start.push(i);
        }
        HanoiTower tower = new HanoiTower();
        tower.move(n, start, buffer, target);
        System.out.println("target = " + target);
        System.out.println("buffer = " + buffer);
        System.out.println("origin = " + start);
    }

    public void move(int n, Stack<Integer> origin, Stack<Integer> buffer, Stack<Integer> destination) {

        //n=1 ->
        // n=0 -> return
        //move 1 from origin to destination
        // n=0 -> return

        //n=2 ->
        //n=1 -> Above step is repeated
        //move 2 from origin to destination

        //n=3
        //n=2 -> above steps

        //move the n-1 elements from the start to buffer using destination as temp : move(n-1, origin, destination, buffer)
        // move the top of the origin to target : destination.push(origin.pop());
        //move the n-1 elements from buffer to target using origin as temp:  move(n-1, buffer, origin, destination)

        if (n > 0) {
            move(n - 1, origin, destination, buffer);
            destination.push(origin.pop());
            move(n - 1, buffer, origin, destination);
        }
    }

}
