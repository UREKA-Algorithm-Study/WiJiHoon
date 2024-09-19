package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj6198 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Stack<Integer> stack = new Stack<>();
		
		int N = Integer.parseInt(br.readLine());
		long ans = 0;
		
		for(int i=0; i<N; i++) {
			int height = Integer.parseInt(br.readLine());
			// 이전에 있는 빌딩의 높이가 현재 빌딩의 높이 보다 낮으면 pop
			while(!stack.isEmpty() && stack.peek() <= height) {
				stack.pop();
			}
			ans += stack.size(); // 볼 수 있는 빌딩 옥상 수
			stack.push(height); // 맨 앞에 있는 빌딩

		}
		System.out.println(ans);
	}

}
