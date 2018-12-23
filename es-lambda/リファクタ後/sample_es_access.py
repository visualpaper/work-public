import os
import json
from datetime import datetime
from es_delete_command import ESDeleteCommand
from es_client import ESClient

ES_INDEX = "sample-index"
ES_PATH_TEMPLATE = "/%s/_delete_by_query"
RESULT_TEMPLATE = "deleted %d"

def lambda_handler(event, context):

    # Generate ES Client
    client = ESClient(
        region=os.environ['ES_REGION'],
        host=os.environ['ES_HOST'],
        aws_access_key_id=os.environ['AWS_ACCESS_KEY_ID'],
        aws_secret_access_key=os.environ['AWS_SECRET_ACCESS_KEY'],
        security_token=os.environ['AWS_SESSION_TOKEN'],
        is_secure=False
    )

    # Generate ES Delete Query
    command = ESDeleteCommand(
        datetime.now(),
        int(os.environ['DELETE_DAY']),
        int(os.environ['DELETE_HOUR']),
        int(os.environ['DELETE_COUNT'])
    )

    # ES Delete Query Execute
    result = json.loads(client.request("POST", ES_PATH_TEMPLATE % (ES_INDEX), command.buildQuery()))

    # Output Deleted Result
    return RESULT_TEMPLATE % (result['deleted'])

