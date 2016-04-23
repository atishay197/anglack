import cv2
import cv2.cv as cv
import os
import time

def Camera(filepath):
	cv.NamedWindow(":)", cv.CV_WINDOW_AUTOSIZE)
	capture = cv.CaptureFromCAM(0)

	file = filepath

	camera_port = 0
	 
	#ramp_frames = 30
	 
	# Now we can initialize the camera capture object with the cv2.VideoCapture class.
	# All it needs is the index to a camera port.
	camera = cv2.VideoCapture(camera_port)

	def repeat():

	    frame = cv.QueryFrame(capture)
	    cv.ShowImage(":)", frame)
	    
	def get_image():
	   	 # read is the easiest way to get a full image out of a VideoCapture object.
	   	 retval, im = camera.read()

	   	 return im

	while True:
	    repeat()
	    #c = cv.WaitKey(10)
	    imag=get_image()

	    cv2.imwrite(file, imag)
	    if cv.WaitKey(10) == 27:
	    	break
