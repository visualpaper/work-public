from locust import TaskSet, task

from actions.getbinary import GetBinaryAction

import json
import random
from pathlib import Path

class BigBinaryDownloadSubTaskSet(TaskSet):

    def on_start(self):
        self._action = GetBinaryAction(self.client)

    @task(1)
    def call(self):
        response = self._action.call(
            "007d2a9c-8ac4-4070-8994-14cc71dc20e7"
        )

        if response.status_code != 200:
            response.failure(response.text)
            raise response

        response.success()
