import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 문제 해결 시간 : 약 30분
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] road = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N-1; i++) {
            road[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        long totalPrice = 0;
        int localMinOilPrice = Integer.parseInt(st.nextToken()); // 로컬 기름 최소값을 첫번째 마을 기름값으로 초기화 및
        long roadBuffer = road[0]; // 경로 버퍼에 첫번째 도로 길이 추가
        
        for(int i=1; i<N; i++) {
        	int currentOilPrice = Integer.parseInt(st.nextToken());
        	// 기름값이 기존보다 작을 때, 혹은 마지막 마을에 방문했을 때
        	if (currentOilPrice < localMinOilPrice || i == N-1) {
        		// 기존 기름값에 버퍼에 쌓아둔 경로 길이를 곱해 가격합에 더한다
        		// 여기서 int * int를 하면 오버플로가 발생함
        		totalPrice += roadBuffer * localMinOilPrice;
        		// 다음 마을까지의 경로길이를 다시 버퍼에 쌓는다
        		roadBuffer = road[i];
        		localMinOilPrice = currentOilPrice;
        	}
        	// 기름값이 기존보다 크거나 같을 때
        	else {
        		roadBuffer += road[i];
        	}
        }
        System.out.println(totalPrice);
    }
}