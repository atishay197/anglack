#FaceCount
from havenondemand.hodclient import *
from PIL import Image
def faceCount(filepath):

	hodClient = HODClient("a1d1963c-6c3e-487c-b339-224747ac81b2", "v1")
	#def facedetect(filepath):
	hodApp = HODApps.DETECT_FACES
	paramArr = {}
	paramArr["file"] = filepath
	paramArr["additional"] = "True"
	im=Image.open(paramArr["file"])
	im.size
	print im.size[0]

	response = hodClient.post_request(paramArr, hodApp, async=False)

	if response is None:
		error = hodClient.get_last_error();
		for err in error.errors:
			print ("Error code: %d \nReason: %s \nDetails: %s\n" % (err.error,err.reason, err.detail))
	else:
		x = response['face']
		NoOfPeople = len(x)
		if(NoOfPeople == 0):
			statement = "No one in the vicinity\n"
		else:
			statement = "Total of " + str(len(x)) + " people.\n"
			for person in x:
				print person
				position = " "
				try:
					personage =  person['additional_information']
					age = personage['age']
				except KeyError:
					age = "person"
				if(person['left'] < im.size[0]/2):
					position = "left"
				else:
					position = "right"
				statement += age + " on your " + position + ".\n"
			# print statement
			if response is None:
				error = hodClient.get_last_error();
				for err in error.errors:
					print ("Error code: %d \nReason: %s \nDetails: %s\njobID: %s\n" % (err.error, err.reason, err.detail, err.jobID))

	return statement


#	return statement