import os
import json
import pytest
import urllib3
import logging

from typing import NamedTuple

import unittest
import unittest.mock

from src.main import lambda_handler

THIS_SCRIPT_DIR = os.path.dirname(os.path.abspath(__file__))

def toJson(jsonFile: str):
    path = os.path.normpath(os.path.join(THIS_SCRIPT_DIR, jsonFile))

    with open(path) as f:
        event = json.load(f)

    return event

class DummyContext(NamedTuple):
    function_name = ""
    function_version = ""
    invoked_function_arn = ""
    memory_limit_in_mb = ""
    aws_request_id = ""
    log_group_name = ""
    log_stream_name = ""

class TestHelloWorld(unittest.TestCase):
    def setUp(self):
        pass

    @pytest.fixture(autouse=True)
    def inject_fixtures(self, caplog):
        self._caplog = caplog

    def test_lambda_handler(self):
        with self._caplog.at_level(logging.INFO):
            event = toJson("./datas/event.json")
            lambda_handler(event=event, context=DummyContext())
            assert len(self._caplog.records) == 3

    def test_lambda_handler_empty(self):
        with self._caplog.at_level(logging.WARN):
            lambda_handler(event={"Records": []}, context=DummyContext())
            assert len(self._caplog.records) == 0

    def test_lambda_handler_fail(self):
        with self._caplog.at_level(logging.WARN):
            lambda_handler(event={}, context=DummyContext())
            assert len(self._caplog.records) == 1
