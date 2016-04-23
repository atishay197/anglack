#Integrator

from FaceCount import faceCount
from OCR import ocr
from SpeechToText import s2Text
from Summarizer import summarizeMe
import pyttsx
from OpenCamera import Camera

filepath_face="pics/face/1.JPG"
filepath_ocr="pics/text/2.png"
filepath_speech="speech/1.wma"

Camera(filepath_face)
engine=pyttsx.init()

button=1 #for button1
tap=1
engine=pyttsx.init()
if(button==1):
	engine.say("Do you want to Summarize the data? Single tap the mouse for yes and double tap for no")
	engine.runAndWait()
	if(tap==1):
		engine.say("You have selected For Summarizing")
		engine.runAndWait()
		SummarizerContent=summarizeMe(OCRContent)
		print(SummarizerContent.encode('utf-8'))
		engine.say(SummarizerContent)
		engine.runAndWait()

	else:
		engine.say("You have selected For Non-Summarizing")
		engine.runAndWait()
		OCRContent=ocr(filepath_ocr)
		SummarizerContent=summarizeMe(OCRContent)


	


	#MOUSE POINTER CHECK 

button=1 #for button1
tap=1
engine=pyttsx.init()
if(button==1):
	engine.say("Do you want to Summarize the data? Single tap the mouse for yes and double tap for no")
	engine.runAndWait()
	if(tap==1):
		engine.say("You have selected For Summarizing")
		engine.runAndWait()
		SummarizerContent=summarizeMe(OCRContent)
		print(SummarizerContent.encode('utf-8'))
		engine.say(SummarizerContent)
		engine.runAndWait()

	else:
		engine.say("You have selected For Non-Summarizing")
		engine.runAndWait()
		OCRContent=ocr(filepath_ocr)
		SummarizerContent=summarizeMe(OCRContent)


	


	#MOUSE POINTER CHECK 



#FaceContent=faceCount(filepath_face)
#OCRContent=ocr(filepath_ocr)
#SpeechContent=s2Text(filepath_speech)

#SummarizerContent=summarizeMe(OCRContent)

#Text 2 Speech
'''

SpeechContent=s2Text(filepath_speech)

SpeechContent.lower()
if(SpeechContent=="yes"):
	engine.say("WEELLLOOOO ......?")
	engine.runAndWait()

	OCRContent=ocr(filepath_ocr)
	SummarizerContent=summarizeMe(OCRContent)

	print(OCRContent)
	
	engine.say(SummarizerContent)
	engine.runAndWait()

else:
	OCRContent=ocr(filepath_ocr)

	print(OCRContent.encode('utf-8'))
	engine.say(OCRContent)
	engine.runAndWait()

'''



FaceContent=faceCount(filepath_face)
print(FaceContent)
engine.say(FaceContent)
engine.runAndWait()



#FaceContent=faceCount(filepath_face)
#OCRContent=ocr(filepath_ocr)
#SpeechContent=s2Text(filepath_speech)

#SummarizerContent=summarizeMe(OCRContent)

#Text 2 Speech
'''
SpeechContent=s2Text(filepath_speech)
SpeechContent.lower()
if(SpeechContent=="yes"):
	engine.say("WEELLLOOOO ......?")
	engine.runAndWait()
	OCRContent=ocr(filepath_ocr)
	SummarizerContent=summarizeMe(OCRContent)
	print(OCRContent)
	
	engine.say(SummarizerContent)
	engine.runAndWait()
else:
	OCRContent=ocr(filepath_ocr)
	print(OCRContent.encode('utf-8'))
	engine.say(OCRContent)
	engine.runAndWait()
'''



FaceContent=faceCount(filepath_face)
print(FaceContent)
engine.say(FaceContent)
engine.runAndWait()
