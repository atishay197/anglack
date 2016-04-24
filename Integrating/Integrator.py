#Integrator

from FaceCount import faceCount
from OCR import ocr
from SpeechToText import s2Text
from Summarizer import summarizeMe
import pyttsx
import pyautogui
from OpenCamera import Camera


f=open('button.txt','r')
#print f
button=int(f.read()) 
#print(button)
tap=1

filepath="pics/img.png"

filepath_speech="speech/1.wma"


engine=pyttsx.init()
rate=engine.getProperty('rate')
engine.setProperty('rate',rate)

engine.say("Hello and welcome")

engine.runAndWait()
	

#print(OCRContent)


engine=pyttsx.init()

engine.say("Do you want to know whats arround you? or you want to read something? Press F for surroundings and j for Reading!!")
engine.runAndWait()

print("Do you want to know whats arround you? or you want to read something? Press F for surroundings and j for Reading!!")

button=raw_input()



if(button=='j'):

	engine.say("You Have selected to reed, Switching on camera..., Press Escape to Capture")

	engine.runAndWait()
	Camera(filepath)

	OCRContent=ocr(filepath)

	engine.say("Do you want to Summarize the data you just snapped? Press F for Yes and J for no")
	engine.runAndWait()
	input1=raw_input()

	#time.sleep(20)
	if(input1=='f'):
		engine.say("You have selected For Summarizing")
		engine.runAndWait()
		SummarizerContent=summarizeMe(OCRContent)
		engine.say(SummarizerContent)
		engine.runAndWait()
		print(SummarizerContent.encode('utf-8'))

	elif(input1=='j'):

		engine.say("You have selected For NOT-Summarizing")
		engine.runAndWait()
		print (OCRContent.encode('utf-8'))
		engine.say(OCRContent)
		engine.runAndWait()

	else:
		engine.say("YOU only had to press between f or j GEnius.")
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



	engine.say("Thank-You for using this")
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












