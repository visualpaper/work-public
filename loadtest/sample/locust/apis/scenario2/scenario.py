from locust import TaskSet, task

import random
from pathlib import Path

from actions.post_binary_action import PostBinaryAction

class Scenario2SubTaskSet(TaskSet):

    UPLOAD_FILES_PATH = Path(__file__).parent.parent.parent / "datas" / "uploadFiles"
    UPLOAD_FILENAMES = ["003"]

    def on_start(self):
        self._action = PostBinaryAction(self.client)

    @task(1)
    def call(self):
        path = self.UPLOAD_FILES_PATH / self.UPLOAD_FILENAMES[random.randrange(1)]

        with open(path.resolve(), 'rb') as upload_file:
            response = self._action.call(
                upload_file
            )

            if hasattr(response, 'error') and response.error:
                try:
                    response.failure(
                        u'error:%s, body: %s '
                        % (response.error, json.loads(response.content.decode('utf8')))
                    )
                except:
                    response.failure(response.text)
                raise response.error

            response.success()
