import os
from boto.connection import AWSAuthConnection

class ESConnection(AWSAuthConnection):

    def __init__(self, region, **kwargs):
        super(ESConnection, self).__init__(**kwargs)
        self._set_auth_region_name(region)
        self._set_auth_service_name("es")

    def _required_auth_capability(self):
        return ['hmac-v4']

    def request(self, method="GET", path="/", data=""):
        resp = self.make_request(method=method, path=path, data=data)
        return resp.read()

def always_failed_handler(event, context):
    print('Exception IN')
    raise Exception('I failed!')

def lambda_handler(event, context):
    print('IN')
    access_key = os.environ['AWS_ACCESS_KEY_ID']
    secret_key = os.environ['AWS_SECRET_ACCESS_KEY']
    session_token = os.environ['AWS_SESSION_TOKEN']

    print(access_key)
    print(secret_key)
    print(session_token)
    host = 'search-sample-es-tldmm3wmlruf4ucj6nnn24htmm.us-west-2.es.amazonaws.com'
    client = ESConnection(
        region='us-west-2',
        host=host,
        aws_access_key_id=access_key,
        aws_secret_access_key=secret_key,
        security_token=session_token,
        is_secure=False
    )
    data='{"query": {"range": {"timestampi": {"lt": "2017-08-07T11:00:00"}}}}'

    print(client.request('POST', "/sample-index/_delete_by_query", data))
    return 'deleted'

