from locust import HttpLocust, TaskSequence, seq_task

import re
import json

class SampleScenario(TaskSequence):

    @seq_task(1)
    def get(self):
        self.client.get("/app/rest/get")

class RedmineUser(HttpLocust):
    task_set = SampleScenario
    min_wait = 500
    max_wait = 1000
