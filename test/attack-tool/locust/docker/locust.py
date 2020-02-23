from locust import TaskSet
from locust.contrib.fasthttp import FastHttpLocust
from locust.wait_time import constant

from apis.sample2.scenario import BinarySubTaskSet
from apis.sample3.scenario import BigBinarySubTaskSet

class MainScenario(TaskSet):

    tasks = {
        BigBinarySubTaskSet: 1
    }

class HttpLocustUser(FastHttpLocust):
    task_set = MainScenario

    # Tast 実行待ち時間 (min, max)
    wait_time = constant(1)
