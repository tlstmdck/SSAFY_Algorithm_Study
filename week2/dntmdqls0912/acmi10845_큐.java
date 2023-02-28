import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Linked List를 직접 구현하여 큐 구현하기

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue q = new Queue();
        StringTokenizer st;

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "push":
                    int num = Integer.parseInt(st.nextToken());
                    q.push(num);
                    break;

                case "pop":
                    System.out.println(q.pop());
                    break;

                case "size":
                    System.out.println(q.size());
                    break;

                case "empty":
                    if (q.size() == 0) {
                        System.out.println(1);
                    }
                    else {
                        System.out.println(0);
                    }
                    break;

                case "front":
                    System.out.println(q.front());
                    break;

                case "back":
                    System.out.println(q.back());

                default:
            }

        }
    }
}

// 데이터와 다음 노드를 가르키는 노드 클래스 
class Node {
    int data;
    Node next;

    public Node() {

    }
    public Node (int data) {
        this.data = data;
        this.next = null;
    }
}

// 노드 클래스를 담는 큐
class Queue {
    private Node head;
    private Node tail;
    private int size;

    // 생성자
    public Queue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    public void push(int n) {
        // 처음 노드가 들어올 땐 head와 tail이 같은 노드를 가르킴
        if (size == 0) {
            Node node = new Node(n);
            this.head = node;
            this.tail = node;
        }
        else {
            tail.next = new Node(n);
            tail = tail.next;
        }
        size++;
    }

    public int pop() {
        if (size == 0) {
            return -1;
        }
        else {
            int pop = head.data;
            head = head.next;
            size--;
            return pop;
        }
    }

    public int size() {
        return size;
    }

    public int front() {
        if (size == 0) {
            return -1;
        }
        else {
            return head.data;
        }
    }

    public int back() {
        if (size == 0) {
            return -1;
        }
        else {
            return tail.data;
        }
    }
}