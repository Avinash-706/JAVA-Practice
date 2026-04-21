/*
Write a Java program that uses a PriorityQueue to schedule and execute tasks based on their priority (lower number = higher priority). You should:
Create a Task class with name and priority.
Store tasks in a PriorityQueue<Task> so that tasks with higher priority (i.e., lower priority value) are processed first.
Add at least 5 tasks with different priorities.
Print the tasks in the order they are executed.
In a priority queue, elements are ordered based on priority (not insertion order). By default Java’s PriorityQueue behaves like a min-heap, meaning the smallest element is at the head.
*/

import java.util.PriorityQueue;
import java.util.Queue;

class Task {
    String name;
    int priority;

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
    @Override
    public String toString(){
        return "Name: " + this.name + ", Priority: " + this.priority;
    }
}


public class TaskPriority {
    public static void main(String[] args) {
        Task t1 = new Task("task1", 12);
        Task t2 = new Task("task2", 100);
        Task t3 = new Task("task3", 3);

        Queue<Task> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.priority, b.priority));

        pq.offer(t1);
        pq.offer(t2);
        pq.offer(t3);

        System.out.println("Task Execution Order");
        while(!pq.isEmpty()) {
            Task t = pq.poll();
            System.out.println(t);
        }

    }
}
