from locust import HttpLocust, TaskSet, TaskSequence, task, seq_task

import json

class SubTaskSet(TaskSequence):

    # sequece 番号順に実施される。
    # ※ task にも order というものがある。
    @seq_task(1)
    def my_task(self):
        self.client.get("/app/rest/get")

    # SubTask の場合、親 Task に終了したことを通知しなければならない。
    # ※ しなかった場合、この SubTask のみずっと実施することになってしまう。
    @seq_task(2)
    def on_stop(self):
        self.interrupt()

class SampleScenario(TaskSet):

    APPLICATION_JSON_CONTENT_TYPE_HEADER = {"content-type": "application/json"}

    # 別の TaskSet を含むこともできる。
    # ※ nest task も使ってみたものの、イマイチうまくいかなかったのでこちらの方法をとっている。
    # ※ {class: weight} の指定をしている。order などの指定も可能。
    tasks = {SubTaskSet: 5}

    def on_start(self):
        self._post_id = 1
        self._post_value = "sample222"

    def on_stop(self):
        pass

    @task(1)
    def get(self):
        with self.client.get("/app/rest/get", catch_response=True) as response:
            # デフォルトで 400/500 系は failure として報告されるが
            # ↓のように、特定の場合は success として看做すようにすることもできる。
            if response.status_code == 404:
                response.success()

    # 重みづけ。 get task の 10 倍の実行率となる。
    # ※ 実行率な点に注意。必ず 10 回数される度に 1 回 get が走るわけではなかった。
    # ※ task 内に指定しなければ 1 と看做されるかも？明記されてはなかった。
    @task(10)
    def post(self):
        payload = {
            "id": self._post_id,
            "value": self._post_value
        }

        self.client.post(
            "/app/rest/post",
            data=json.dumps(payload),
            headers=self.APPLICATION_JSON_CONTENT_TYPE_HEADER
        )

class HttpLocustUser(HttpLocust):
    task_set = SampleScenario

    # Tast 実行待ち時間 (min, max)
    min_wait = 500
    max_wait = 1000
