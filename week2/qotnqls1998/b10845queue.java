package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b10845queue{
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bf.readLine());	
		Queue q = new Queue(tc);
		
		for(int i=0;i<tc;i++) {
			StringTokenizer tmp = new StringTokenizer( bf.readLine());
			String s=tmp.nextToken();
			int item=0;
			
			System.out.print(s);
			if(s.equals("front")) {
				q.front();		
			}else if(s.equals("back")) {
				q.back();
			}else if(s.equals("size")) {
				q.size();
			}else if(s.equals("empty")) {
				if(q.isEmpty()==true) 
					System.out.println(1);
				else System.out.println(0);
			}
			else if(s.equals("pop")) {
				q.pop();
			}
			else {
				item = Integer.parseInt(tmp.nextToken());
				q.push(item);
			}
		}
	}
}

class Queue {
	int front; //idx
	int rear;
	int size; //큐 배열의 크기
	int[] queue;
	
	public Queue(int size) {
		this.size = size;
		queue = new int[size];
		front =0;
		rear=0;
	}
	public void push(int item) {
		if(isFull()==true)
			System.out.println(-1);
		else {
			rear = (rear + 1) % size;
		    queue[rear] = item;
		}		
	}
	public void pop() {
		// 비어있으면 -1 출력
		if(isEmpty()==true)
			System.out.println(-1);
		//아니라면 pop 실행 
		else {
			System.out.println(queue[++front % size]);
			
			queue[++front % size]=0;
		}		
	}
	public void front() {
		if(isEmpty()==true)
			System.out.println(-1);
		else {
			System.out.println(queue[front]);
		}		
	}
	public void back() {
		if(isEmpty()==true)
			System.out.println(-1);
		else {
			System.out.println(queue[rear]);
		}	
	}
	public void size() {
		System.out.println(rear-front);
	}
	public boolean isEmpty() {
		if (front==rear)
			return true;
		return false;
	}
	
	public boolean isFull() {
		if ((rear+1)%size==front) 
			return true;
		return false;
	}
}
