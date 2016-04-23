from havenondemand.hodclient import *

hodClient = HODClient("a1d1963c-6c3e-487c-b339-224747ac81b2", "v1")
hodApp = ""

# callback function
def asyncRequestCompleted(jobID, error, **context):
    if error is not None:
        for err in error.errors:
            print ("Error code: %d \nReason: %s \nDetails: %s\n" % (err.error,err.reason, err.detail))
    elif jobID is not None:
        hodClient.get_job_status(jobID, requestCompleted, **context)

def requestCompleted(response, error, **context):
	resp = ""
	if error is not None:
		for err in error.errors:
			if err.error == ErrorCode.QUEUED:
				# wait for some time then call GetJobStatus or GetJobResult again with the same jobID from err.jobID
				print ("job is queued. Retry in 10 secs. jobID: " + err.jobID)
				time.sleep(10)
				hodClient.get_job_status(err.jobID, requestCompleted, **context)
				return
			elif err.error == ErrorCode.IN_PROGRESS:
				# wait for some time then call GetJobStatus or GetJobResult again with the same jobID from err.jobID
				print ("task is in progress. Retry in 60 secs. jobID: " + err.jobID)
				time.sleep(60)
				hodClient.get_job_status(err.jobID, requestCompleted, **context)
				return
			else:
				resp += "Error code: %d \nReason: %s \nDetails: %s\njobID: %s\n" % (err.error,err.reason, err.jobID)
	elif response is not None:
		app = context["hodapp"]
		if app == HODApps.RECOGNIZE_SPEECH:
			documents = response["document"]
			for doc in documents:
				resp += doc["content"] + "\n"
			paramArr = {}
			print resp
			paramArr["text"] = resp


hodApp = HODApps.RECOGNIZE_SPEECH
paramArr = {}
paramArr["file"] = "testdata/Voice.aac"

context = {}
context["hodapp"] = hodApp

hodClient.post_request(paramArr, hodApp, async=True, callback=asyncRequestCompleted, **context)