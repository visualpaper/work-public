from locust import TaskSet, task

from actions.get_rest_action import GetRestAction

class GetRestSubTaskSet(TaskSet):

    def on_start(self):
        self._action = GetRestAction(self.client)

    @task(1)
    def call(self):
        response = self._action.call()

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
