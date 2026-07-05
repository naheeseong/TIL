// 문제 링크: https://leetcode.com/problems/consecutive-numbers-sum/

/*
   - 수학 공식으로 풀이한 문제.
   - 핵심: 연속된 k개의 정수를 start부터 시작할 때, start = (n - k(k-1)/2) / k 공식 이용.
   - 각 k에 대해 remainder = n - k(k-1)/2가 k로 나누어떨어지는지만 확인하면 됨.
   - Sliding Window는 n이 10^9일 때 TLE 발생하므로, k(k-1)/2 < n 조건으로 O(√n) 최적화.
   - 오버플로우 방지: k * (k - 1) / 2 계산 시 long 타입 사용.
*/

// 리트코드에 제출해서 통과한 클래스
class ConsecutiveNumbersSum {

    public int consecutiveNumbersSum(int n) {
        int count = 1;  // k=1: n 자신은 항상 카운트

        // k: 연속된 정수의 개수
        // k(k-1)/2 < n일 때만 start가 양수가 될 수 있음
        for (int k = 2; k * (k - 1) / 2 < n; k++) {
            // start = (n - k(k-1)/2) / k
            // remainder가 k로 나누어떨어지면 start는 정수
            long remainder = n - (long)k * (k - 1) / 2;

            if (remainder % k == 0) {
                count++;
            }
        }

        return count;
    }
}

// 인텔리제이에서 실행하기 위한 메인 클래스
public class LT_ConsecutiveNumbersSum_Hard {
    public static void main(String[] args) {

        ConsecutiveNumbersSum obj = new ConsecutiveNumbersSum();

        // 테스트 케이스 1
        int n1 = 5;
        System.out.println(obj.consecutiveNumbersSum(n1)); // 출력: 2
        // 설명: 5 = 5, 5 = 2 + 3

        // 테스트 케이스 2
        int n2 = 9;
        System.out.println(obj.consecutiveNumbersSum(n2)); // 출력: 3
        // 설명: 9 = 9, 9 = 4 + 5, 9 = 2 + 3 + 4

        // 테스트 케이스 3
        int n3 = 15;
        System.out.println(obj.consecutiveNumbersSum(n3)); // 출력: 4
        // 설명: 15 = 15, 15 = 7 + 8, 15 = 4 + 5 + 6, 15 = 1 + 2 + 3 + 4 + 5

        // 추가 테스트 케이스 (큰 수)
        int n4 = 1000000000;
        System.out.println(obj.consecutiveNumbersSum(n4)); // 출력: 15
        // 큰 수에서도 O(√n) 덕분에 빠르게 처리됨
    }
}