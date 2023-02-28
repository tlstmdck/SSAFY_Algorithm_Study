import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int N,M;
	static int[][] map;
	static int[][] original_map;
	static boolean[][] visit;
	static int zero_cnt;
	static int cnt;
	static int max_cnt=0;
	static int total_cb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		
		original_map=new int[N][M]; // 변하지 않는 맵 
		map=new int[N][M];//계속 타임마다 사용할 맵 
		visit=new boolean[N][M];
		
		int[][] arr = new int[N*M][2]; //최대 0의 개수 만큼 배열 생성 
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				original_map[i][j]=sc.nextInt();
			}
		}
		
		//배열 복사 
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				map[i][j]=original_map[i][j];
			}
		}
		
		//조합으로 0인거 n개 중에서 3개뽑아서 1로만들기 
		
		//0개 갯
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==0) { 
					arr[cnt][0]=i;
					arr[cnt][1]=j;
					cnt++;
				}
			}
		}
		
		//조합 보내
		int r=3;
		combination(arr, new boolean[cnt], 0, 0, r);	
		
		System.out.println(max_cnt);
	}

	private static void DFS(int x, int y) {
		int[] dx= {0,0,1,-1};
		int[] dy= {1,-1,0,0};
		
		visit[x][y]=true;
		map[x][y]=2; // 거처간 애들을 2로 만들
		
		for(int i=0;i<4;i++) {
			int nx=x+dx[i];
			int ny=y+dy[i];
			
			if(check(nx,ny)) {
				if(!visit[nx][ny]&&map[nx][ny]==0) {
					DFS(nx,ny);
				}
			}
		}
		
	}
	public static void combination(int[][] arr, boolean[] visited, int start, int depth, int r){
        
		if(depth == r){
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					map[i][j]=original_map[i][j];
				}
			}
			
            for(int i=0; i<cnt; i++){
                if(visited[i]) {
                	//뽑은 것들! 	
                	//System.out.println((arr[i][0]+1)+" "+(arr[i][1]+1 ));
                	map[arr[i][0]][arr[i][1]]=1;
                }
                
            }
            
            visit=new boolean[N][M];
            zero_cnt=0;
            
    		for(int i=0;i<N;i++) {
    			for(int j=0;j<M;j++) {
    				if(!visit[i][j]&&map[i][j]==2) {
    					DFS(i,j);
    				}
    			}
    		}
    	
    		for(int i=0;i<N;i++) {
    			for(int j=0;j<M;j++) {
    				if(map[i][j]==0) zero_cnt++;
    			}
    		}
    		//System.out.println(zero_cnt);
    		max_cnt= Math.max(zero_cnt, max_cnt);
      
            return;
        }
		
        for(int i=start; i<cnt; i++){
            if(!visited[i]){
                visited[i] = true;
                combination(arr, visited, i+1, depth+1, r);
                visited[i] = false;
            }
        }
    }
	
	private static boolean check(int nx, int ny) {
		if(nx>=0&&nx<N&&ny>=0&&ny<M)
			return true;
		
		else return false;
	}
}
