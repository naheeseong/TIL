# Java 기초 문법 정리

2026-07-02
### 1. Scanner - nextInt() vs nextLine()
* 문제: `nextInt()` 후 `nextLine()`을 쓰면 버퍼에 엔터(`\n`)가 남아 다음 입력 무시됨.
* 해결법: `sc.nextInt();` 바로 아래에 공백 `sc.nextLine();`을 한 줄 추가해서 버퍼를 비워주기, sc.next(); 사용
.
### 2. toCharArray() 문자열 변환
* 개념: String을 한 글자씩 쪼개서 `char[]` 배열로 만들어 줌.
* 장점: 문자열의 길~~~~이에 맞춰 크기가 자동으로 설정됨.