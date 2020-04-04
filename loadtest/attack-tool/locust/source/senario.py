from locust import HttpLocust, TaskSequence, seq_task

import re
import json

class SampleScenario(TaskSequence):

    @seq_task(1)
    def get(self):
        response = self.client.get("/app/rest/get")
        response_data = json.loads(response.text)
        self.get_response_id = response_data["id"]
        #print(response_data)

    @seq_task(2)
    def post(self):
        response = self.client.post(
            "/app/rest/post",
            data=json.dumps({'id': self.get_response_id, 'value':'sampleValue'}),
            headers={'content-type': 'application/json'}
        )
        #response_data = json.loads(response.text)
        #print(response_data)

    @seq_task(3)
    def getBinary(self):
        self.client.get("/app/rest/getBinary")

    @seq_task(4)
    def postBinary(self):
        self.client.post(
            "/app/rest/postBinary",
            files={'file': open("C:\\umejima\\work\\public\\test\\attack-tool\\locust\\source\\postBinary\\sample", 'rb')},
            headers={'content-type': 'application/octet-stream'}
        )


# task 間の待機時間の min_wait と max_wait (ms)
# この範囲で task 間ランダム wait される。
class RedmineUser(HttpLocust):
    task_set = SampleScenario
    min_wait = 500
    max_wait = 1000
