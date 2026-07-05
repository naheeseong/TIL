// 문제 링크: https://leetcode.com/problems/range-sum-query-2d-immutable/

/*
   - 리트코드 2D 누적합 문제! 1D와 달리 2차원 배열에서 직사각형 영역의 합을 구해야 함.
   - prefixSum 배열의 크기를 (N+1) x (M+1)로 설계하지 않으면, sumRegion에서 row1-1, col1-1 할 때 0일 경우 마이너스 인덱스 에러(ArrayIndexOutOfBoundsException) 발생함.
   - 2D 누적합 공식은 포함-배제 원리(inclusion-exclusion principle)를 사용: 아래 + 왼쪽 - 대각선(중복 제거) + 현재값.
   - 직사각형 구간 합 쿼리는 4개의 누적합 값으로 O(1)에 계산 가능: 우하단 - 우상단 - 좌하단 + 좌상단.
   - 무조건 누적 합 배열은 0번 행과 0번 열을 0으로 비워두고, 1번부터 차례대로 쌓는 게 필수.
*/
public class LT_RangeSumQuery2D_Medium {

    private int[][] prefixSum;

    // 생성자 역할을 하는 메서드 (클래스명과 일치)
    public LT_RangeSumQuery2D_Medium(int[][] matrix) {
        // 예외 처리 안전장치
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;

        int rowCount = matrix.length;
        int colCount = matrix[0].length;

        prefixSum = new int[rowCount + 1][colCount + 1];

        for (int i = 1; i <= rowCount; i++) {
            for (int j = 1; j <= colCount; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1]
                        - prefixSum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    // 릿코드 핵심 풀이 메서드
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return prefixSum[row2 + 1][col2 + 1]
                - prefixSum[row1][col2 + 1]
                - prefixSum[row2 + 1][col1]
                + prefixSum[row1][col1];
    }

    // 인텔리제이에서 실행하기 위한 메인 메서드
    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };

        // 하나로 합쳤기 때문에 내 자신을 객체로 생성
        LT_RangeSumQuery2D_Medium obj = new LT_RangeSumQuery2D_Medium(matrix);

        System.out.println(obj.sumRegion(2, 1, 4, 3)); // 출력: 8
        System.out.println(obj.sumRegion(1, 1, 2, 2)); // 출력: 11
        System.out.println(obj.sumRegion(1, 2, 2, 4)); // 출력: 12
    }
}