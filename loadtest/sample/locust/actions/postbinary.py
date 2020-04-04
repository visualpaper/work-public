
class PostBinaryAction():

    APPLICATION_OCTET_STREAM_CONTENT_TYPE_HEADER = {"content-type": "application/octet-stream"}

    def __init__(self, client):
        self._client = client

    def call(self, payload):
        return self._client.post(
            "/app/rest/postBinary",
            data=payload,
            headers=self.APPLICATION_OCTET_STREAM_CONTENT_TYPE_HEADER,
            catch_response=True
        )
