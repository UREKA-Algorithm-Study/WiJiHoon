import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 입력값 : 좌표 4자리 -> 4자리 좌표는 연결해서 지하철이됨\
 * 		   A와 B의 좌표
 * 
 * 지하철을 이용하거나 이용하지 않고 최단거리로 A와 B가 만나는 최단거리를 구하시오.
 * 
 * 그냥 만나는 경우(사람 지하철 내부) 지하철 최대 최소 x, y값을 대상으로 현재 위치랑 비교했을때 최소값
 * 그냥 만나는 경우(사람 지하철 외부) 지하철 최대 최소 x, y값을 대상으로 현재 위치랑 비교했을때 최소값의 합
 * 지하철을 타는 경우 (|x2-x1| + |y2-y1|)
 * */
public class cj{
	// 만약 지하철 좌표가 랜덤으로 들어오는거면 x를 기준으로 정렬후 y를 기준으로 정렬
	/*public static int[][] subway = {{-2, 2}, {2, 2}, {2, -2}, {-2, -2}};
	public static int[] A = {1, 2};
	public static int[] B = {-2, -2};*/
	
	public static int subway[][] = new int[4][2];
	public static int A[] = new int[2];
	public static int B[] = new int[2];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i=0; i<4; i++) {
			for(int j=0; j<2; j++) {
				subway[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		A[0] = Integer.parseInt(st.nextToken());
		A[1] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		B[0] = Integer.parseInt(st.nextToken());
		B[1] = Integer.parseInt(st.nextToken());
		
		// 뚜벅이
		int walk = Math.abs(A[0] - B[0]) + Math.abs(A[1] - B[1]);

		System.out.println(Math.min(walk, takeTheSubway(A)+takeTheSubway(B)));
	}

	// 지하철타기
	private static int takeTheSubway(int[] person) {
		int subwayMinX = Math.min(subway[0][0], Math.min(subway[1][0], Math.min(subway[2][0], subway[3][0])));
		int subwayMinY = Math.min(subway[0][1], Math.min(subway[1][1], Math.min(subway[2][1], subway[3][1])));
		int subwayMaxX = Math.max(subway[0][0], Math.max(subway[1][0], Math.max(subway[2][0], subway[3][0])));
		int subwayMaxY = Math.max(subway[0][1], Math.max(subway[1][1], Math.max(subway[2][1], subway[3][1])));
		
		int distance = 0;
		/*
		* 좌상
		* (person[0] < subwayX && person[1] >subwayY)
		* 우상
		* (person[0] > subwayX && person[1] > subwayY)
		* 우하
		* (person[0] > subwayX && person[1] < subwayY)
		* 좌하
		* (person[0] < subwayX && person[1] <subwayY)
		*/
		// 사람이 지하철 라인 밖 대각선에 있는경우
		if((person[0] < subwayMinX && person[1] > subwayMaxY) ||
				(person[0] > subwayMaxX && person[1] > subwayMaxY) ||
				(person[0] > subwayMaxX && person[1] < subwayMinY) ||
				(person[0] < subwayMinX && person[1] < subwayMinY)) {
			distance = Math.min(Math.abs(person[0] - subwayMinX),Math.abs(person[0] - subwayMaxX)) +
					Math.min(Math.abs(person[1] - subwayMinY),Math.abs(person[1] - subwayMaxY));
		}else { // 지하철 가로 세로 축에 있는경우
			distance = Math.min(Math.abs(person[0] - subwayMinX),
					Math.min(Math.abs(person[0] - subwayMaxX), 
					Math.min(Math.abs(person[1] - subwayMinY),
					Math.abs(person[1] - subwayMaxY))));
		}
		return distance;
	}

}
