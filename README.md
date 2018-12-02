Project ``LED Matrix``
=======================
2018 POSTECH HCI Project (Team 8)

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

Members
-----------

- [Dajin Lee](https://github.com/leerubi)
  - Android Application UI
  - Making LED Matrix
  
- [Jeongwoo Son](https://github.com/ngng0274)
  - Arduino Implementation
  - Making LED Matrix 

- [Seongbin Baek](https://github.com/P17seongbin)
  - Android Application UI
  - Making LED Matrix


Progress Report
-------------

- UI: 안드로이드 애플리케이션 구현
- AD: 아두이노 구현
- LED: LED Matrix 제작
- REP: 프로젝트 보고서 작성

### Milestone 1 (18/11/14 ~ 18/11/25)

| actual  | estimated | story | description |
| ------  | --------- | ----- | ----------- |
|    2     | 3 units   | UI_S01 |  UI 디자인 설계    |
|    5     | 5 units   | LED_S01 |  LED Matrix 틀 제작  |


- LED_S01: 정통연 디시설 실습실에서 진행
- UI_S01: 안드로이드 앱 구현 언어 - Kotiln
  - Task 1 : 페이지 구성 (``SW 디자인.doc`` 참고)
    1. 메인 화면 (나중에)
        - 네비게이션 드로워 없이 주요 버튼 3개와 블루투스 연결 여부가 화면에 표시될 예정.
    2. 메인 버튼1 - 패턴 관리 (성빈)
    3. 메인 버튼2 - 어플 연동 (다진)
    4. 메인 버튼3 - 무드 LED (나중에)

### Milestone 2 (18/11/26 ~ 18/12/02)

| actual  | estimated | story | description |
| ------  | --------- | ----- | ----------- |
|     2 units    | 5 units  | UI_S02 | 메인 버튼1(패턴 관리) UI 구현  |
|    4 units    | 5 units  | UI_S03 | 메인 버튼2(어플 연동) UI 구현  |
|    15 units     | 5 units   | LED_S02 |  LED Matrix 납땜  |
|    1 units     | 1 units  | AD_S02 | 패턴 샘플 띄워보기  |
|    4 units     | 4 units  | REP_S01 | 첫번째 프로젝트 레포트 작성 및 데모 준비  |

- ``아크릴을 자를 수 있는 일물실 실험실을 빌릴 수 있는지 연락해보기.`` (18/11/26 성빈이 컨택 완료)
- 글루건, 가위에 반납해야 함.
- 피드백을 바탕으로 Milestone task를 수정. (18/11/27 미팅)
- 프로젝트 레포트 셀프 피드백
  - 검토를 꼼꼼히 할 것 (오타, 이상한 문장, 출처, 간트 차트에 쓸데 없는 줄 등 끝마무리가 허술했음)
  - 하루에 다 하려고 하지 말 것

### Milestone 3 (18/12/03 ~ 18/12/09)

| actual  | estimated | story | description |
| ------  | --------- | ----- | ----------- |
|         | 1 units  | AD_S01 | 어플과 LED Matrix 간 블루투스 통신  |
|         |  5 units   | UI_S04 |  패턴 편집 및 적용 기능 구현(메인 버튼1)   |
|         |  5 units   | UI_S05 |  LED Matrix-애플리케이션 간 알림 설정 기능 구현(메인 버튼2)   |
|         |  2 units    | AD_S03 |   패턴 이미지 샘플 만들기    |

- 12/5 (수) 오후 3시 반 일물실 실험실에서 아크릴판 자르고 부착 완료.
- 12/7 (금) Prototype evaluation 오후 2시 지곡회의실
  - [설문지 작성](https://goo.gl/forms/bfaJq28ojb6VUp4z2)
  - [Task 설계](http://www.11math.com/calc#D9133F7E)


### Milestone 4 (18/12/10 ~ 18/12/14)

| actual  | estimated | story | description |
| ------  | --------- | ----- | ----------- |
|      |      |        |        |
|         | 4 units  | REP_S01 | 두번째 프로젝트 레포트 작성 및 데모 준비  |

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

- References
  - [Saving activity state](https://stackoverflow.com/questions/151777/saving-android-activity-state-using-save-instance-state)
  - [Switching button](https://android--code.blogspot.com/2018/02/android-kotlin-switch-button-example.html)
  - [Notification information1](http://snowdeer.github.io/android/2017/03/26/notification-listener-service-sample/)
  - [Notification information2](http://susemi99.kr/1413)
  - [Broadcast Receiver](https://developer88.tistory.com/34)
  - [Bluetooth application development](https://www.intorobotics.com/how-to-develop-simple-bluetooth-android-application-to-control-a-robot-remote/)
