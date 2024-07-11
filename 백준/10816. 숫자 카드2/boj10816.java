package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 중복되는 수가 있기떄문에 lower_bound와 upper_bound를 활용해서 구해야함
// lower_bound - 중복된 수 중 맨 아래 값, upper_bound - 중복된 수 중 맨 위 값
// 개수 구하는 방법 upper_bound - lower_bound
public class boj10816 {

	public static int N;
	public static int arr[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int M = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			int n = Integer.parseInt(st.nextToken());
			sb.append(upper_bound(n) - lower_bound(n) + " ");
		}
		
		System.out.println(sb);
	}

	private static int upper_bound(int n) {// 처음으로 마주하는 key값 이상을 갖고있는 인덱스를 찾음
		int left = 0;
		int right = N;
		
		while(left < right) {
			int mid = (right + left) / 2;
			
			if(n < arr[mid]) {
				right = mid;
			}else {
				left = mid + 1;
			}
		}
		return left;
	}

	private static int lower_bound(int n) {// 찾고자하는 값을 처음으로 초과하는 값을 가진 인덱스
		int left = 0;
		int right = N;
		
		while(left < right) {
			int mid = (right + left) / 2;
			
			if(n <= arr[mid]) {
				right = mid;
			}else {
				left = mid + 1;
			}
		}
		return left;
	}
}
