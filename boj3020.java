package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj3020 {
	
	public static int N, M;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N은 종유석 석순의 총 개수, N은 짝수
		N = Integer.parseInt(st.nextToken());
		// M은 동굴 높이, 종유석과 장애물은 M보다 작다
		M = Integer.parseInt(st.nextToken());
		
		// 종유석과 석순을 따로 배열에 넣어줌
		int seok[] = new int[N/2];
		int jong[] = new int[N/2];
		
		for(int i=0; i<N/2; i++) {
			seok[i] = Integer.parseInt(br.readLine());
			jong[i] = Integer.parseInt(br.readLine());
		}
		
		// 이분탐색을 하기위해 정렬
		Arrays.sort(seok);
		Arrays.sort(jong);
		
		// 개똥벌레가 부딪치는 횟수를 구하기 위한 변수
		int min = Integer.MAX_VALUE;
		// 개똥벌레가 최소 횟수로 부딪치는 횟수
		int count = 0;
		
		// 높이 1~M까지 확인
		for(int i=1; i<=M; i++) {
			// 높이가 i일떄 머리박는 횟수
			// 종유석에서 높이를 M-i+1하는 이유는 거꾸로 매달려있기 때문에 맨 아래 위치를 구하기 위함
			// 이분탐색으로 종유석과 석순에 몇번씩 박는지 구함
			int seokSoon = binarySearch(seok, i);
			int jongYouSeok = binarySearch(jong, M-i+1);
					
			// 종유석 + 석순의 값을 최소값과 비교한 후
			// 만약 최소값이 더 크면 min값을 종유석 + 석순으로 변경하고
			// count = 1
			// 만약 같을경우 count++;
			if(min > jongYouSeok + seokSoon) {
				min = jongYouSeok + seokSoon;
				count = 1;
			}else if(min == jongYouSeok + seokSoon){
				count++;
			}
		}
		
		System.out.println(min + " " + count);

	}

	private static int binarySearch(int stone[], int height) {
		int start = 0;
		int end = N/2-1;
		
		// 높이를 기준으로 이분탐색을 진행
		// 만약 중앙 값보다 종유석/석순이 크면 end에 mid - 1
		// 아닐 경우 start에 mid + 1
		while(start <= end) {
			int mid = (start + end)/2;
			
			if(stone[mid] >= height) {
				end = mid-1;
			}else {
				start = mid+1;
			}
		}
		return stone.length - start;
	}
}
