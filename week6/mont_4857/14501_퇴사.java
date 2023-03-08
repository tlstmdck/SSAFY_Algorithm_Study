import java.io.*;
import java.util.*;

/*
 * 일이 끝나는 일을 기준으로 정렬하여 (일의 시작 전날까지의 최대값 + 해당 일)과 전 날까지의 최대값을 비교하여 해당 날짜의 최대값을 구한다.
*/
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
    
    		// 끝나는 일을 기준으로 정렬하기 위한 PriorityQueue
		PriorityQueue<Work> pq = new PriorityQueue<>();
		int[] day = new int[N+1];
		
    		// 모든 일을 pq에 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int end = Integer.parseInt(st.nextToken());
			int pay = Integer.parseInt(st.nextToken());
			pq.offer(new Work((i+1),end+i,pay));
		}
    
   		 // 0번째 날 초기화
		day[0] = 0;
		
		/*
		 * 1. 해당 날짜별로 최대값 비교
		 * 2. 해당 날짜에 일이 있는 지 확인
		 * 2-1. 일이 존재하는 경우
		         -> 해당 날짜의 최대값 = Math.max(전날의 최대값, 해당 일의 시작 전날의 최대값 + 해당 일의 가격)
	   	 * 2-2. 일이 없는 경우
		         -> 전날의 최대값을 입력
		*/
		for (int i = 1; i < day.length; i++) {
			boolean isT = false;
			while(!pq.isEmpty() && pq.peek().end == i) {
				Work cur = pq.poll();
				isT = true;
				day[i] = Math.max(day[i], Math.max(day[i-1], day[cur.start-1] + cur.pay));
			}
			if(!isT) day[i] = day[i-1];
		}
		
		System.out.println(day[N]);
		
	}
	static class Work implements Comparable<Work>{
		int start;
		int end;
		int pay;
		public Work(int start, int end, int pay) {
			this.start = start;
			this.end = end;
			this.pay = pay;
		}
		@Override
		public String toString() {
			return "Work [start=" + start + ", end=" + end + ", pay=" + pay + "]";
		}
    
  		/*
  		 * 1. 끝나는 날이 빠른 것
  		 * 2. 시작 날이 느린 것
   		*/
		@Override
		public int compareTo(Work o) {
			if(this.end == o.end) {
				return o.start-this.start;
			}
			return this.end - o.end;
		}
	}
}
