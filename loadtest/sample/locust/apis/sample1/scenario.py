from locust import TaskSet, task
from locust.contrib.fasthttp import FastHttpLocust
from locust.wait_time import constant

import json

class Sample1Scenario(TaskSet):

    APPLICATION_JSON_CONTENT_TYPE_HEADER = {"content-type": "application/json"}

    def on_start(self):
        self._post_id = 1
        self._post_value = "sample1"

    def on_stop(self):
        pass

    @task(1)
    def call(self):
        payload = {
            "id": self._post_id,
            "value": self._post_value
        }

        self.client.post(
            "/app/sample1/api",
            data=json.dumps(payload),
            headers=self.APPLICATION_JSON_CONTENT_TYPE_HEADER
        )

class HttpLocustUser(FastHttpLocust):
    task_set = Sample1Scenario

    # Tast 実行待ち時間 (min, max)
    wait_time = constant(1)
