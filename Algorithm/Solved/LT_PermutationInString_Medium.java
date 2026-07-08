import java.util.Arrays;

/**
 * LeetCode 567. Permutation in String
 * 유형: 고정 크기 슬라이딩 윈도우 (Sliding Window)
 * * - s1의 순열(알파벳 구성)이 s2의 연속된 부분 문자열로 존재하는지 확인하는 문제
 * - 매번 새로 카운트하지 않고, 창문이 이동할 때 [새로 들어온 글자 +, 나간 글자 -]로 O(N) 최적화
 */
public class LT_PermutationInString_Medium {

    // 릿코드 채점 서버 제출용 핵심 메서드
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();

        // [안전장치 1] s1이 s2보다 길면 절대로 순열이 포함될 수 없음
        if (len1 > len2) return false;

        int[] s1Count = new int[26];
        int[] s2Count = new int[26];

        // 1단계: s1 전체와 s2의 첫 창문 크기만큼 알파벳 카운트
        for (int i = 0; i < len1; i++) {
            s1Count[s1.charAt(i) - 'a']++;
            s2Count[s2.charAt(i) - 'a']++;
        }

        // 2단계: 첫 창문 영역이 일치하면 바로 true
        if (Arrays.equals(s1Count, s2Count)) return true;

        // [안전장치 2] 길이가 같았는데 위에서 통과 못 했다면 무조건 불일치이므로 런타임 에러 방지용 종료
        if (len1 == len2) return false;

        // 3단계: 슬라이딩 윈도우로 창문 한 칸씩 밀기
        for (int i = len1; i < len2; i++) {
            s2Count[s2.charAt(i) - 'a']++;               // 들어온 글자 증가
            s2Count[s2.charAt(i - len1) - 'a']--;        // 나간 글자 감소

            // 빈도 배열이 완벽히 일치하는지 비교
            if (Arrays.equals(s1Count, s2Count)) {
                return true;
            }
        }

        return false;
    }

    // 인텔리제이 로컬 실행 및 디버깅용 메인 메서드
    public static void main(String[] args) {
        LT_PermutationInString_Medium tester = new LT_PermutationInString_Medium();

        // 예시 1
        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println("테스트 1 결과: " + tester.checkInclusion(s1, s2)); // 출력: true

        // 예시 2
        String s3 = "ab";
        String s4 = "eidboaoo";
        System.out.println("테스트 2 결과: " + tester.checkInclusion(s3, s4)); // 출력: false

        // 예시 3 (런타임 에러 수비 확인용 극단적 케이스)
        System.out.println("테스트 3 결과: " + tester.checkInclusion("a", "a")); // 출력: true
    }
}