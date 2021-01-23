package edu.greenriver.dev;

public class Main {

    // unit testing (required)
    public static void main(String[] args) {
	    // write your code here
        Deque<String> q = new Deque<>();
        q.addFirst("hi");
        q.addLast("bye");
        q.addFirst("hello");
        q.addLast("see ya");

        //the short way
        for(String s : q){
            System.out.println(s);
            //q.removeFirst();
        }


    }
}
