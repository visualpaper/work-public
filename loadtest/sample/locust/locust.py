from locust import TaskSet
from locust.contrib.fasthttp import FastHttpLocust
from locust.wait_time import constant_pacing

from apis.scenario1.scenario import Scenario1SubTaskSet
from apis.scenario2.scenario import Scenario2SubTaskSet
from apis.scenario3.scenario import Scenario3SubTaskSet

from apis.sample3.scenario import BigBinarySubTaskSet
from apis.sample4.scenario import SuperBigBinarySubTaskSet
from apis.sample5.scenario import BigBinaryDownloadSubTaskSet
from apis.sample6.scenario import BufferBigBinarySubTaskSet

class MainScenario(TaskSet):

    tasks = {
        Scenario3SubTaskSet: 1
    }

class HttpLocustUser(FastHttpLocust):
    task_set = MainScenario

    # Tast 実行待ち時間
    wait_time = constant_pacing(1)
