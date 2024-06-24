package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj2294 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 문자열 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine()); // " "기준으로 문자열 나눠주기, new StringTokenizer(br.readLine(), " ")과 같음 뒤에 스킵 가능
		
		int N = Integer.parseInt(st.nextToken()); // 동전 N개
		int K = Integer.parseInt(st.nextToken()); // 금액 K개
		
		int coin[] = new int[N]; // 동전 배열
		int dp[] = new int[K+1]; // dp용 배열
			
		Arrays.fill(dp, 10001); // 문제에서 K의 최대 범위가 10000까지 였기에 10001을 모든 배열에 넣어줌
		
		// 동전 입력
		for(int i=0; i<N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
			if(coin[i] <= K) { // // coin[i]의 값이 K보다 더 큰게 들어오는 경우 dp배열의 범위를 벗어나 오류 발생 
				dp[coin[i]] = 1;
			}
		}
		
		for(int i=0; i<N; i++) {
				//동전배열 1, 5, 12 순으로 탐색
				for(int j=coin[i]+1; j<=K; j++) { 
					// 금액 배열 coin[i]+1 ~ K 까지 돌면서 dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1); 실행
					// dp[coin]의 값은 동전 입력할때 1 넣었으니 dp는 coin[i] + 1 값부터 탐색
					dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
				}		
		}
		
		// 만약 K가 동전으로 만들수 없는 금액이면  최초로 들어간 10001이 출력되기에 -1
		if(dp[K] == 10001) {
			System.out.println(-1);
		}else {
			System.out.println(dp[K]);
		}

	}
}
