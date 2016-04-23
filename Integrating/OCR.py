#Code for OCR, PASS the complete filepath as the argument. 
from havenondemand.hodclient import *
def ocr(filepath):
    hodClient = HODClient("84798130-5d8b-49c5-8a47-8f115aaa322f", "v1")

    hodApp = HODApps.OCR_DOCUMENT
    paramArr = {}
    paramArr["file"] = filepath
    paramArr["mode"] = "document_photo"

    response = hodClient.post_request(paramArr, hodApp, async=True)

    if response is None:
            error = hodClient.get_last_error();
            for err in error.errors:
                    print ("Error code: %d \nReason: %s \nDetails: %s\n" % (err.error,err.reason, err.detail))
    else:
            #print (response['jobID'])
            response = hodClient.get_job_result(response['jobID'])
            if response is None:
                    error = hodClient.get_last_error();
                    for err in error.errors:
                            print ("Error code: %d \nReason: %s \nDetails: %s\njobID: %s\n" % (err.error, err.reason, err.detail, err.jobID))
            else:
                    texts = response["text_block"]
                    resp = ""
                    return texts
