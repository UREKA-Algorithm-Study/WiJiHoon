package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj2504 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int ans = 0;
        int temp = 1;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char chr = str.charAt(i);

            switch (chr) {
                case '(':
                    stack.add(chr);
                    temp *= 2;
                    break;
                case '[':
                    stack.add(chr);
                    temp *= 3;
                    break;
                case ')':
                    if (stack.isEmpty() || stack.peek() != '(') { // 무조건 스택이 비었는지 먼저 확인해야함, 비었는데 peek()를 할경우 오류 발생
                        System.out.println(0);
                        return;
                    }
                    if (str.charAt(i - 1) == '(') { // [([])] 라고 할 경우 가장 가운데 []부분을 찾음 저 위치에서 모든 값을 ans에 더함
                        ans += temp;
                    }
                    stack.pop();
                    temp /= 2;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.peek() != '[') {
                        System.out.println(0);
                        return;
                    }
                    if (str.charAt(i - 1) == '[') {
                        ans += temp;
                    }
                    stack.pop();
                    temp /= 3;
                    break;
            }
        }

        // 마지막 값이 '(' 이거나 '['인 경우 위에서 못걸러내기에 마지막으로 체크
        if (!stack.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(ans);
        }
    }
}
