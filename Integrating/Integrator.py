#Integrator

from FaceCount import faceCount
from OCR import ocr
from SpeechToText import s2Text
from Summarizer import summarizeMe
import pyttsx

filepath_face="pics/face/1.JPG"
filepath_ocr="pics/text/1.png"
filepath_speech="speech/1.wma"

engine=pyttsx.init()
#FaceContent=faceCount(filepath_face)
#OCRContent=ocr(filepath_ocr)
#SpeechContent=s2Text(filepath_speech)

#SummarizerContent=OCRContent

#Text 2 Speech
'''
engine=pyttsx.init()
engine.say("Do you want to Summarize the data?")
engine.runAndWait()

SpeechContent=s2Text(filepath_speech)

SpeechContent.lower()
if(SpeechContent=="yes"):
	OCRContent=ocr(filepath_ocr)
	SummarizerContent=OCRContent
	
	engine.say(SummarizerContent)
	engine.runAndWait()

else:
	OCRContent=ocr(filepath_ocr)
	engine.say(OCRContent)
	engine.runAndWait()


'''


FaceContent=faceCount(filepath_face)
print(FaceContent)
engine.say(FaceContent)
engine.runAndWait()








