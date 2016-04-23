from havenondemand.hodclient import *
from PIL import Image

hodClient = HODClient("a1d1963c-6c3e-487c-b339-224747ac81b2", "v1")

hodApp = HODApps.DETECT_FACES
paramArr = {}
paramArr["file"] = "testdata/download1.jpg"
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
	x = list(response['face'])
	NoOfPeople = len(x)
	for person in x:
		print person
		position = " "
		age = " "
		if(response['face']['left'] < im.size[0]/2):
			position = "left"
		else:
			position = "right"
		statment += 
	if response is None:
		error = hodClient.get_last_error();
		for err in error.errors:
			print ("Error code: %d \nReason: %s \nDetails: %s\njobID: %s\n" % (err.error, err.reason, err.detail, err.jobID))