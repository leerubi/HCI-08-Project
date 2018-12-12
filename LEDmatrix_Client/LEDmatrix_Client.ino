#include <SoftwareSerial.h>
#include <FastLED.h>
#include <AceRoutine.h>
//#include "Plasma.cpp"
#include "Pattern.cpp" 

#define WIDTH 9
#define HEIGHT 9
#define NUM_LEDS WIDTH * HEIGHT
#define DATA_PIN 7
using namespace ace_routine;
CRGB leds[NUM_LEDS];
int blueTx=2;   //Tx (보내는핀 설정)
int blueRx=3;   //Rx (받는핀 설정)
SoftwareSerial mySerial(blueTx, blueRx);  //시리얼 통신을 위한 객체선언
String myString=""; //받는 문자열
static bool Blink = true;
static int frame = 0;
static long pattern_time;
static uint16_t *sprite;
long last_time;
        int blink_count = 0;
        /*
COROUTINE(kakaoText)
{
  COROUTINE_BEGIN();
   while(true)
  {

    COROUTINE_YIELD();
    uint32_t *sprite = KaKaoTextflow;
    Serial.print("Pattern2");
    //1프레임 보여주고 End
     uint8_t saturation = random(0, 16) * 16;
        for (int spx = 0; spx < SPRITE_WIDTH; spx++) {
            for (int spy = 0; spy < SPRITE_HEIGHT; spy++) {
              int index = spy * (SPRITE_HEIGHT * (int)(TEXT1_LENGTH / 2)) + frame + (SPRITE_WIDTH) - spx;
                                  uint8_t spritePixel = 0;
              if(0 <= index && index < (SPRITE_HEIGHT * SPRITE_WIDTH * (int)(TEXT1_LENGTH / 2)))
 spritePixel = sprite[index];
                    if (spritePixel) {
                        leds[spy * SPRITE_HEIGHT + spx] = CHSV(spritePixel, saturation, 0xFF);//hue 0 -> RED
                    }
                   else
                   leds[spy * SPRITE_HEIGHT + spx] = CRGB::Black;
            }
        }

        FastLED.show();
        frame++;
        if(frame > SPRITE_WIDTH)
        frame = 0;
        //Blink = !Blink;
  }
  COROUTINE_END();
}
*/
//LED Matrix의 선이 연결된 부분이 오른쪽 위에 있다고 가정합니다.
COROUTINE(Sprite_Blink)
{
   COROUTINE_BEGIN();

   while(true)
  {
    COROUTINE_YIELD();
     //uint8_t saturation = random(14, 16) * 16;
        for (int spx = 0; spx < SPRITE_WIDTH; spx++) {
            for (int spy = 0; spy < SPRITE_HEIGHT; spy++) {
                    uint16_t spritePixel = sprite[spy * SPRITE_HEIGHT + spx];
                    if (spritePixel && Blink) {
                      uint8_t R = spritePixel / 0x100;
                      uint8_t G = spritePixel / 0x10;
                      uint8_t B = spritePixel % 0x10;
                      G -= (R* (0x10));
                        leds[spy * SPRITE_HEIGHT + (SPRITE_HEIGHT - spx - 1)] = CRGB(R*16,G*16,B*16);//hue 0 -> RED//hue 0 -> RED leds[spy * SPRITE_HEIGHT + spx] = CHSV(spritePixel, saturation, 0xFF);//hue 0 -> RED
                    }
                   else
                   leds[spy * SPRITE_HEIGHT + spx] = CRGB::Black;
            }
        }
        FastLED.show();
        Blink = !Blink;
  }
  COROUTINE_END();
}


void setup() {
  // put your setup code here, to run once:

    FastLED.addLeds<WS2812B, DATA_PIN, GRB>(leds, NUM_LEDS);
    Serial.begin(9600);   //시리얼모니터 
    mySerial.begin(9600); //블루투스 시리얼 개방
    CoroutineScheduler::setup();
    Sprite_Blink.runCoroutine();
    last_time = millis();
}

void loop() {
        String cache = "";
        char myChar;
    while(mySerial.available())  //mySerial에 전송된 값이 있으면
    {
      blink_count = 0;
      myChar = (char)mySerial.read();  //mySerial int 값을 char 형식으로 변환
      if('\n' == myChar)
        break;
      cache+=myChar;   //수신되는 문자를 myString에 모두 붙임 (1바이트씩 전송되는 것을 연결)*/
      delay(5);           //수신 문자열 끊김 방지
    }
        //  Serial.print(cache+ '\n');
    if(cache != "")
    myString = cache;
    Serial.println("input value: "+ myString); //시리얼모니터에 myString값 출력
    if(myString != "")
    {   
      blink_count++;
      clearMatrix();
      //1 and 2 for legacy
      if(myString=="kakao" || myString == "1")
      {       
        sprite = kakaoData;
        Sprite_Blink.runCoroutine();
        delay(500);
      }
      else if(myString == "heart" || myString == "2")
      {
         sprite = heartData;
         Sprite_Blink.runCoroutine();
         delay(500);
      }
      else if(myString=="call")
      {
        blink_count ++;
//        doPlasma();
      }
      else if(myString=="message")
      {
        sprite = messageData;
        Sprite_Blink.runCoroutine();
        delay(500);
      }
      else if(myString=="clear")
      {
        blink_count=0;
        sprite = blankData;
        Sprite_Blink.runCoroutine();
      }
      else//이상한 텍스트가 들어온 경우(위에 clear랑 같긴 한데, 혹시 몰라서 분리해둠)
      {
        myString="";
        blink_count=0;
        sprite = blankData;
        Sprite_Blink.runCoroutine();
      }
    }
      if(blink_count > 8)
      {
        clearMatrix();
        sprite = idleData;
        Sprite_Blink.runCoroutine();
        delay(500);
        myString="";  //myString 변수값 초기화
      }
}
/*
void doDeadChannel() {
    DeadChannel deadChannel(leds, WIDTH, HEIGHT);
    deadChannel.start();
}*/

void clearMatrix()
{
  for (int spx = 0; spx < SPRITE_WIDTH; spx++) {
            for (int spy = 0; spy < SPRITE_HEIGHT; spy++) {
              leds[spy * SPRITE_HEIGHT + spx] = CRGB::Black;
            }
  }
  FastLED.show();
}
/*
void doPlasma() {
    Plasma plasma(leds, WIDTH, HEIGHT, mySerial);
    plasma.start();
}


void doTwinkle() {
    Twinkle twinkle(leds, WIDTH, HEIGHT, mySerial, true, true);
    twinkle.start();
}

void doSnake() {
    Snake snake(leds, WIDTH, HEIGHT, mySerial);
    snake.start();
}


void doLife() {
    Life life(leds, WIDTH, HEIGHT, 56);
    life.start();
}
void doSprite() {
    Sprite sprite(leds, WIDTH, HEIGHT, mySerial);
    sprite.start();
}*/
