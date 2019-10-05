from boto.connection import AWSAuthConnection

class ESClient(AWSAuthConnection):

    def __init__(self, region, **kwargs):
        super(ESClient, self).__init__(**kwargs)
        self._set_auth_region_name(region)
        self._set_auth_service_name("es")

    def _required_auth_capability(self):
        return ['hmac-v4']

    def request(self, method="GET", path="/", data=""):
        resp = self.make_request(method=method, path=path, data=data)
        return resp.read()

