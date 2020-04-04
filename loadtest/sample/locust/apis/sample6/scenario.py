from locust import TaskSet, task

from actions.postbufferbinary import PostBufferBinaryAction

import json
import random
from pathlib import Path

class BufferBigBinarySubTaskSet(TaskSet):
    UPLOAD_FILES_PATH = Path(__file__).parent.parent.parent / "datas" / "uploadFiles"
    UPLOAD_FILENAMES = ["003"]

    def on_start(self):
        self._action = PostBufferBinaryAction(self.client)

    @task(1)
    def call(self):
        path = self.UPLOAD_FILES_PATH / self.UPLOAD_FILENAMES[random.randrange(1)]

        with open(path.resolve(), 'rb') as upload_file:
            response = self._action.call(
                upload_file
            )

            if response.status_code != 200:
                response.failure(response.text)
                raise response

            response.success()
