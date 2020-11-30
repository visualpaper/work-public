# -*- coding: utf-8 -*-
import boto3
import logging

from boto3.dynamodb.conditions import Key, Attr

# get dynamodb object
dynamodb = boto3.client('dynamodb')


def lambda_handler(event, context):
    """
    Methods to set with AWS Lambda.
    """

    for record in event['Records']:
        if record['eventName'] == "INSERT" or record['eventName'] == "MODIFY":
            item = record['dynamodb']['NewImage']
            logging.info(item)
