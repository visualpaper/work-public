
class GetBinaryAction():

    def __init__(self, client):
        self._client = client

    def call(self, id):
        return self._client.get(
            "/app/rest/getBinary/{id}".format(id=id),
            catch_response=True,
            stream=True
        )
