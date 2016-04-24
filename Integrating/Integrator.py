#Integrator

from FaceCount import faceCount
from OCR import ocr
from SpeechToText import s2Text
from Summarizer import summarizeMe
import pyttsx
import pyautogui
from OpenCamera import Camera


f=open('button.txt','r')
print f
button=int(f.read()) 
print(button)
tap=1

filepath="pics/img.JPG"

filepath_speech="speech/1.wma"


engine=pyttsx.init()

engine.say("Hello and welcome")

engine.runAndWait()
	

#print(OCRContent)


engine=pyttsx.init()

if(button==1):

	engine.say("You Have selected to reead, Switching on camera..., Press Escape to Capture")

	engine.runAndWait()
	Camera(filepath)

	OCRContent=ocr(filepath)

	engine.say("Do you want to Summarize the data you just snapped? Single tap the mouse for yes and double tap for no")
	engine.runAndWait()
	#time.sleep(20)
	if(0):
		engine.say("You have selected For Summarizing")
		engine.runAndWait()
		SummarizerContent=summarizeMe(OCRContent)
		print(SummarizerContent.encode('utf-8'))
		engine.say(SummarizerContent)
		engine.runAndWait()

	else:

		engine.say("You have selected For NOT-Summarizing")
		engine.runAndWait()
		print (OCRContent)
		engine.say(OCRContent)
		engine.runAndWait()
		
else:
	
	engine.say("You Have selected   observe the environment, Switching on camera... Press escape to Capture")
	engine.runAndWait()
	Camera(filepath)
	engine.say("Captured Pic")
	engine.runAndWait()
		
	FaceContent=faceCount(filepath)
	print(FaceContent)
	engine.say(FaceContent)
	engine.runAndWait()
	



	


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












