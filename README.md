``메인 버튼2 (어플 연동)`` Documentation
=======================
implemented by [Dajin Lee](https://github.com/leerubi/)

- PatternListActivity()
  - UI만 구현한 상태
  - Switch로 어플 알림을 활성화/비활성화
    - 각 어플 알림 받아오는 기능 추후 구현
  - P Button을 누르면 어플의 패턴 관리 액티비티로 전환
    - 성빈이가 패턴 관리 쪽 구현하면 추후 연동
  - 어플 리스트 및 아이콘을 받아오는 기능 추후 구현
  - 키워드 알림/꺼짐/켜짐 아무 텍스트를 누르면 KeywordAlarmActivity로 전환
  - ![ex_screenshot1](/PatternListActivity.png?raw=true)

- KeywordAlarmActivity()
  - UI만 구현한 상태
  - Switch로 키워드 알림을 활성화/비활성화
  - 키워드 추가 텍스트를 누르면 키워드 줄이 추가되는 기능 구현
  - 키워드 입력 칸을 누르면 키보드가 올라오는 기능 구현
  - 입력한 키워드를 토대로 키워드 알림하는 기능 구현
    - 푸시 알림의 텍스트 추출하는 것부터 구현
  - ![ex_screenshot2](/KeywordAlarmActivity.png?raw=true)
