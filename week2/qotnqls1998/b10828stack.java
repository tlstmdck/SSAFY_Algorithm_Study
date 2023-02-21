package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//stack 구현하기 FILO
public class b10828stack {
	public static void main(String[] args) throws  IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bf.readLine());	
		Stack st = new Stack(tc);
		
		for(int i=0;i<tc;i++) {
			StringTokenizer tmp = new StringTokenizer( bf.readLine());
			String s=tmp.nextToken();
			int item=0;
			
			if(s.equals("top")) {
				st.top();
				//System.out.println(s);
			}else if(s.equals("size")) {
				st.size();

			}
			else if(s.equals("empty")) {
				if(st.isEmpty()==true) 
					System.out.println(1);
				else System.out.println(0);
			}
			else if(s.equals("pop")) {
				st.pop();
			}
			else {
				item = Integer.parseInt(tmp.nextToken());
				st.push(item);
			}
		}
	}
}

class Stack {
	int top; //idx
	int size; //스택 배열의 크기
	int[] stack;
	
	//배열로 스택구현 - 생성자
	public Stack(int size) {
		this.size = size;
		stack = new int[size];
		top =-1;
		
	}
	public void push(int item) {
		if(isFull()==true)
			System.out.println(-1);
		else {
			stack[++top]=item;
		}		
	}
	public void pop() {
		// 비어있으면 -1 출력
		if(isEmpty()==true)
			System.out.println(-1);
		//아니라면 pop 실행 
		else {
			System.out.println(stack[top]);
			stack[top--]=0;
		}		
	}
	public void top() {
		if(isEmpty()==true)
			System.out.println(-1);
		//아니라면 top 실행 
		else {
			System.out.println(stack[top]);
		}		
	}
	public void size() {
		System.out.println(top+1);
	}
	public boolean isEmpty() {
		if (top==-1) return true;
		else return false;
	}
	public boolean isFull() {
		if (top==size-1) return true;
		else return false;
	}
}
