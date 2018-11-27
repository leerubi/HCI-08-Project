``Project Goal``
===============
- 스마트폰의 중요한 알림을 조명장치를 통해 확인할 수 있게 한다.

``Project Purpose``
==================
- 스마트폰의 진동모드, 무음모드를 사용하는 경우 중요한 알림을 놓치는 경우가 많다.
- 잦은 알림은 사람에게 스트레스를 유발하지만 조명장치를 통해 부정적인 영향을 줄인다.

``Functionality``
===============
- 스마트폰의 알림에 따른 조명장치의 변화
추가 사항
- 조명장치의 패턴을 사용자가 변경할 수 있는 기능

``메인 버튼2 (어플 연동)`` Documentation
=======================
implemented by [Dajin Lee](https://github.com/leerubi/)

- ApplicationListActivity()
  - UI만 구현한 상태
  - Switch로 어플 알림을 활성화/비활성화
    - 각 어플 알림 받아오는 기능 추후 구현
  - P Button을 누르면 어플의 패턴 관리 액티비티로 전환
    - 성빈이가 패턴 관리 쪽 구현하면 추후 연동
  - 어플 리스트 및 아이콘을 받아오는 기능 추후 구현
  - 키워드 알림/꺼짐/켜짐 아무 텍스트를 누르면 KeywordAlarmActivity로 전환
  - [ex_screenshot1](/ApplicationListActivity.png?raw=true)

- KeywordAlarmActivity()
  - UI만 구현한 상태
  - Switch로 키워드 알림을 활성화/비활성화
  - 키워드 추가 텍스트를 누르면 키워드 줄이 추가되는 기능 구현
  - 키워드 입력 칸을 누르면 키보드가 올라오는 기능 구현
  - 입력한 키워드를 토대로 키워드 알림하는 기능 구현
    - 푸시 알림의 텍스트 추출하는 것부터 구현
  - [ex_screenshot2](/KeywordAlarmActivity.png?raw=true)
