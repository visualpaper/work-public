from datetime import timedelta

class ESDeleteCommand:
    __QUERY_TEMPLATE = '{"size": %d ,"query": {"range": {"timestamp": {"lt": "%s"}}}}'

    def __init__(self, now, delete_day, delete_hour, delete_count):
        self.__timestamp = now - (timedelta(days=delete_day, hours=delete_hour))
        self.__delete_count = delete_count

    def buildQuery(self):
        return self.__QUERY_TEMPLATE % (
            self.__delete_count,
            self.__timestamp.strftime('%Y-%m-%dT%H:%M:%SZ')
        )

