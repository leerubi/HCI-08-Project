/*
 * The Effect abstract class for the GauntletII project.
 */

#ifndef Effect_h
#define Effect_h
#import <SoftwareSerial.h>
#import <FastLED.h>

class Effect {

    protected:
        CRGB *leds;
        int width, height;
        SoftwareSerial mySerial;
        struct CRGB& pixel(int x, int y);
        bool inXRange(int x);
        bool inYRange(int y);
    
        void clearLeds();

    public:
        Effect(CRGB *leds, int width, int height, SoftwareSerial mySerial);

        virtual void start() {};

};

#endif
